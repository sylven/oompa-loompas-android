package app.sylven.oompaloompas.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.sylven.oompaloompas.model.OompaLoompaPageItem

class OompaDetailViewModelFactory (
    application: Application,
    oompa: OompaLoompaPageItem
) :
    ViewModelProvider.Factory {

    private val mApplication: Application
    private val mOompa: OompaLoompaPageItem

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OompaDetailViewModel::class.java)) {
            return OompaDetailViewModel(mApplication, mOompa) as T
        }
        throw IllegalArgumentException("Cannot create Instance for this class")
    }

    init {
        mApplication = application
        mOompa = oompa
    }

}
