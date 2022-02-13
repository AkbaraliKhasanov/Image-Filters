package ali.akbarali.imagefilters.activities.savedimages

import ali.akbarali.imagefilters.activities.editimage.EditImageActivity
import ali.akbarali.imagefilters.activities.filteredimage.FilteredImageActivity
import ali.akbarali.imagefilters.adapter.SavedImagesAdapter
import ali.akbarali.imagefilters.databinding.ActivitySavedImagesBinding
import ali.akbarali.imagefilters.listeners.SavedImagesListener
import ali.akbarali.imagefilters.utilities.displayToast
import ali.akbarali.imagefilters.viewmodels.SavedImagesViewModel
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class SavedImagesActivity : AppCompatActivity(), SavedImagesListener {

    private lateinit var binding: ActivitySavedImagesBinding
    private val viewModel: SavedImagesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObserver()
        setListeners()
        viewModel.loadSavedImages()
    }

    private fun setupObserver() {
        viewModel.savedImagesUiState.observe(this) {
            val savedImagesDataState = it ?: return@observe
            binding.savedImagesProgressBar.visibility =
                if (savedImagesDataState.isLoading) View.VISIBLE else View.GONE
            savedImagesDataState.savedImages?.let { savedImages ->
                SavedImagesAdapter(savedImages, this).also { savedImagesAdapter ->
                    with(binding.rvSavedImages) {
                        this.adapter = savedImagesAdapter
                        visibility = View.VISIBLE
                    }
                }
            } ?: run {
                savedImagesDataState.error?.let { error ->
                    displayToast(error)
                }
            }
        }
    }

    private fun setListeners() {
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onImageClicked(file: File) {
        val fileUri = FileProvider.getUriForFile(
            applicationContext,
            "${packageName}.provider",
            file
        )
        Intent(
            applicationContext,
            FilteredImageActivity::class.java
        ).also { filteredImageIntent->
            filteredImageIntent.putExtra(EditImageActivity.KEY_FILTERED_IMAGE_URI,fileUri)
            startActivity(filteredImageIntent)
        }
    }
}