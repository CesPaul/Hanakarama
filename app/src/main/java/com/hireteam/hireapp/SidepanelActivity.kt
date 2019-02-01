package com.hireteam.hireapp

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_authorization.*
import kotlinx.android.synthetic.main.activity_sidepanel.*
import kotlinx.android.synthetic.main.app_bar_sidepanel.*
import kotlinx.android.synthetic.main.nav_header_sidepanel.*

class SidepanelActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sidepanel)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.sidepanel, menu)

        val currentLogin = intent.getStringExtra("currentLogin")
        if (currentLogin != null){
            val textView: TextView = findViewById(R.id.loginTextViewNavBar) as TextView
            textView.setText(currentLogin)
        }

        val currentName = intent.getStringExtra("currentName")
        if (currentName != null){
            val textView: TextView = findViewById(R.id.nameTextViewNavBar) as TextView
            textView.setText(currentName)
        }

        val currentPhone = intent.getStringExtra("currentPhone")
        if (currentPhone != null){
            val textView: TextView = findViewById(R.id.phoneNumberTextViewNavBar) as TextView
            textView.setText(currentPhone)
        }

        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_myitems -> {
                // Handle the camera action
            }
            R.id.nav_items -> {

            }
            R.id.nav_createItem -> {

            }
            R.id.nav_settings -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
