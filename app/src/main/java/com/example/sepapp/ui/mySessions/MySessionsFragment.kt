package com.example.sepapp.ui.mySessions

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sepapp.R
import com.example.sepapp.data.SepSession
import com.example.sepapp.viewModel.SepSessionViewModel

/**
 * This is the fragment where users can view and modify their to-attend list
 */
class MySessionsFragment : Fragment(), MySessionListAdapter.SessionListItemListener {

    private lateinit var sepSessionViewModel: SepSessionViewModel
    private lateinit var listView: ListView
    private lateinit var navController: NavController
    private lateinit var adapter: MySessionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Disable the back to home button
        (requireActivity() as AppCompatActivity).run{
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)


        val view = inflater.inflate(R.layout.fragment_my_sessions, container, false)

        // To-attend list is shown in the listView
        listView = view.findViewById(R.id.my_session_listView)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        sepSessionViewModel =
            ViewModelProvider(requireActivity()).get(SepSessionViewModel::class.java)
        sepSessionViewModel.myAttendSessionData.observe(viewLifecycleOwner, Observer {
            adapter = MySessionListAdapter(requireContext(), it, this)
            listView.adapter = adapter
        })

        return view
    }

    /**
     * Go to detail fragment if user click one of the session on the fragment
     */
    override fun onListItemClick(sepSession: SepSession) {
        Log.i("list_log", sepSession.sessionName)

        sepSessionViewModel.selectedListSession.value = sepSession
        //Log.i("list_log", sepSessionViewModel.selectedListSession.value!!.sessionName)
        //navController.navigate(R.id.action_nav_my_session_detail)
        //sepSessionViewModel.selectedGridSession.value = sepSession
        navController.navigate(R.id.action_nav_my_attend_session_detail)
    }



}