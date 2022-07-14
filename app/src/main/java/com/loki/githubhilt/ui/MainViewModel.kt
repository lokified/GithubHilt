package com.loki.githubhilt.ui

import androidx.lifecycle.*
import com.loki.githubhilt.data.domain.Repository
import com.loki.githubhilt.data.model.Repos
import com.loki.githubhilt.data.model.Users
import com.loki.githubhilt.data.model.responses.UserResponse
import com.loki.githubhilt.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val mErrorText = MutableLiveData<Event<String>>()
    val errorText : LiveData<Event<String>> = mErrorText

    fun loadUsers(searchTerm: String): LiveData<List<Users>?> {

        val users = MutableLiveData<List<Users>?>()

        viewModelScope.launch {

            val userResponse = repository.getUsers(searchTerm) { mErrorText.postValue(Event(it)) }

            val userList = userResponse?.results

            users.postValue(userList)

        }

        return users
    }
}