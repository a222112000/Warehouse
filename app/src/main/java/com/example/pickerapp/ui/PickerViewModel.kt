package com.example.pickerapp.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pickerapp.data.DataItems
import com.example.pickerapp.data.Item
import com.example.pickerapp.domain.PickerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PickerViewModel @Inject constructor(private val repository: PickerRepository) : ViewModel() {

    var sourceList = mutableStateListOf<Item>()
    var destinationList = mutableStateListOf<Item>()

    fun warehousePicker(item: Item) {
        sourceList.remove(item)
        destinationList.add(item)
    }

    fun warehouseReturn(item: Item) {
        destinationList.remove(item)
        sourceList.add(item)
    }

    fun addToWarehouse(item: Item){
        addItemToWarehouse(item)
    }

    fun addToDispatcher(item: Item){
        addItemToDispatcher(item)
    }


//    private val _jsonViewModels = MutableLiveData<List<Item>>()
//    val jsonViewModels: LiveData<List<Item>> get() = _jsonViewModels

//    init {
//        _jsonViewModels.value = repository.warehouseList()
//    }

    fun getWareHouseItems():List<Item>{
        return repository.warehouseList()
    }

    fun getPickedUpList():List<Item>{
        return repository.pickedUpList()
    }

    fun addItemToWarehouse(jsonViewModel: Item) {
        val currentList = sourceList
        currentList.add(jsonViewModel)
        currentList.let {
            sourceList = it
            repository.saveToWareHouse(it)
        }
    }
//
    fun removeItemFromWareHouse(jsonViewModel: Item) {
        val currentList = sourceList
        currentList.remove(jsonViewModel)
        currentList.let {
            sourceList = it
            repository.saveToWareHouse(it)
        }
    }
//
    fun addItemToDispatcher(jsonViewModel: Item) {
        val currentList = destinationList
        currentList.add(jsonViewModel)
        currentList.let {
            destinationList = it
            repository.saveToDispatcher(it)
        }
    }
//
    fun removeItemFromDispatcher(jsonViewModel: Item) {
        val currentList = destinationList
        currentList.remove(jsonViewModel)
        currentList.let {
            destinationList = it
            repository.saveToDispatcher(it)
        }
    }
}