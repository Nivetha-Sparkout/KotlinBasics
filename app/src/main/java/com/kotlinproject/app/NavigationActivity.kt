package com.kotlinproject.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.kotlinproject.app.fragment.BookingFragment
import android.R.attr.fragment
import android.widget.Toast
import com.kotlinproject.app.fragment.ApiFragment
import com.kotlinproject.app.fragment.ItemFragment


class NavigationActivity : AppCompatActivity() {


    private lateinit var mDrawerLayout: DrawerLayout

    var string: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {

            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)

        }

        mDrawerLayout = findViewById(R.id.drawerLay)

        if (intent != null) {
            val intent = intent
            string = intent.getStringExtra("PASS")
        }

        Toast.makeText(this@NavigationActivity, string + "Paasing intent value", Toast.LENGTH_LONG).show()

        initiateNavigation()

    }


    public fun initiateNavigation() {

        val navigationView: NavigationView = findViewById(R.id.navigationView)

        //set default fragment
        val textFragment = BookingFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame, textFragment)
        transaction.commit()

        navigationView.setNavigationItemSelectedListener { item: MenuItem ->


            item.isChecked = true
            mDrawerLayout.closeDrawers()



            when (item.itemId) {

                R.id.booking -> {
                    // Get the text fragment instance
                    val textFragment = BookingFragment()

                    // Get the support fragment manager instance
                    val manager = supportFragmentManager

                    // Begin the fragment transition using support fragment manager
                    val transaction = manager.beginTransaction()

                    // Replace the fragment on container
                    transaction.replace(R.id.frame, textFragment)
//                    transaction.addToBackStack(null)

                    // Finishing the transition
                    transaction.commit()
                }

                R.id.history -> {
                    // Get the text fragment instance
                    val itemFragment = ItemFragment()

                    // Get the support fragment manager instance
                    val manager = supportFragmentManager

                    // Begin the fragment transition using support fragment manager
                    val transaction = manager.beginTransaction()

                    // Replace the fragment on container
                    transaction.replace(R.id.frame, itemFragment)
//                    transaction.addToBackStack(null)

                    // Finishing the transition
                    transaction.commit()
                }

                R.id.api -> {
                    val apiFragment = ApiFragment()
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.frame, apiFragment)
                    transaction.commit()

                }
            }



            true
        }


    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            return when (item.itemId) {
                android.R.id.home -> {
                    mDrawerLayout.openDrawer(GravityCompat.START)
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }
        } else {
            return false
        }


    }
}
