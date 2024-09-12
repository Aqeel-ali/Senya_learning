package com.aqeel.senyaapp.ui.fragments

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearSnapHelper
import com.aqeel.senyaapp.R
import com.aqeel.senyaapp.adabters.DetailsEpoxyController
import com.aqeel.senyaapp.data.Attraction
import com.aqeel.senyaapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment:BaseFragment() {

    private var _binding:FragmentDetailBinding?=null
    private val binding get() = _binding!!


//
//    private val attraction:Attraction by lazy {
//        attractionList.find { it.id==safeArgs.id }!!
//    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner){
           val attraction=it

        //    Picasso.get().load(attraction.image_urls).into(binding.DetailsImage)
            binding.DetailsRecycler.setControllerAndBuildModels(DetailsEpoxyController(attraction.image_urls))
            LinearSnapHelper().attachToRecyclerView(binding.DetailsRecycler)
            binding.indicator.attachToRecyclerView(binding.DetailsRecycler)



            binding.DetailsTitle.text=attraction.title
            binding.DetailsDescrption.text=attraction.description
            binding.DetailsMonth.text=attraction.months_to_visit
            binding.DetailsFacts.text="${attraction.facts.size} Facts"

            binding.DetailsFacts.setOnClickListener {

                val stringBuilder=StringBuilder("")
                attraction.facts.forEach {
                    stringBuilder.append(it)
                    stringBuilder.append("\n")
                    stringBuilder.append("\n")
                }
                val message=stringBuilder.toString().substring(0,stringBuilder.toString().lastIndexOf("\n\n"))

                AlertDialog.Builder(requireContext())
                    .setTitle("${attraction.title} facts")
                    .setMessage(message)
                    .setPositiveButton("Ok"
                    ) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }


        }



    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.details_menu,menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


      return when (item.itemId){
          R.id.menuItemLocation->{
              // Creates an Intent that will load a map of San Francisco
              val attraction=activityViewModel.selectedAttractionLiveData.value?:return true
              activityViewModel.locationSelectedLiveData.postValue(attraction)

              true
          }
          else -> super.onOptionsItemSelected(item)
      }



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}