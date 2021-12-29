package com.hfad.epoxyapp.model

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.hfad.epoxyapp.R
import com.hfad.epoxyapp.vo.Repo

@EpoxyModelClass(layout = R.layout.item_progress)
abstract class ProgressModel: EpoxyModelWithHolder<ProgressModel.Holder>() {


    override fun bind(holder: Holder) {

    }


    class Holder: EpoxyHolder() {

        override fun bindView(itemView: View) {

        }

    }
}