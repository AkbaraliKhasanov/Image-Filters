package ali.akbarali.imagefilters.adapter

import ali.akbarali.imagefilters.databinding.ItemContainerSavedImagesBinding
import ali.akbarali.imagefilters.listeners.SavedImagesListener
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class SavedImagesAdapter(
    private val savedImages: List<Pair<File, Bitmap>>,
    private val savedImagesListener: SavedImagesListener
) :
    RecyclerView.Adapter<SavedImagesAdapter.SavedImageViewHolder>() {

    inner class SavedImageViewHolder(val binding: ItemContainerSavedImagesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedImageViewHolder {
        return SavedImageViewHolder(
            ItemContainerSavedImagesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SavedImageViewHolder, position: Int) {
        with(holder) {
            with(savedImages[position]) {
                binding.imageSaved.setImageBitmap(second)
                binding.imageSaved.setOnClickListener {
                    savedImagesListener.onImageClicked(first)
                }
            }
        }
    }

    override fun getItemCount(): Int = savedImages.size
}