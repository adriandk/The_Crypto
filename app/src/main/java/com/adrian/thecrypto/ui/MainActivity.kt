package com.adrian.thecrypto.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.adrian.thecrypto.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val favoriteFragment = FavoriteFragment()
    private val marketFragment = MarketFragment()
    private val fragmentManager = supportFragmentManager
    private var fragment: Fragment = marketFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        fragmentManager.beginTransaction().add(R.id.frame_layout, favoriteFragment)
            .hide(favoriteFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame_layout, marketFragment).commit()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.market -> {
                    fragmentManager.beginTransaction().hide(fragment).show(marketFragment).commit()
                    fragment = marketFragment
                }
                R.id.favorite -> {
                    fragmentManager.beginTransaction().hide(fragment).show(favoriteFragment).commit()
                    fragment = favoriteFragment
                }
            }
            true
        }
    }
}