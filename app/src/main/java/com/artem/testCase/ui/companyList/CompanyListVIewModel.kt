package com.artem.testCase.ui.companyList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.testCase.POJO.CompanyPOJO
import com.artem.testCase.di.DaggerAppComponent
import com.artem.testCase.retrofit.ApiRequest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class CompanyListVIewModel : ViewModel() {

    @Inject
    lateinit var apiRequest: ApiRequest


    private val _lifehackResponse: MutableLiveData<Response<ArrayList<CompanyPOJO>>> = MutableLiveData()
    var lifehackResponse = _lifehackResponse

    private val  _errorChecker:MutableLiveData<Boolean> = MutableLiveData()
    var errorChecker = _errorChecker


    init {
        DaggerAppComponent.create().injectCompanyListViewModel(this)
    }

    fun getCompany(){
        viewModelScope.launch {
            try {
                val response = apiRequest.getRequestCompany()
                _lifehackResponse.value = response
                _errorChecker.postValue(false)
            }catch (e:Exception){
                Log.i("MyTag", "Error: $e")
                _errorChecker.postValue(true)
            }
        }
    }

}