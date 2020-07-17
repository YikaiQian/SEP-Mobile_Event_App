package com.example.sepapp.viewModel

import android.app.Application
import android.content.Intent
import android.provider.CalendarContract
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sepapp.data.SepSession
import com.example.sepapp.data.SepSessionRepository

class SepSessionViewModel(app: Application) : AndroidViewModel(app) {
    private val dataRepo = SepSessionRepository(app)
    val allSessionData = dataRepo.sessionData
    val selectedGridSession = MutableLiveData<SepSession>()

    val myAttendSessionData = dataRepo.myAttendSessionData
    val selectedListSession = MutableLiveData<SepSession>()

    fun addSession() {
        if (selectedGridSession.value == null) {
            Log.i("de", "viewmodel selected grid session empty")
        } else {
            dataRepo.addSession(selectedGridSession.value!!)
        }
    }

    fun deleteSession(){
        if (selectedListSession.value == null) {
            Log.i("de", "viewmodel selected List session empty")
        } else {
            dataRepo.deleteSession(selectedListSession.value!!)
        }
    }



}