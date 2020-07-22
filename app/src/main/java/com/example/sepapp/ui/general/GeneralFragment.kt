package com.example.sepapp.ui.general

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sepapp.R
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.sepapp.data.SepSession
import com.example.sepapp.viewModel.SepSessionViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_general.*
import kotlinx.android.synthetic.main.session_grid_item.view.*

/**
 * This is the fragment where users can view general event info
 */
class GeneralFragment : Fragment() {

    private lateinit var sepSessionViewModel: SepSessionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Disable the back to home button
        (requireActivity() as AppCompatActivity).run{
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_general, container, false)

        //val week:Int = 3

        sepSessionViewModel = ViewModelProvider(requireActivity()).get(SepSessionViewModel::class.java)
        sepSessionViewModel.allSessionData.observe(viewLifecycleOwner, Observer
        { list ->
            // Populate the fragment with 3-week session data
            for (i in 1..3){
                val weekSessions = list.filterIndexed{
                        index, _ ->  index%3==(i-1)
                }
                general_linear_layout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
                general_linear_layout.dividerDrawable = resources.getDrawable(R.drawable.divider, null)

                //val newCardView = createCardView(i, weekSessions)
                //general_linear_layout.addView(newCardView)
                addWeekScrollView(general_linear_layout, weekSessions, i)
            }
        })
        return view
    }

    /**
     * Add One week of sessions into the fragment programmatically with HorizontalScrollView
     */
    private fun addWeekScrollView(parent: ViewGroup, sessions:List<SepSession>, week:Int){

        val weekLinerLayout = LinearLayout(context)

        weekLinerLayout.orientation = LinearLayout.VERTICAL
        val weekLinerLayoutParams = RelativeLayout.LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )

        //weekLinerLayout.layoutParams.width = MATCH_PARENT
        //weekLinerLayout.layoutParams.height = WRAP_CONTENT
        weekLinerLayout.layoutParams = weekLinerLayoutParams

        // TextView: Week title
        val weekTitle = TextView(weekLinerLayout.context)
        weekTitle.setTextAppearance(R.style.TextAppearance_MaterialComponents_Headline5)
        weekTitle.text = getString(R.string.title_week_text, week.toString())

        // Scroll View: Sessions in a week
        val weekScrollView = HorizontalScrollView(weekLinerLayout.context)
        val weekScrollViewParams = RelativeLayout.LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
        //weekScrollView.layoutParams.width = MATCH_PARENT
        //weekScrollView.layoutParams.height = weekScrollViewHeight
        weekScrollView.layoutParams = weekScrollViewParams

        val scrollLinearLayout = LinearLayout(weekScrollView.context)
        scrollLinearLayout.orientation = LinearLayout.HORIZONTAL
        val innerParams = RelativeLayout.LayoutParams(
            MATCH_PARENT,
            MATCH_PARENT
        )

        //scrollLinearLayout.layoutParams.width = MATCH_PARENT
        //scrollLinearLayout.layoutParams.height = MATCH_PARENT
        scrollLinearLayout.layoutParams = innerParams

        addWeekSessionsToScrollView(scrollLinearLayout, sessions)

        weekScrollView.addView(scrollLinearLayout)
        weekLinerLayout.addView(weekTitle)
        weekLinerLayout.addView(weekScrollView)

        parent.addView(weekLinerLayout)
    }


    private fun addWeekSessionsToScrollView(parent: ViewGroup, sessions:List<SepSession>){
        for (session in sessions){
            val sessionCardView = LayoutInflater.from(context).inflate(R.layout.session_grid_item, parent ,false)
            sessionCardView.layoutParams.width = 600
            sessionCardView.setOnClickListener {
                Snackbar.make(it,"Clickable in 'All Sessions' fragment",Snackbar.LENGTH_LONG).show()
            }

            Log.i("scroll", session.sessionName)
            // TODO: Refactor
            with(sessionCardView) {
                titleText.text = session.sessionName
                descriptionText.text = session.description
                timeText.text = session.date

                Glide.with(parent.rootView)
                    .load(session.imageSrc)
                    .into(session_image)
            }

            parent.addView(sessionCardView)
        }
    }


    /*fun createCardView(week:Int, sessions:List<SepSession>):MaterialCardView{
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
    }*/


}