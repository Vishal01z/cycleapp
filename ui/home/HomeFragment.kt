package com.vishal.cycleapp.ui.home

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val tv = TextView(requireContext())
        tv.text = "Home Screen"
        tv.textSize = 20f
        tv.gravity = Gravity.CENTER
        return tv
    }
}
