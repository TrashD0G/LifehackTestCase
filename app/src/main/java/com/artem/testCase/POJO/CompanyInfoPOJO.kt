package com.artem.testCase.POJO

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CompanyInfoPOJO: Serializable {

    @SerializedName("id")
    val id: Int = 0
    @SerializedName("name")
    val name: String = "null"
    @SerializedName("img")
    val img: String = "null"
    @SerializedName("description")
    val description: String = "null"
    @SerializedName("lat")
    val lat: Double = 0.0
    @SerializedName("lon")
    val lon: Double = 0.0
    @SerializedName("www")
    val webSite: String = "null"
    @SerializedName("phone")
    val phone: String = "null"
}