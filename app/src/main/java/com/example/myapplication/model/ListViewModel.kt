package com.example.myapplication.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.Content
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

    fun getList() {
        _users2.value = listOf(
            Content("1", null),
            Content("2", null),
            Content("3", null),
            Content("4", null),
            Content("5", null)
        )
    }

    private val _users = MutableLiveData<List<ItemDemo>>()
    private val _users2 = MutableLiveData<List<Content>>()

    val demo: LiveData<List<ItemDemo>> = _users
    val demo2: LiveData<List<Content>> = _users2
}