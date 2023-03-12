package io.github.skullhound.boodschappenlijst

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var data: ArrayList<ItemsViewModel>
lateinit var recyclerView: RecyclerView


class MainActivity : AppCompatActivity() {

    var filterState: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        data = ArrayList()

        val addButton: ImageButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val dialog = AddGroceryDialogFragment()
            dialog.show(supportFragmentManager, AddGroceryDialogFragment.TAG)
        }

        val filterImage: ImageView = findViewById(R.id.filterImage)

        val filterButton: ImageButton = findViewById(R.id.filterButton)
        filterButton.setOnClickListener {
            when (filterState) {
                0 -> {
                    filterButton.setImageResource(R.drawable.filter)
                    filterImage.setImageResource(R.drawable.checkbox_blank)
                    filterState = 1
                    filterImage.visibility = View.VISIBLE
                }
                1 -> {
                    filterButton.setImageResource(R.drawable.filter)
                    filterImage.setImageResource(R.drawable.checkbox_marked)
                    filterState = 2
                    filterImage.visibility = View.VISIBLE
                }
                2 -> {
                    filterButton.setImageResource(R.drawable.filter_remove)
                    filterState = 0
                    filterImage.visibility = View.INVISIBLE
                }
            }

            val adapter = CustomAdapter(data, this)
            recyclerView.adapter = adapter
        }
    }

    fun addItem(groceryDesc: String) {
        data.add(ItemsViewModel(groceryDesc))

        val adapter = CustomAdapter(data, this)
        recyclerView.adapter = adapter
    }

    fun removeItem(item: ItemsViewModel): Boolean {
        val success = data.remove(item)

        val adapter = CustomAdapter(data, this)
        recyclerView.adapter = adapter

        return success
    }

    fun updateItem(item: ItemsViewModel, string: String) {
        item.text = string
        data[data.indexOf(item)] = item

        val adapter = CustomAdapter(data, this)
        recyclerView.adapter = adapter
    }

}