package io.github.skullhound.boodschappenlijst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..100) {
            data.add(ItemsViewModel("Item $i"))
        }

        val adapter = CustomAdapter(data)

        recyclerView.adapter = adapter

    }
}