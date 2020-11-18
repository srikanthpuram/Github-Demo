package com.srikanthpuram.githubapidemo.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srikanthpuram.githubapidemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container,UsersListFragment()).commit()
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.github_users -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container,UsersListFragment()).commit()
                    return@setOnNavigationItemSelectedListener  true
                }
                R.id.github_commits -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container,CommitsListFragment()).commit()
                    return@setOnNavigationItemSelectedListener  true
                }
            }
            false
        }
    }
}
