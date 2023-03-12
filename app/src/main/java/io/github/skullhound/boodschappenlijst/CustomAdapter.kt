package io.github.skullhound.boodschappenlijst

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ItemsViewModel>, private val activity: MainActivity) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val checkboxIcon: ImageView
        val cardView: CardView

        init {
            textView = view.findViewById(R.id.textView)
            checkboxIcon = view.findViewById(R.id.checkBox)
            cardView = view.findViewById(R.id.cardView)
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
            holder.checkboxIcon.setImageResource(R.drawable.checkbox_marked)
        } else {
            holder.checkboxIcon.setImageResource(R.drawable.checkbox_blank)
        }

        if (item.crossedOff && activity.filterState == 1) {
            holder.cardView.visibility = View.GONE
            holder.cardView.layoutParams = RecyclerView.LayoutParams(0, 0)
        } else if (!item.crossedOff && activity.filterState == 2) {
            holder.cardView.visibility = View.GONE
            holder.cardView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }

        holder.itemView.setOnClickListener {
            item.crossedOff = !item.crossedOff
            activity.updateItem(item, item.text)
        }

        holder.itemView.setOnLongClickListener {
            val dialog = UpdateModifyDialogFragment(item)
            dialog.show(
                activity.supportFragmentManager,
                UpdateModifyDialogFragment.TAG
            )
            true
        }

    }

    override fun getItemCount() = mList.size

}