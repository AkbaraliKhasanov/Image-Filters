package ali.akbarali.imagefilters.adapter

import ali.akbarali.imagefilters.R
import ali.akbarali.imagefilters.data.ImageFilter
import ali.akbarali.imagefilters.databinding.ItemContainerFilterBinding
import ali.akbarali.imagefilters.listeners.ImageFilterListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ImageFiltersAdapter(
    private val imageFilters: List<ImageFilter>,
    private val imageFilterListener: ImageFilterListener
) :
    RecyclerView.Adapter<ImageFiltersAdapter.ImageFilterViewHolder>() {

    private var selectedFilterPosition = 0
    private var previouslySelectedPosition = 0

    inner class ImageFilterViewHolder(val binding: ItemContainerFilterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageFilterViewHolder {
        return ImageFilterViewHolder(
            ItemContainerFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageFilterViewHolder, position: Int) {
        with(holder) {
            with(imageFilters[position]) {
                binding.imageFilterPreview.setImageBitmap(filterPreview)
                binding.textFilterName.text = name
                binding.root.setOnClickListener {
                    if (position!=selectedFilterPosition){
                        imageFilterListener.onFilterSelected(this)
                        previouslySelectedPosition=selectedFilterPosition
                        selectedFilterPosition=position
                        with(this@ImageFiltersAdapter){
                            notifyItemChanged(previouslySelectedPosition,Unit)
                            notifyItemChanged(selectedFilterPosition,Unit)
                        }
                    }

                }
            }
            binding.textFilterName.setTextColor(
                ContextCompat.getColor(
                    binding.textFilterName.context,
                    if (selectedFilterPosition == position)
                        R.color.primaryDark
                    else
                        R.color.primaryText
                )
            )
        }
    }

    override fun getItemCount(): Int = imageFilters.size
}