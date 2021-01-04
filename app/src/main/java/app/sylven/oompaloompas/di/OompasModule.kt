package app.sylven.oompaloompas.di

import app.sylven.oompaloompas.model.OompaLoompaPageItem
import app.sylven.oompaloompas.repository.DefaultOompasRepository
import app.sylven.oompaloompas.viewModel.OompaDetailViewModel
import app.sylven.oompaloompas.viewModel.OompasViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val oompasModule = module {

    viewModel {
        OompasViewModel(get(), get())
    }

    viewModel { (oompa: OompaLoompaPageItem) ->
        OompaDetailViewModel(get(), oompa)
    }

    single { DefaultOompasRepository()  }

}
