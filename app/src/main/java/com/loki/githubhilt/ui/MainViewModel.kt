package com.loki.githubhilt.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loki.githubhilt.data.domain.Repository
import com.loki.githubhilt.data.model.Repos
import com.loki.githubhilt.data.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var userList: MutableLiveData<List<Users>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<Users>> {

        return userList
    }

    fun loadUsers(user:  String) {

        repository.getUsers(user, liveData = userList)
    }
}