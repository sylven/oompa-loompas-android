package app.sylven.oompaloompas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.sylven.oompaloompas.model.OompaLoompaPageItem
import app.sylven.oompaloompas.repository.OompaLoompasRepository
import kotlinx.coroutines.flow.Flow

class OompasViewModel(application: Application) : AndroidViewModel(application) {
    private val oompaLoompasRepository = OompaLoompasRepository(application)
    private lateinit var oompaLoompasResponse: Flow<PagingData<OompaLoompaPageItem>>

    fun fetchOompas(): Flow<PagingData<OompaLoompaPageItem>> {
        if (!this::oompaLoompasResponse.isInitialized) {
            oompaLoompasResponse = oompaLoompasRepository.fetchOompaLoompas()
                // cachedIn() shares the paging state across multiple consumers of posts,
                // e.g. different generations of UI across rotation config change
                .cachedIn(viewModelScope)
        }
        return oompaLoompasResponse
    }

}
