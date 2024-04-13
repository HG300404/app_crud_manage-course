package com.example.a22iteb036_lemaihuong

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.a22iteb036_lemaihuong.databinding.ActivityMainBinding
import com.example.a22iteb036_lemaihuong.fragments.bai1Fragment
import com.example.a22iteb036_lemaihuong.fragments.bai2Fragment
import com.example.a22iteb036_lemaihuong.fragments.bai3Fragment
import com.google.android.material.navigation.NavigationView


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


//        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        drawerLayout = binding.drawerLayout

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,bai1Fragment()).commit()
            navigationView.setCheckedItem(R.id.nav_bai1)
        }
    }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_bai1 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, bai1Fragment()).commit()
            R.id.nav_bai2 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, bai2Fragment()).commit()
            R.id.nav_bai3 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, bai3Fragment()).commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

//    override fun onSupportNavigateUp(): Boolean {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//            return true // Consumed the event, navigation handled
//        }
//        return navigateUp() // Delegate to default navigation handling
//    }
    override fun onBackPressed() {
    super.onBackPressed()
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}