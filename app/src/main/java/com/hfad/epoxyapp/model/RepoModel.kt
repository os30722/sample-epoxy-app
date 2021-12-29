package com.hfad.epoxyapp.model

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.hfad.epoxyapp.R
import com.hfad.epoxyapp.vo.Repo

@EpoxyModelClass(layout = R.layout.item_repo)
abstract class RepoModel: EpoxyModelWithHolder<RepoModel.Holder>() {

    @EpoxyAttribute
    var repo: Repo? = null

    override fun bind(holder: Holder) {
        holder.name.text = repo?.name
        holder.description.text = repo?.description
        holder.language.text = repo?.language
    }


    class Holder: EpoxyHolder() {

        lateinit var name: TextView
        lateinit var description: TextView
        lateinit var language: TextView

        override fun bindView(itemView: View) {
            name = itemView.findViewById(R.id.repo_name)
            description = itemView.findViewById(R.id.repo_description)
            language = itemView.findViewById(R.id.repo_language)
        }

    }
}