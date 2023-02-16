package io.github.skullhound.boodschappenlijst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()
        val adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

        fun addGrocery(view: View) {
            data.add(ItemsViewModel("Lorem Ipsum Grocery"))
        }

    }
}