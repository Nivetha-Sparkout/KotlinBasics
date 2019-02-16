package com.kotlinproject.app.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
        @PrimaryKey open var ID: Long = 0,

        open var firstName: String = "",
        open var lastName: String = "",
        open var email: String = "",
        open var mobileNumber: String = "") : RealmObject() {


}