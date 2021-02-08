package com.artem.testCase.POJO

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CompanyPOJO: Serializable {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var companyName: String = "null"

    @SerializedName("img")
    var avatar: String = "null"
}

   