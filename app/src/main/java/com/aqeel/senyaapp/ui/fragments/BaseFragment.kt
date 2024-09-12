package com.aqeel.senyaapp.ui.fragments

import androidx.fragment.app.Fragment
import com.aqeel.senyaapp.arch.AttractionViewModel
import com.aqeel.senyaapp.data.Attraction
import com.aqeel.senyaapp.ui.MainActivity

abstract class BaseFragment() :Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }


    protected val activityViewModel:AttractionViewModel
        get() = (activity as MainActivity).attractionViewModel

}