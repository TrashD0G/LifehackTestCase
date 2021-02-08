package com.artem.testCase.retrofit

import com.artem.testCase.POJO.CompanyInfoPOJO
import com.artem.testCase.POJO.CompanyPOJO
import com.artem.testCase.di.DaggerAppComponent
import retrofit2.Response
import javax.inject.Inject

class ApiRequest {

    @Inject
    lateinit var api:LifehackApi


    init {
        DaggerAppComponent.create().injectApiRequest(this)
    }

    suspend fun getRequestCompany(): Response<ArrayList<CompanyPOJO>>{
        return api.getCompany()
    }

    suspend fun getRequestCompanyInfo(id: Int): Response<ArrayList<CompanyInfoPOJO>>{
        return api.getCompanyInfo(id)
    }


}