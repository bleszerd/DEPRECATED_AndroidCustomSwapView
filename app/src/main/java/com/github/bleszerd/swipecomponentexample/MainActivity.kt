package com.github.bleszerd.swipecomponentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerSwap = findViewById<RecyclerView>(R.id.recycler_swap)
        recyclerSwap.adapter = SwapAdapter()
        recyclerSwap.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}