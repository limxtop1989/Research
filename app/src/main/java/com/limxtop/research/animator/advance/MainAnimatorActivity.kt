package com.limxtop.research.animator.advance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.limxtop.research.R

class MainAnimatorActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_animator)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = SlidePageAdapter(4, this)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }


    private class SlidePageAdapter(val pageCount: Int, activity: FragmentActivity) : FragmentStateAdapter(activity) {

        override fun getItemCount(): Int {
            return pageCount
        }

        override fun createFragment(position: Int): Fragment {
            return SlidePageFragment.newInstance(position.toString())
        }

    }
}