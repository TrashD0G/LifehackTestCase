package com.artem.testCase.di

import com.artem.testCase.retrofit.ApiRequest
import com.artem.testCase.ui.company.CompanyViewModel
import com.artem.testCase.ui.companyList.CompanyListVIewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CompanyListProvides::class])
interface AppComponent {

    fun injectCompanyListViewModel(companyListVIewModel: CompanyListVIewModel)
    fun injectCompanyViewModel(companyViewModel: CompanyViewModel)
    fun injectApiRequest(apiRequest: ApiRequest)
}