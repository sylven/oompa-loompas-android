package app.sylven.oompaloompas.repository

import androidx.paging.PagingSource
import app.sylven.oompaloompas.api.OompaApi
import app.sylven.oompaloompas.model.OompaLoompaPageItem
import retrofit2.HttpException
import java.io.IOException

class OompasPagingSource(
    private val oompaService: OompaApi
) : PagingSource<Int, OompaLoompaPageItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OompaLoompaPageItem> {

        // Retrofit calls that return the body type throw either IOException for network
        // failures, or HttpException for any non-2xx HTTP status codes. This code reports all
        // errors to the UI, but you can inspect/wrap the exceptions to provide more context.
        return try {
            // Key may be null during a refresh, if no explicit key is passed into Pager
            // construction. Use 1 as default, because our API is indexed starting at index 1
            val pageNumber = params.key ?: 1

            // Suspending network load via Retrofit. This doesn't need to be wrapped in a
            // withContext(Dispatcher.IO) { ... } block since Retrofit's Coroutine
            // CallAdapter dispatches on a worker thread.
            val response = oompaService.getOompaLoompas(page = pageNumber)
            val listing = response.body()
            val oompas = listing?.results?.map { it }

            // Since 1 is the lowest page number, return null to signify no more pages should
            // be loaded before it.
            val prevKey = if (pageNumber > 1) pageNumber - 1 else null

            // This API defines that it's out of data when reached last page (total). When out of
            // data, we return `null` to signify no more pages should be loaded
            val nextKey = if (listing?.current ?: 1 < listing?.total ?: 0) pageNumber + 1 else null
            LoadResult.Page(
                data =  oompas ?: listOf(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}
