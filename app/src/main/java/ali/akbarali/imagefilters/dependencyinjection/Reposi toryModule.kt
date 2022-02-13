package ali.akbarali.imagefilters.dependencyinjection

import ali.akbarali.imagefilters.repositories.EditImageRepository
import ali.akbarali.imagefilters.repositories.EditImageRepositoryImpl
import ali.akbarali.imagefilters.repositories.SavedImageRepository
import ali.akbarali.imagefilters.repositories.SavedImageRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<EditImageRepository> { EditImageRepositoryImpl(androidContext()) }
    factory<SavedImageRepository>{SavedImageRepositoryImpl(androidContext())}
}