package com.kotlinproject.app.model

import io.realm.Realm
import io.realm.RealmResults

interface UserInterface {


    fun addOrUpdateUser(realm: Realm, user: User): Boolean
    fun getUser(realm: Realm, userId: Long): User
    fun deleteUser(realm: Realm, user: User): Boolean
    fun removeLastUser(realm: Realm)
    fun getUsers(realm: Realm): RealmResults<User>
}