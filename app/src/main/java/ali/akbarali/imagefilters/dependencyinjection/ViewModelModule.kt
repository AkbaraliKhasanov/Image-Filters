package ali.akbarali.imagefilters.dependencyinjection

import ali.akbarali.imagefilters.viewmodels.EditImageViewModel
import ali.akbarali.imagefilters.viewmodels.SavedImagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EditImageViewModel(editImageRepository = get()) }
    viewModel { SavedImagesViewModel(savedImagesRepository = get()) }
}