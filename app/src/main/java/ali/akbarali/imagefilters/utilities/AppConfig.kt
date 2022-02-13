package ali.akbarali.imagefilters.utilities

import ali.akbarali.imagefilters.dependencyinjection.repositoryModule
import ali.akbarali.imagefilters.dependencyinjection.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class AppConfig : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppConfig)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}