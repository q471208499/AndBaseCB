package cn.cb.andbase.activity

import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.recyclerview.widget.RecyclerView
import cn.cb.andbase.R
import cn.cb.andbase.adapter.PathAdapter
import cn.cb.baselibrary.activity.BaseActivity

/**
 * 路径
 */

class PathActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path)
        initBarView()

        val list = findViewById<RecyclerView>(R.id.path_list)

        list.adapter = PathAdapter(this, getList())
    }

    private fun getList(): List<Map<String, String>> {
        val list = ArrayList<Map<String, String>>()

        val map1 = HashMap<String, String>()
        map1["pathStr"] = getExternalFilesDir(Environment.DIRECTORY_ALARMS)?.absolutePath ?: ""
        map1["pathName"] = "exFile#ALARMS"
        list.add(map1)

        val map2 = HashMap<String, String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            map2["pathStr"] =
                getExternalFilesDir(Environment.DIRECTORY_AUDIOBOOKS)?.absolutePath ?: ""
        }
        map2["pathName"] = "exFile#AUDIOBOOKS[Q]"
        list.add(map2)

        val map3 = HashMap<String, String>()
        map3["pathStr"] = getExternalFilesDir(Environment.DIRECTORY_DCIM)?.absolutePath ?: ""
        map3["pathName"] = "exFile#DCIM"
        list.add(map3)

        val map4 = HashMap<String, String>()
        map4["pathStr"] = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath ?: ""
        map4["pathName"] = "exFile#DOCUMENTS"
        list.add(map4)

        val map5 = HashMap<String, String>()
        map5["pathStr"] = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath ?: ""
        map5["pathName"] = "exFile#DOWNLOADS"
        list.add(map5)

        val map6 = HashMap<String, String>()
        map6["pathStr"] = getExternalFilesDir(Environment.DIRECTORY_MOVIES)?.absolutePath ?: ""
        map6["pathName"] = "exFile#MOVIES"
        list.add(map6)

        val map7 = HashMap<String, String>()
        map7["pathStr"] = getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.absolutePath ?: ""
        map7["pathName"] = "exFile#MUSIC"
        list.add(map7)

        val map8 = HashMap<String, String>()
        map8["pathStr"] =
            getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS)?.absolutePath ?: ""
        map8["pathName"] = "exFile#NOTIFICATIONS"
        list.add(map8)

        val map9 = HashMap<String, String>()
        map9["pathStr"] = getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath ?: ""
        map9["pathName"] = "exFile#PICTURES"
        list.add(map9)

        val map10 = HashMap<String, String>()
        map10["pathStr"] = getExternalFilesDir(Environment.DIRECTORY_PODCASTS)?.absolutePath ?: ""
        map10["pathName"] = "exFile#PODCASTS"
        list.add(map10)

        val map11 = HashMap<String, String>()
        map11["pathStr"] = getExternalFilesDir(Environment.DIRECTORY_RINGTONES)?.absolutePath ?: ""
        map11["pathName"] = "exFile#RINGTONES"
        list.add(map11)

        val map12 = HashMap<String, String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            map12["pathStr"] =
                getExternalFilesDir(Environment.DIRECTORY_SCREENSHOTS)?.absolutePath ?: ""
        }
        map12["pathName"] = "exFile#SCREENSHOTS[Q]"
        list.add(map12)


        val map13 = HashMap<String, String>()
        map13["pathStr"] = packageCodePath
        map13["pathName"] = "packageCodePath"
        list.add(map13)

        val map14 = HashMap<String, String>()
        map14["pathStr"] = packageResourcePath
        map14["pathName"] = "packageResourcePath"
        list.add(map14)

        val map15 = HashMap<String, String>()
        map15["pathStr"] = getDatabasePath("app.db").absolutePath
        map15["pathName"] = "getDatabasePath"
        list.add(map15)

        val map16 = HashMap<String, String>()
        map16["pathStr"] = noBackupFilesDir.absolutePath
        map16["pathName"] = "noBackupFilesDir"
        list.add(map16)

        val map17 = HashMap<String, String>()
        map17["pathStr"] = cacheDir.absolutePath
        map17["pathName"] = "cacheDir"
        list.add(map17)

        val map18 = HashMap<String, String>()
        map18["pathStr"] = codeCacheDir.absolutePath
        map18["pathName"] = "codeCacheDir"
        list.add(map18)

        val map19 = HashMap<String, String>()
        map19["pathStr"] = dataDir.absolutePath
        map19["pathName"] = "dataDir"
        list.add(map19)

        val map20 = HashMap<String, String>()
        map20["pathStr"] = externalCacheDir?.absolutePath ?: ""
        map20["pathName"] = "externalCacheDir"
        list.add(map20)

        val map21 = HashMap<String, String>()
        map21["pathStr"] = filesDir.absolutePath
        map21["pathName"] = "filesDir"
        list.add(map21)

        val map22 = HashMap<String, String>()
        map22["pathStr"] = obbDir.absolutePath
        map22["pathName"] = "obbDir"
        list.add(map22)

        val map23 = HashMap<String, String>()
        map23["pathStr"] = Environment.getDataDirectory().absolutePath
        map23["pathName"] = "Data"
        list.add(map23)

        val map24 = HashMap<String, String>()
        map24["pathStr"] = Environment.getDownloadCacheDirectory().absolutePath
        map24["pathName"] = "DownloadCache"
        list.add(map24)

        val map25 = HashMap<String, String>()
        map25["pathStr"] = Environment.getRootDirectory().absolutePath
        map25["pathName"] = "Root"
        list.add(map25)

        val map26 = HashMap<String, String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            map26["pathStr"] = Environment.getStorageDirectory().absolutePath
        }
        map26["pathName"] = "Storage[R]"
        list.add(map26)

        val map27 = HashMap<String, String>()
        map27["pathStr"] =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
        map27["pathName"] = "DOWNLOADS"
        list.add(map27)

        val map28 = HashMap<String, String>()
        map28["pathStr"] =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath
        map28["pathName"] = "DOCUMENTS"
        list.add(map28)

        val map29 = HashMap<String, String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            map29["pathStr"] =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_SCREENSHOTS).absolutePath
        }
        map29["pathName"] = "SCREENSHOTS[Q]"
        list.add(map29)

        val map30 = HashMap<String, String>()
        map30["pathStr"] =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath
        map30["pathName"] = "PICTURES"
        list.add(map30)

        val map31 = HashMap<String, String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            map31["pathStr"] =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_AUDIOBOOKS).absolutePath
        }
        map31["pathName"] = "AUDIOBOOKS[Q]"
        list.add(map31)

        return list
    }
}