package com.meutreino.tasks.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View

import com.devmasterteam.tasks.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val mViewHolder = ViewHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // navigationView.setNavigationItemSelectedListener(this);


        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)


        // Incia a fragment padrão
        this.startDefaultFragment()
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var fragment: Fragment? = null
        val id = item.itemId

        try {
            if (id == R.id.nav_all_tasks) {
                fragment = TaskListFragment.newInstance()
            } else if (id == R.id.nav_next_seven_days) {
                fragment = TaskListFragment.newInstance()
            } else if (id == R.id.nav_overdue) {
                fragment = TaskListFragment.newInstance()
            } else if (id == R.id.nav_logout) {
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Insere fragment substituindo qualquer existente
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit()

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * Incia a fragment padrão
     */
    private fun startDefaultFragment() {

        var fragment: Fragment? = null
        try {
            fragment = TaskListFragment.newInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Insere fragment substituindo qualquer existente
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit()
    }

    /**
     * ViewHolder
     */
    private class ViewHolder
}
