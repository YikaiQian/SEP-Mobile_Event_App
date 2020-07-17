package com.example.sepapp.ui.mySessions

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sepapp.R
import com.example.sepapp.data.SepSession
import com.example.sepapp.databinding.FragmentMyAttendSessionDetailBinding
import com.example.sepapp.databinding.FragmentSessionDetailBinding
import com.example.sepapp.util.TimeHelper
import com.example.sepapp.viewModel.SepSessionViewModel
import kotlinx.android.synthetic.main.fragment_my_attend_session_detail.view.*
import kotlinx.android.synthetic.main.fragment_session_detail.view.*
import java.util.*


class MyAttendSessionDetail : Fragment() {

    private lateinit var navController: NavController
    private lateinit var sepSessionViewModel: SepSessionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)

        navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )

        sepSessionViewModel =
            ViewModelProvider(requireActivity()).get(SepSessionViewModel::class.java)

        val binding = FragmentMyAttendSessionDetailBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = sepSessionViewModel

        val view = binding.root
        view.button_unregister.setOnClickListener {
            sepSessionViewModel.deleteSession()
        }

        view.button_export_calendar.setOnClickListener {
            exportToCalendar()
        }

        return view
        //return inflater.inflate(R.layout.fragment_my_attend_session_detail, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navController.navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun exportToCalendar() {
        val calendarIntent = Intent(Intent.ACTION_INSERT)

        val sessionInfo: SepSession? = sepSessionViewModel.selectedListSession.value
        if (sessionInfo == null) {
            Log.i("debug", "calendar session info is null")
        }

        calendarIntent.data = CalendarContract.Events.CONTENT_URI
        calendarIntent.type = "vnd.android.cursor.item/event"

        calendarIntent.putExtra(CalendarContract.Events.TITLE, sessionInfo!!.sessionName)
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Online")
        calendarIntent.putExtra(CalendarContract.Events.DESCRIPTION, sessionInfo.description)

        val millis: Long = TimeHelper.parseDateTimeStringToLocalMillis(sessionInfo.date) ?: 0
        calendarIntent.putExtra(
            CalendarContract.EXTRA_EVENT_BEGIN_TIME,
            millis
        );

        startActivity(calendarIntent)
    }


}