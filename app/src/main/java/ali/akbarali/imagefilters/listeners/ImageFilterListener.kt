package ali.akbarali.imagefilters.listeners

import ali.akbarali.imagefilters.data.ImageFilter

interface ImageFilterListener {
    fun onFilterSelected(imageFilter: ImageFilter)
}