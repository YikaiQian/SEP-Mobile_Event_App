package com.example.sepapp.ui.mySessions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.sepapp.R
import com.example.sepapp.data.SepSession
import java.util.*
import kotlin.collections.ArrayList

class MySessionListAdapter(
    val context: Context,
    var mySessions: List<SepSession>,
    val listItemListener: SessionListItemListener
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var sessionFilterList: List<SepSession> = mySessions

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.fragment_my_attent_list_item, parent, false)

        val titleText: TextView = rowView.findViewById(R.id.my_session_name)
        val image: ImageView = rowView.findViewById(R.id.my_session_img)
        val dateText: TextView = rowView.findViewById(R.id.session_time)
        val speakerText: TextView = rowView.findViewById(R.id.session_speaker)

        val mySession = getItem(position) as SepSession
        titleText.text = mySession.sessionName
        dateText.text = mySession.date
        speakerText.text = mySession.speaker

        Glide.with(context)
            .load(mySession.imageSrc)
            .into(image)

        rowView.setOnClickListener {
            listItemListener.onListItemClick(mySession)
        }

        return rowView
    }

    override fun getItem(position: Int): Any {
        return sessionFilterList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return sessionFilterList.size
    }


    interface SessionListItemListener {
        fun onListItemClick(sepSession: SepSession)
    }


}