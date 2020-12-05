package com.example.cws_project

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BookmarkItemAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{BookmarkItemFragment.newItemFragment(1)} //병.의원
            1->{BookmarkItemFragment.newItemFragment(2)} //약국
            2->{BookmarkItemFragment.newItemFragment(3)} //응급기관
            else -> BookmarkItemFragment.newItemFragment(1)
        }
    }
}