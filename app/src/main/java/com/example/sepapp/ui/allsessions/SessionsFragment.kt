package com.example.sepapp.ui.allsessions

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sepapp.R
import com.example.sepapp.data.SepSession
import com.example.sepapp.viewModel.SepSessionViewModel


/**
 * This is the fragment where users can view list of sessions
 */
class SessionsFragment : Fragment(), SessionsRecyclerAdapter.SessionGridListener {

    private lateinit var sepSessionViewModel: SepSessionViewModel
    private lateinit var recyclerview: RecyclerView
    private lateinit var navController: NavController
    private lateinit var adapter: SessionsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Disable the back to home button and enable the search bar menu in this fragment
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.fragment_all_sessions, container, false)
        recyclerview = view.findViewById(R.id.allSessionsRecyclerView)

        // Navigation using Navigation architecture component
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        // Subscribe session data from viewModel
        sepSessionViewModel =
            ViewModelProvider(requireActivity()).get(SepSessionViewModel::class.java)
        sepSessionViewModel.allSessionData.observe(viewLifecycleOwner, Observer
        {
            adapter = SessionsRecyclerAdapter(requireContext(), it, this)
            recyclerview.adapter = adapter
        })

        return view
    }

    /**
     * Go to detail fragment if user click one of the session grid on the fragment
     */
    override fun onGridItemClick(sepSession: SepSession) {
        //Log.i("grid_log", sepSession.sessionName)

        sepSessionViewModel.selectedGridSession.value = sepSession
        navController.navigate(R.id.action_nav_session_detail)
    }

    /**
     * Set up a search bar to filter sessions displayed on the fragment
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_search_menu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView: SearchView = item.actionView as SearchView
        searchView.queryHint = getString(R.string.allsessions_searchbar_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String?): Boolean {
                adapter.filter.filter(searchText)
                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

}