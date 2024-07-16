package com.example.mbtipersonalitytypes


import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        window.statusBarColor = Color.parseColor("#4298b4")

        val toolbar: Toolbar = findViewById(R.id.toolbar_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailType: TextView = findViewById(R.id.tv_detail_type)
        val tvDetailItem: TextView = findViewById(R.id.tv_detail_item)
        val tvDetailFrequencyMales: TextView = findViewById(R.id.tv_detail_frequency_males)
        val tvDetailFrequencyFemales: TextView = findViewById(R.id.tv_detail_frequency_females)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val tvDetailStrengths: TextView = findViewById(R.id.tv_detail_streghts)
        val tvDetailWeaknesses: TextView = findViewById(R.id.tv_detail_weaknesses)
        val tvDetailFamousPerson: TextView = findViewById(R.id.tv_detail_famous_person)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)

        val dataPersonality = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_personality", Personality::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Personality>("key_personality")
        }

        dataPersonality?.let {
            tvDetailName.text = it.name
            tvDetailType.text = it.type
            tvDetailItem.text = it.item
            tvDetailFrequencyMales.text = it.frequencyMales
            tvDetailFrequencyFemales.text = it.frequencyFemales
            tvDetailDescription.text = it.description
            tvDetailStrengths.text = it.strengths
            tvDetailWeaknesses.text = it.weaknesses
            tvDetailFamousPerson.text = it.famousPerson
            Glide.with(this)
                .load(it.photo)
                .into(ivDetailPhoto)
        }

        findViewById<Button>(R.id.action_share).setOnClickListener {
            shareHeroDetail()
        }
    }

    private fun shareHeroDetail() {
        val dataPersonality = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_personality", Personality::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Personality>("key_personality")
        }
        dataPersonality?.let {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Check out this personality: ${it.name}")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }


}
