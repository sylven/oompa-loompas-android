package app.sylven.oompaloompas.repository

import androidx.paging.PagingData
import app.sylven.oompaloompas.model.OompaLoompa
import app.sylven.oompaloompas.model.OompaLoompaPageItem
import kotlinx.coroutines.flow.Flow

interface OompasRepository {

    fun fetchOompaLoompas(): Flow<PagingData<OompaLoompaPageItem>>

    fun fetchOompaLoompa(id: Int): Flow<OompaLoompa>

}
