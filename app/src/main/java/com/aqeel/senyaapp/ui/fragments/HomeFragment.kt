package com.aqeel.senyaapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aqeel.senyaapp.R
import com.aqeel.senyaapp.adabters.HomeEpoxyController
import com.aqeel.senyaapp.databinding.FragmentHomeBinding

class HomeFragment:BaseFragment() {

    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val homeController= HomeEpoxyController { attractionid ->
            navController.navigate(R.id.action_homeFragment_to_detailFragment)
            activityViewModel.onSelectAttraction(attractionid)

        }

        homeController.isLoading=true
        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner){ attractionList->
            homeController.elements=attractionList
        }



        binding.homeRecyclerView.setController(homeController)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}