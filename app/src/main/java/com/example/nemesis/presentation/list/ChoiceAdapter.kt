package com.example.nemesis.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nemesis.R
import com.squareup.picasso.Picasso

class ChoiceAdapter(private var dataSet: List<Choice>, private var listener: ((Choice) -> Unit)? = null) : RecyclerView.Adapter<ChoiceAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.choice_name)
            imageView = view.findViewById(R.id.choice_font)

        }
    }

    fun UpdateList(list: List<Choice>){

        dataSet = list
        notifyDataSetChanged() //Notifie comme quoi la liste a été changée

    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.choice_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val choice = dataSet[position
        ]
        viewHolder.textView.text = choice.name
        Picasso.get().load(choice.img_drawable).into(viewHolder.imageView);
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(choice)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
