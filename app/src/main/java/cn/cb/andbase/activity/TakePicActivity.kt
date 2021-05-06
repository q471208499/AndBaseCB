package cn.cb.andbase.activity

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import cn.cb.andbase.R
import cn.cb.baselibrary.activity.BaseActivity
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.models.PermissionRequest
import es.dmoral.toasty.MyToast
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


const val REQUEST_IMAGE_CAPTURE = 1110
const val REQUEST_PERMISSION = 1100
const val REQUEST_TAKE_PHOTO = 1010

class TakePicActivity : BaseActivity(), EasyPermissions.PermissionCallbacks {
    val perms = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    var imageView: ImageView? = null
    var photoURI: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_pic)

        findViewById<Button>(R.id.take_pic_btn).setOnClickListener(click())
        imageView = findViewById(R.id.take_pic_img)
    }

    fun click() = View.OnClickListener {
        requestPermissions()
    }

    private fun requestPermissions() {
        var permission = PermissionRequest
            .Builder(this)
            .code(REQUEST_PERMISSION)
            .perms(perms)
            .rationale("权限不足，无法拍照！")
            .build()
        EasyPermissions.requestPermissions(this, permission)

        /*when {
            checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED -> {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION_CAMERA)
            }
            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED -> {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSION_STORAGE
                )
            }
            else -> {
                openCapture()
            }
        }*/
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        MyToast.show("权限不足！")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (perms.size == this.perms.size) {
            //openCapture()
            dispatchTakePictureIntent()
        } else {
            MyToast.show("权限不足！")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    fun openCapture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { tackPictureIntent ->
            tackPictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(tackPictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(imageBitmap)
        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            val bitmap = BitmapFactory.decodeStream(photoURI?.let {
                contentResolver.openInputStream(
                    it
                )
            })
            imageView?.setImageBitmap(bitmap)

        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    ex.printStackTrace()
                    // Error occurred while creating the File
                    //...
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    //路径：/sdcard/Android/data/cn.cb.andbase/files/Pictures
                    photoURI = FileProvider.getUriForFile(
                        this,
                        "cn.cb.andbase.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    var currentPhotoPath: String? = null

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

}