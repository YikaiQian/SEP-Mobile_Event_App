package com.example.sepapp.ui.allsessions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sepapp.R
import com.example.sepapp.data.SepSession
import java.util.*
import kotlin.collections.ArrayList

class SessionsRecyclerAdapter (val context: Context,
                               val sessions: List<SepSession>,
                               private val gridItemListener:SessionGridListener):
    RecyclerView.Adapter<SessionsRecyclerAdapter.ViewHolder>(), Filterable{

    var sessionsFiltered: List<SepSession> = sessions

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleText: TextView = itemView.findViewById(R.id.titleText)
        val image: ImageView = itemView.findViewById(R.id.session_image)
        val dateText: TextView = itemView.findViewById(R.id.timeText)
        val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
    }

    override fun getItemCount(): Int {
        return sessionsFiltered.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.session_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sessionData = sessionsFiltered[position]

        // Fill session data into each grid
        with(holder){
            titleText.let{
                it.text = sessionData.sessionName
                it.contentDescription = sessionData.sessionName
            }
            dateText.text = sessionData.date
            descriptionText.text = sessionData.description

            // Display images from url with Glide
            Glide.with(context)
                .load(sessionData.imageSrc)
                .into(image)
        }


        holder.itemView.setOnClickListener {
            gridItemListener.onGridItemClick(sessionData)
        }
    }


    /**
     * The adapter and the fragment are connected in a listener relationship.
     * The fragment is the listener (grid item onclick event).
     */
    interface SessionGridListener {
        fun onGridItemClick(sepSession: SepSession)
    }

    /**
     * Filter of the search bar to select sessions displayed on screen by session name
     */
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchText = constraint.toString()

                if (searchText.isEmpty()) {
                    sessionsFiltered = sessions
                } else {
                    val resultList = ArrayList<SepSession>()
                    for (row in sessions) {
                        if (row.sessionName.toLowerCase(Locale.ROOT)
                                .contains(searchText.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    sessionsFiltered = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = sessionsFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                sessionsFiltered = results?.values as List<SepSession>
                notifyDataSetChanged()
            }

        }
    }
}