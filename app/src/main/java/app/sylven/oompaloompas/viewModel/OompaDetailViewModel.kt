package app.sylven.oompaloompas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import app.sylven.oompaloompas.model.OompaLoompa
import app.sylven.oompaloompas.model.OompaLoompaPageItem
import app.sylven.oompaloompas.repository.OompaLoompasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class OompaDetailViewModel(application: Application, oompa: OompaLoompaPageItem) :
    AndroidViewModel(application) {

    private val oompaLoompasRepository = OompaLoompasRepository(application)
    private var selectedOompa: Flow<OompaLoompaPageItem>
    private var selectedOompaId = 1

    init {
        selectedOompaId = oompa.id
        selectedOompa = flow { emit(oompa) }
    }

    fun getSelectedOompa(): Flow<OompaLoompaPageItem>? {
        return selectedOompa
    }

    fun fetchOompaDetails(): Flow<OompaLoompa> {
        return oompaLoompasRepository.fetchOompaLoompa(selectedOompaId)
    }

}
