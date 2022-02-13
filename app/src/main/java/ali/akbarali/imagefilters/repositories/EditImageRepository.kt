package ali.akbarali.imagefilters.repositories

import ali.akbarali.imagefilters.data.ImageFilter
import android.graphics.Bitmap
import android.net.Uri

interface EditImageRepository {
    suspend fun prepareImagePreview(imageUri: Uri): Bitmap?
    suspend fun getImagesFilters(image:Bitmap):List<ImageFilter>
    suspend fun saveFilteredImage(filteredBitmap: Bitmap):Uri?
}