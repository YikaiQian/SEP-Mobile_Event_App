package com.example.sepapp.ui.sessionDetail

import android.os.Bundle
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
import com.example.sepapp.databinding.FragmentSessionDetailBinding
import com.example.sepapp.viewModel.SepSessionViewModel
import kotlinx.android.synthetic.main.fragment_session_detail.*
import kotlinx.android.synthetic.main.fragment_session_detail.view.*


class SessionDetailFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var sepSessionViewModel: SepSessionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as AppCompatActivity).run{
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        sepSessionViewModel = ViewModelProvider(requireActivity()).get(SepSessionViewModel::class.java)

        val binding = FragmentSessionDetailBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = sepSessionViewModel

        //return inflater.inflate(R.layout.fragment_session_detail, container, false)

        val view = binding.root
        view.attend_button.setOnClickListener {
            sepSessionViewModel.addSession()
        }


        return binding.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            navController.navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }

}