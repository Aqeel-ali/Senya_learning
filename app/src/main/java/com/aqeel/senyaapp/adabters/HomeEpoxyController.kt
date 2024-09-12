package com.aqeel.senyaapp.adabters

import com.airbnb.epoxy.EpoxyController
import com.aqeel.senyaapp.R
import com.aqeel.senyaapp.data.Attraction
import com.aqeel.senyaapp.databinding.EpoxyModelHeaderBinding
import com.aqeel.senyaapp.databinding.ViewHolderHomeBinding
import com.aqeel.senyaapp.epoxy.LoadingEpoxyModel
import com.aqeel.senyaapp.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HomeEpoxyController(private val onClickedCallback: (String) -> Unit) : EpoxyController() {


    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var elements = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }


    override fun buildModels() {
        if (isLoading) {
            //todo show loading state
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        } else if (elements.isEmpty()) {
            //todo show empty state
        } else {

            val firsyGroup=elements.filter { it.title.startsWith("s",true)||it.title.startsWith("d",true) }

            HeaderEpoxyModel("Recently Viewed").id("header_1").addTo(this)
            firsyGroup.forEach {
                HeaderEpoxyModel(it.title)
                AttractionEpoxyModel(it, onClickedCallback)
                    .id(it.id)
                    .addTo(this)
            }
            HeaderEpoxyModel("All Attraction").id("header_2").addTo(this)
            elements.forEach {
               HeaderEpoxyModel(it.title)
              AttractionEpoxyModel(it, onClickedCallback)
                    .id(it.id)
                    .addTo(this)
            }
        }


    }

    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<ViewHolderHomeBinding>(R.layout.view_holder_home) {

        override fun ViewHolderHomeBinding.bind() {
            titleTextView.text = attraction.title
            // binding.headerImageView  //image loading
            Picasso.get().load(attraction.image_urls[0]).into(headerImageView);
            monthsToVisitTextView.text = attraction.months_to_visit

            root.setOnClickListener {
                onClicked(attraction.id)
            }

        }
    }

    data class HeaderEpoxyModel(
        val title:String,
    ):ViewBindingKotlinModel<EpoxyModelHeaderBinding>(R.layout.epoxy_model_header){
        override fun EpoxyModelHeaderBinding.bind() {
            headerTitle.text=title
        }
    }


}