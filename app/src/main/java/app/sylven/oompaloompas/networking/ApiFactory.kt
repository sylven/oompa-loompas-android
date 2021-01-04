package app.sylven.oompaloompas.networking

import app.sylven.oompaloompas.AppConstants
import app.sylven.oompaloompas.BuildConfig
import app.sylven.oompaloompas.api.OompasApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val oompaClient =
        if (BuildConfig.DEBUG) {
            OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient().newBuilder()
                .build()
        }

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(oompaClient)
        .baseUrl(AppConstants.OOMPA_LOOMPA_API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val oompasApi: OompasApi = retrofit().create(OompasApi::class.java)

}
