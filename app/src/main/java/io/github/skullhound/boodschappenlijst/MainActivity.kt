package io.github.skullhound.boodschappenlijst

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var data: ArrayList<ItemsViewModel>
lateinit var recyclerView: RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        data = ArrayList()

        val button: ImageButton = findViewById(R.id.imageButton)
        button.setOnClickListener {
            val dialog = AddGroceryDialogFragment()
            dialog.show(supportFragmentManager, "")
        }
    }

    companion object {
        fun addItem(groceryDesc: String) {
            data.add(ItemsViewModel("• $groceryDesc"))

            val adapter = CustomAdapter(data)
            recyclerView.adapter = adapter
        }

        fun removeItem(item: ItemsViewModel): Boolean {
            val success = data.remove(item)

            val adapter = CustomAdapter(data)
            recyclerView.adapter = adapter

            return success
        }

        fun updateItem(item: ItemsViewModel) {
            data[data.indexOf(item)] = item

            val adapter = CustomAdapter(data)
            recyclerView.adapter = adapter
        }
    }

}