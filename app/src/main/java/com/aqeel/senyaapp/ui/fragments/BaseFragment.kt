package com.aqeel.senyaapp.ui.fragments

import androidx.fragment.app.Fragment
import com.aqeel.senyaapp.ui.MainActivity

abstract class BaseFragment() :Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

}