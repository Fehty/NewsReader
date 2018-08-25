package com.fehty.newsreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.fehty.newsreader.DrawerLayout.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, AllNewsFragment()).commit()

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.firstData -> {
                    setFragment(AllNewsFragment())
                    drawerLayout.closeDrawers()
                }
                R.id.secondData -> {
                    setFragment(WallJournalFragment())
                    drawerLayout.closeDrawers()
                }
                R.id.thirdData -> {
                    setFragment(BusinessFragment())
                    drawerLayout.closeDrawers()
                }
                R.id.fourthData -> {
                    setFragment(TechCrunchFragment())
                    drawerLayout.closeDrawers()
                }
                R.id.fifthData -> {
                    setFragment(BitcoinFragment())
                    drawerLayout.closeDrawers()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}
