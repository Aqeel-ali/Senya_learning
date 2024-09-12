package com.aqeel.senyaapp.arch

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aqeel.senyaapp.data.Attraction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AttractionViewModel:ViewModel() {

    private val repository =AttractionsRepository()

    //for Home Fragment
    val attractionListLiveData = MutableLiveData<ArrayList<Attraction>>()
    fun init(context: Context){

            val attractionList = repository.parseAttractions(context)
            attractionListLiveData.postValue(attractionList as ArrayList<Attraction>)

    }

    //for Details Fragment
    val selectedAttractionLiveData=MutableLiveData<Attraction>()
    @SuppressLint("SuspiciousIndentation")
    fun onSelectAttraction(attractionId:String){
     val attraction=   attractionListLiveData.value?.find { attractionId==it.id }?:return
        selectedAttractionLiveData.postValue(attraction)

    }


    val locationSelectedLiveData = MutableLiveData<Attraction> ()






}