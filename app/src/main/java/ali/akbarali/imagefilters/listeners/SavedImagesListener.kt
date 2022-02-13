package ali.akbarali.imagefilters.listeners

import java.io.File

interface SavedImagesListener {
    fun onImageClicked(file: File)
}