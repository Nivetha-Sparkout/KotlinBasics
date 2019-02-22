package com.kotlinproject.app.model

import io.realm.Realm
import io.realm.RealmResults

class UserDetails : UserInterface {
    override fun addOrUpdateUser(realm: Realm, user: User): Boolean {
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(user)
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun getUser(realm: Realm, userId: Long): User {
        return realm.where(User::class.java).equalTo("ID", userId).findFirst()!!
    }


    override fun editUser(realm: Realm, user: User): Boolean {
        try {
            realm.beginTransaction()
            realm.insertOrUpdate(user)
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    override fun deleteUser(realm: Realm, user: User): Boolean {
        try {
            realm.beginTransaction()
            user.deleteFromRealm()
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun removeLastUser(realm: Realm) {
        realm.beginTransaction()
        getLastUser(realm).deleteFromRealm()
        realm.commitTransaction()
    }

    fun getLastUser(realm: Realm): User {
        return realm.where(User::class.java).findAll().last()!!
    }

    override fun getUsers(realm: Realm): RealmResults<User> {
        return realm.where(User::class.java).findAll()
    }

    fun getNextPrimaryKey(realm: Realm): Long {
        var number: Number? = realm.where(User::class.java).max("ID")
        var nextkey: Long = 1
        if (number != null) {
            nextkey = number!!.toLong() + 1
        }
        return nextkey
    }

}