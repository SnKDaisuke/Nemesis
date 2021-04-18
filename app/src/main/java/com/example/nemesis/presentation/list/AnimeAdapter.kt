package com.example.nemesis.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nemesis.R
import com.squareup.picasso.Picasso

class AnimeAdapter(private var dataSet: List<Anime>, private var listener: ((Anime) -> Unit)? = null) : RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.anime_name)
            imageView = view.findViewById(R.id.anime_font)

        }
    }

    fun UpdateList(list: List<Anime>){

        dataSet = list
        notifyDataSetChanged() //Notifie comme quoi la liste a été changée

    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.anime_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val anime = dataSet[position
        ]
        viewHolder.textView.text = anime.title
        Picasso.get().load(anime.image_url).into(viewHolder.imageView);
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(anime)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
