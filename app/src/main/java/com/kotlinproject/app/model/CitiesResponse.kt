package com.kotlinproject.app.model

class CitiesResponse {

    var status: String? = null

    constructor(status: String?, data: ArrayList<CitesDetails>?) {
        this.status = status
        this.data = data
    }

    var data: ArrayList<CitesDetails>? = null
    override fun toString(): String {
        return "CitiesResponse(status=$status, data=$data)"
    }

}