package com.artem.testCase.retrofit

import com.artem.testCase.POJO.CompanyInfoPOJO
import com.artem.testCase.POJO.CompanyPOJO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LifehackApi {

    @GET("test.php")
    suspend fun getCompany(): Response<ArrayList<CompanyPOJO>>

    @GET("test.php")
    suspend fun getCompanyInfo(@Query("id") id: Int): Response<ArrayList<CompanyInfoPOJO>>

}