package com.artem.testCase.ui.company

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.testCase.POJO.CompanyInfoPOJO
import com.artem.testCase.di.DaggerAppComponent
import com.artem.testCase.retrofit.ApiRequest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class CompanyViewModel : ViewModel(){

    @Inject
    lateinit var apiRequest: ApiRequest

    private var companyList = emptyList<CompanyInfoPOJO>()

    private val _companyName: MutableLiveData<String> = MutableLiveData()
    var companyName = _companyName

    private val  _description: MutableLiveData<String> = MutableLiveData()
    var description = _description

    private val _img: MutableLiveData<String> = MutableLiveData()
    var img = _img

    private val _website: MutableLiveData<String> = MutableLiveData()
    var website = _website

    private val _phone: MutableLiveData<String> = MutableLiveData()
    var phone = _phone


    private val _lifehackResponse: MutableLiveData<Response<ArrayList<CompanyInfoPOJO>>> = MutableLiveData()
    var lifehackResponse = _lifehackResponse

    private val  _errorChecker:MutableLiveData<Boolean> = MutableLiveData()
    var errorChecker = _errorChecker

    init {
        DaggerAppComponent.create().injectCompanyViewModel(this)
    }


    fun getCompanyInformation(id: Int) {
        viewModelScope.launch {
            try {
                val response = apiRequest.getRequestCompanyInfo(id)


                _lifehackResponse.value = response
                _errorChecker.postValue(false)
            }  catch (e: Exception) {

                _errorChecker.postValue(true)
            }
        }
    }


    fun setData(newCompanyInfoList: ArrayList<CompanyInfoPOJO>){
        companyList = newCompanyInfoList
        val companyInfoPOJOData: CompanyInfoPOJO = companyList[0]
        bind(companyInfoPOJOData)
    }

    fun bind(companyInfoPOJOData: CompanyInfoPOJO) {
        _companyName.value = companyInfoPOJOData.name
        _description.value = companyInfoPOJOData.description
        _img.value = companyInfoPOJOData.img
        _website.value = companyInfoPOJOData.webSite
        _phone.value = companyInfoPOJOData.phone

    }

}