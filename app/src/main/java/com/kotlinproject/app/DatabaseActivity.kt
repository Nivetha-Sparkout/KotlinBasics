package com.kotlinproject.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.widget.Toast
import com.kotlinproject.app.model.User
import com.kotlinproject.app.model.UserDetails
import io.realm.Realm

class DatabaseActivity : AppCompatActivity() {


    var nameEditText: AppCompatEditText? = null

    var lastNameEditText: AppCompatEditText? = null

    var emailEditText: AppCompatEditText? = null

    var mobilNumberEditText: AppCompatEditText? = null

    var saveAppButtonCompat: AppCompatButton? = null

    var displayAppButtonCompat: AppCompatButton? = null


    var displayAppCompatTextView: AppCompatTextView? = null

    private lateinit var userDetails: UserDetails


    private lateinit var realm: Realm


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        Realm.init(this@DatabaseActivity)

        userDetails = UserDetails()
        realm = Realm.getDefaultInstance()

        nameEditText = this.findViewById(R.id.et_first_name)

        lastNameEditText = this.findViewById(R.id.et_last_name)

        emailEditText = this.findViewById(R.id.et_email)

        mobilNumberEditText = this.findViewById(R.id.et_mobile_nbr)


        saveAppButtonCompat = this.findViewById(R.id.saveBtn)
        displayAppButtonCompat = this.findViewById(R.id.DisplayBtn)

        displayAppCompatTextView = this.findViewById(R.id.tv_displayDetails)




        saveAppButtonCompat!!.setOnClickListener {

            var userModel = User(userDetails.getNextPrimaryKey(realm), nameEditText!!.text.toString(), lastNameEditText!!.text.toString(), emailEditText!!.text.toString(), mobilNumberEditText!!.text.toString())

            userDetails.addOrUpdateUser(realm, userModel)


            Toast.makeText(this@DatabaseActivity, "The Data Sshould Be Added", Toast.LENGTH_LONG).show()
        }


        displayAppButtonCompat!!.setOnClickListener {


            var data = ""
            var results = userDetails.getUsers(realm)
            results.forEach { result ->
                data += "$result \n --------------------------------------- \n"
            }
            displayAppCompatTextView!!.text = data

        }


    }
}
