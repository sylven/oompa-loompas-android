package app.sylven.oompaloompas.api

import app.sylven.oompaloompas.model.OompaLoompa
import app.sylven.oompaloompas.model.OompaLoompasPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OompaApi {

    @GET("oompa-loompas")
    suspend fun getOompaLoompas(
        @Query("page") page: Int = 1
    ): Response<OompaLoompasPageResponse>

    @GET("oompa-loompas/{id}")
    suspend fun getOompaLoompa(
        @Path("id") id: Int = 1
    ): Response<OompaLoompa>

}
