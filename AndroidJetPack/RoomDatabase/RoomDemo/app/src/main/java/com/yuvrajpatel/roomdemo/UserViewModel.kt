package com.yuvrajpatel.roomdemo

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuvrajpatel.Event
import com.yuvrajpatel.roomdemo.db.User
import com.yuvrajpatel.roomdemo.db.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel(), Observable{

    private var isUpdateOrDelete = false
    private lateinit var updateOrDeleteUser : User

    val users = repository.users

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val btnTextSaveOrUpdate = MutableLiveData<String>()

    @Bindable
    val btnTextDeleteOrClearAll = MutableLiveData<String>()

    @Bindable
    val statusMessage = MutableLiveData<Event<String>>()

    @Bindable
    val message : LiveData<Event<String>> = statusMessage


    init {
        btnTextSaveOrUpdate.value = "Save"
        btnTextDeleteOrClearAll.value = "Clear All"
    }

    fun saveOrUpdate(){
        if(inputName.value.isNullOrEmpty() || inputEmail.value.isNullOrEmpty()){
            statusMessage.value = Event("Please enter all information")
        } else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value).matches()) {
            statusMessage.value = Event("Please enter valid e-mail address")
        } else {
            val name = inputName.value!!
            val email = inputEmail.value!!
            if (isUpdateOrDelete) {
                updateOrDeleteUser.name = name
                updateOrDeleteUser.email = email
                update(updateOrDeleteUser)
            } else {
                insert(User(0, name, email))
                inputName.value = null
                inputEmail.value = null
            }
        }
    }

    fun initUpdateAndDelete(user : User){
        inputName.value = user.name
        inputEmail.value = user.email
        isUpdateOrDelete = true
        updateOrDeleteUser = user
        btnTextSaveOrUpdate.value = "Update"
        btnTextDeleteOrClearAll.value = "Delete"
    }
    fun deleteOrClearAll(){
        if(isUpdateOrDelete){
            delete(updateOrDeleteUser)
        } else {
            deleteAll()
        }
    }

    fun insert(user: User) = viewModelScope.launch {
        val rowAdded = repository.insert(user)
        if(rowAdded > -1) {
            statusMessage.value = Event("User inserted successfully $rowAdded")
        } else {
            statusMessage.value = Event("Error occurred")
        }
    }

    fun update(user: User) = viewModelScope.launch {
        val rowUpdated = repository.update(user)
        if(rowUpdated > 0) {
            statusMessage.value = Event("User updated successfully $rowUpdated")
            setButtonText()
        } else {
            statusMessage.value = Event("Error occurred")
        }
    }

    fun delete(user: User) = viewModelScope.launch {
        val rowDeleted = repository.delete(user)
        if(rowDeleted > 0) {
            statusMessage.value = Event("User deleted successfully $rowDeleted")
            setButtonText()
        } else {
            statusMessage.value = Event("Error occurred")
        }
    }

    fun deleteAll() = viewModelScope.launch {
        val rowDeleted = repository.deleteAll()
        if(rowDeleted > 0) {
            statusMessage.value = Event("All users deleted successfully $rowDeleted")
            setButtonText()
        } else {
            statusMessage.value = Event("Error occurred")
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    fun setButtonText() {
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        btnTextSaveOrUpdate.value = "Save"
        btnTextDeleteOrClearAll.value = "Clear All"
    }
}