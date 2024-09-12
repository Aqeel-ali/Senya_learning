package com.aqeel.senyaapp.arch

import android.content.Context
import android.util.Log
import com.aqeel.senyaapp.R
import com.aqeel.senyaapp.data.Attraction
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AttractionsRepository {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @OptIn(ExperimentalStdlibApi::class)
    fun parseAttractions(context: Context):List<Attraction> {

         val textFromFile = context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val adapter: JsonAdapter<List<Attraction>> = moshi.adapter<List<Attraction>>()
        return adapter.fromJson(textFromFile) ?: emptyList()


//        val textFromFile =
//            resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
//
//        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//
//        val adapter: JsonAdapter<List<Attraction>> = moshi.adapter<List<Attraction>>()
//        return try {
//            val response = adapter.fromJson(textFromFile)
//            response ?: emptyList()
//        } catch (e: Exception) {
//            Log.i("test", "parseAttractions: error ${e.message}")
//            getAttractionList()
//        }

    }


}