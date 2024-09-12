package com.aqeel.senyaapp.adabters

import com.airbnb.epoxy.EpoxyController
import com.aqeel.senyaapp.R
import com.aqeel.senyaapp.databinding.ModelHeaderImageBinding
import com.aqeel.senyaapp.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class DetailsEpoxyController(
    val imageUrls:List<String>
):EpoxyController() {
    override fun buildModels() {
        imageUrls.forEachIndexed { index, url ->
            HeaderImageEpoxyModel(url).id("image_$index").addTo(this)
        }
    }
    inner class HeaderImageEpoxyModel(val imageUrl:String): ViewBindingKotlinModel<ModelHeaderImageBinding>(R.layout.model_header_image){
        override fun ModelHeaderImageBinding.bind() {
            Picasso.get().load(imageUrl).into(DetailsImage)
        }
    }

}