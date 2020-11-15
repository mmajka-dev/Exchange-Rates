package com.mmajka.waluty.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mmajka.waluty.Fragments.AllCurrencies.AllCurrencies
import com.mmajka.waluty.Fragments.FavoriteCurrencies.FavoriteCurrencies

class TabAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> AllCurrencies()
            else -> return FavoriteCurrencies()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "All"
            else ->{
                return "Favorites"
            }
        }
    }
}