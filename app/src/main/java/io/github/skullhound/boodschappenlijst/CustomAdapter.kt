package io.github.skullhound.boodschappenlijst

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.textView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]

        holder.textView.text = item.text

        if (item.crossedOff) {
            holder.textView.paintFlags = (Paint.STRIKE_THRU_TEXT_FLAG)
        }

        holder.itemView.setOnClickListener {
            item.crossedOff = !item.crossedOff
            MainActivity.updateItem(item)
        }

        holder.itemView.setOnLongClickListener {
            MainActivity.removeItem(item)
        }
    }

    override fun getItemCount() = mList.size

}