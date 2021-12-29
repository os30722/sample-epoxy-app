package com.hfad.epoxyapp.data

import android.util.Log
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.hfad.epoxyapp.model.ProgressModel_
import com.hfad.epoxyapp.model.RepoModel_
import com.hfad.epoxyapp.vo.Repo


class RepoListController: PagedListEpoxyController<Repo>(){
    override fun buildItemModel(currentPosition: Int, item: Repo?): EpoxyModel<*> {
        return RepoModel_().id(item!!.id).repo(item)

    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)
        ProgressModel_().id("loading").addIf(models.isNotEmpty(), this)
    }

}