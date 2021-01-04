package app.sylven.oompaloompas

import android.app.Application
import app.sylven.oompaloompas.di.oompasModule
import org.koin.android.ext.android.startKoin

class OompasApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(oompasModule)
        startKoin(this, moduleList)
    }

}
