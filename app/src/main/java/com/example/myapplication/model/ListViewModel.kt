package com.example.myapplication.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.ItemDemo


class ListViewModel(application: Application) : AndroidViewModel(application) {
    fun getDataList(int: Int?) {
        if (int == null) {
            _users.value = listOf(
                ItemDemo("1"),
                ItemDemo("2"),
                ItemDemo("3"),
                ItemDemo("4"),
                ItemDemo("5")
            )
        } else {
            _users.value = listOf(
                ItemDemo("6"),
                ItemDemo("7"),
                ItemDemo("8"),
                ItemDemo("9"),
                ItemDemo("10")
            )
        }


    }

    private val _users = MutableLiveData<List<ItemDemo>>()
    val demo: LiveData<List<ItemDemo>> = _users

}