package com.example.pickerapp.domain
import android.content.Context
import android.util.Log
import com.example.pickerapp.data.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.OutputStreamWriter


class PickerRepository(private val context: Context) {

    fun warehouseList(): List<Item> {
            val json: String = try {
                val inputStream: InputStream = context.assets.open("source.json")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charsets.UTF_8)

            } catch (e: IOException) {
                e.printStackTrace()
                return emptyList()
            }

            val itemType = object : TypeToken<List<Item>>() {}.type
            return Gson().fromJson(json, itemType)
        }

    fun pickedUpList(): List<Item> {
        val json: String = try {
            val inputStream: InputStream = context.assets.open("destination.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)

        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }

        val itemType = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(json, itemType)
    }

    fun saveToWareHouse(jsonViewModels: List<Item>) {
        val json: String = Gson().toJson(jsonViewModels)

        try {
            val outputStream: OutputStream = context.openFileOutput("source.json", Context.MODE_PRIVATE)
            outputStream.write(json.toByteArray())
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun saveToDispatcher(jsonViewModels: List<Item>) {
        val json: String = Gson().toJson(jsonViewModels)

        try {

            val outputStream: OutputStreamWriter = OutputStreamWriter(context.openFileOutput("destination.json",
                Context.MODE_PRIVATE))
            outputStream.write(json)
            Log.d("YUYU",json.toString())
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}