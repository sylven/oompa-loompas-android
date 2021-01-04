package app.sylven.oompaloompas.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.sylven.oompaloompas.model.OompaLoompa
import app.sylven.oompaloompas.model.OompaLoompaPageItem
import app.sylven.oompaloompas.networking.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DefaultOompasRepository(context: Context) {
    
    private val oompaService = ApiFactory.oompaApi

    @OptIn(ExperimentalPagingApi::class)
    fun fetchOompaLoompas(): Flow<PagingData<OompaLoompaPageItem>> {
        return Pager(
            PagingConfig(
                pageSize = 25,
                enablePlaceholders = false,
                initialLoadSize = 25,
                prefetchDistance = 18
            )
        ) {
            OompasPagingSource(
                oompaService = oompaService
            )
        }.flow.flowOn(Dispatchers.IO)
    }

    fun fetchOompaLoompa(id: Int): Flow<OompaLoompa> {
        return flow {
            oompaService.getOompaLoompa(id).body()?.let { emit(it) }
        }.flowOn(Dispatchers.IO)
    }

}
