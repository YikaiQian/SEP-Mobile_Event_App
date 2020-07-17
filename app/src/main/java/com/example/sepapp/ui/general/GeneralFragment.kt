package com.example.sepapp.ui.general

import android.R.attr.*
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sepapp.R
import androidx.lifecycle.Observer
import com.example.sepapp.data.SepSession
import com.example.sepapp.ui.allsessions.SessionsRecyclerAdapter
import com.example.sepapp.viewModel.SepSessionViewModel
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.fragment_general.*


class GeneralFragment : Fragment() {

    private lateinit var sepSessionViewModel: SepSessionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_general, container, false)

        val week:Int = 3

        sepSessionViewModel = ViewModelProvider(requireActivity()).get(SepSessionViewModel::class.java)
        sepSessionViewModel.allSessionData.observe(viewLifecycleOwner, Observer
        { list ->
            for (i in 1..3){
                val weekSessions = list.filterIndexed{
                        index, _ ->  index%3==(i-1)
                }
                val newCardView = createCardView(i, weekSessions)
                general_linear_layout.addView(newCardView)
            }
        })
        return view
    }



    fun createCardView(week:Int, sessions:List<SepSession>):MaterialCardView{
        val context = requireContext()
        val cardView = MaterialCardView(context)

        val cardLinearLayout = LinearLayout(context)
        cardLinearLayout.orientation = LinearLayout.VERTICAL

        val cardParams = RelativeLayout.LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
        cardParams.setMargins(16)
        cardView.layoutParams = cardParams

        val textParams = RelativeLayout.LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
        textParams.setMargins(8)

        val sessionWeek = TextView(context)
        sessionWeek.text = "Week ${week.toString()}"
        sessionWeek.textSize = 24f
        sessionWeek.setTypeface(Typeface.DEFAULT_BOLD, Typeface.BOLD)
        sessionWeek.layoutParams = textParams
        cardLinearLayout.addView(sessionWeek)

        for (session in sessions){
            val sessionName = TextView(context)
            sessionName.text = session.sessionName
            sessionName.textSize = 20f
            sessionName.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
            sessionName.layoutParams = textParams

            val description = TextView(context)
            description.text = getString(R.string.dummy_description)
            description.textSize = 16f
            description.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC)


            cardLinearLayout.addView(sessionName)
            cardLinearLayout.addView(description)
        }

        cardView.addView(cardLinearLayout)
        return cardView
    }


}