package com.l0122077.ikhsanari.responsi1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Menerima data dari intent
        val playerName = intent.getStringExtra("player_name")
        val playerDesc = intent.getStringExtra("player_desc")
        val playerImageId = intent.getIntExtra("player_image_id", 0) // Mengambil ID gambar

        // Referensi komponen dari layout XML
        val imagePlayer = findViewById<ImageView>(R.id.image_player)
        val textPlayerName = findViewById<TextView>(R.id.text_player_name)
        val textPlayerDesc = findViewById<TextView>(R.id.text_player_desc)

        // Set nilai dari intent ke komponen layout
        imagePlayer.setImageResource(playerImageId)
        textPlayerName.text = playerName
        textPlayerDesc.text = playerDesc
    }
}
