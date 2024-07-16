package com.example.mbtipersonalitytypes

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvPersonalities: RecyclerView
    private val list = ArrayList<Personality>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.parseColor("#4298b4")

        val btnAccount: ImageButton = findViewById(R.id.about_page)
        btnAccount.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        rvPersonalities = findViewById(R.id.rv_personalities)
        rvPersonalities.setHasFixedSize(true)
        list.addAll(getListPersonalities())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvPersonalities.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvPersonalities.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListPersonalities(): ArrayList<Personality> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataType = resources.getStringArray(R.array.data_type)
        val dataItem = resources.getStringArray(R.array.data_item)
        val dataFrequencyMales = resources.getStringArray(R.array.data_frequencyMales)
        val dataFrequencyFemales = resources.getStringArray(R.array.data_frequencyFemales)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataStrengths = resources.getStringArray(R.array.data_strengths)
        val dataWeaknesses = resources.getStringArray(R.array.data_weaknesses)
        val dataFamousPerson = resources.getStringArray(R.array.data_famousPerson)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listPersonality = ArrayList<Personality>()
        for (i in dataName.indices) {
            val personality = Personality(dataName[i], dataType[i], dataItem[i], dataFrequencyMales[i], dataFrequencyFemales[i], dataDescription[i], dataStrengths[i], dataWeaknesses[i], dataFamousPerson[i], dataPhoto.getResourceId(i, -1))
            listPersonality.add(personality)
        }
        return listPersonality
    }

    private fun showRecyclerList() {
        rvPersonalities.layoutManager = LinearLayoutManager(this)
        val listPersonalityAdapter = ListPersonalityAdapter(list)
        rvPersonalities.adapter = listPersonalityAdapter
    }

}