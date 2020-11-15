package com.mmajka.waluty.MainActivity

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mmajka.waluty.Adapters.TabAdapter
import com.mmajka.waluty.Database.AllCurrenciesLocal
import com.mmajka.waluty.Database.DataSource
import com.mmajka.waluty.Models.Rate
import com.mmajka.waluty.R
import kotlinx.coroutines.*

class MainActivity() : AppCompatActivity() {

    //----------VIEWS
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var infoButton: ImageView
    var rates = listOf<Rate>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        infoButton = findViewById(R.id.infoButton)


        val fragmentAdapter = TabAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        viewPager.offscreenPageLimit = 1
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.setTabTextColors(resources.getColor(R.color.colorBlack), resources.getColor(
            R.color.colorWhite
        ))
        tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.colorAccent))
        tabLayout.setSelectedTabIndicator(resources.getDrawable(R.drawable.shape_button_background))

        //TODO info dialog - table info from server
    }


}