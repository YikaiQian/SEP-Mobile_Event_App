package com.example.sepapp.data

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.sepapp.R
import com.example.sepapp.util.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SepSessionRepository(val app: Application) {
    val sessionData = MutableLiveData<List<SepSession>>()
    val myAttendSessionData = MutableLiveData<List<SepSession>>()


    private val sepSessionDao = SepSessionDatabase.getDatabase(app).sepSessionDao()

    private val listType = Types.newParameterizedType(
        List::class.java, SepSession::class.java
    )

    init {
        getAllSessionData()
        CoroutineScope(Dispatchers.IO).launch {
            val data = sepSessionDao.getAllSessions()
            myAttendSessionData.postValue(data)

            if(data.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(app, myAttendSessionData.value?.get(0)?.sessionName,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun addSession(sepSession: SepSession){
        CoroutineScope(Dispatchers.IO).launch {
            sepSessionDao.insertSession(sepSession)
            val updatedMySessionData = sepSessionDao.getAllSessions()
            myAttendSessionData.postValue(updatedMySessionData)
        }
    }

    fun deleteSession(sepSession: SepSession){
        CoroutineScope(Dispatchers.IO).launch {
            sepSessionDao.deleteSession(sepSession)
            val updatedMySessionData = sepSessionDao.getAllSessions()
            myAttendSessionData.postValue(updatedMySessionData)
        }
    }

    private fun getAllSessionData() {
        val text = FileHelper.getTextFromResources(app, R.raw.all_sessions)
        Log.i("mylog", text)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adaptor: JsonAdapter<List<SepSession>> = moshi.adapter(listType)
        sessionData.value = adaptor.fromJson(text) ?: emptyList()

    }
}