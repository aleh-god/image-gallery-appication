package by.godevelopment.imagegalleryapplication.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class StringArrayListMoshiAdapter {
    @ToJson
    fun arrayListToJson(list: ArrayList<String>): List<String> = list

    @FromJson
    fun arrayListFromJson(list: List<String>): ArrayList<String> = ArrayList(list)
}