package com.kotlinproject.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.util.Log
import android.widget.Toast
import com.kotlinproject.app.R.id.et_first_name
import com.kotlinproject.app.model.User
import com.kotlinproject.app.model.UserDetails
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_database.*

class DatabaseActivity : AppCompatActivity() {


    var nameEditText: AppCompatEditText? = null

    var lastNameEditText: AppCompatEditText? = null

    var emailEditText: AppCompatEditText? = null

    var mobilNumberEditText: AppCompatEditText? = null

    var saveAppButtonCompat: AppCompatButton? = null

    var displayAppButtonCompat: AppCompatButton? = null

    var updateAppCompatButton: AppCompatButton? = null

    var deleteAppCompatButton: AppCompatButton? = null


    var displayAppCompatTextView: AppCompatTextView? = null

    var displaySingleAppCompatTextView: AppCompatTextView? = null

    private lateinit var userDetails: UserDetails

    var arrayList: ArrayList<String> = ArrayList()


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

        displaySingleAppCompatTextView = this.findViewById(R.id.tv_individaul_displayDetails)

        updateAppCompatButton = this.findViewById(R.id.UpdateBtn)

        deleteAppCompatButton = this.findViewById(R.id.deleteBtn)


        //clear db

        deleteAppCompatButton!!.setOnClickListener {


            realm.executeTransaction { realm -> realm.deleteAll() }

        }


        //update data


        updateAppCompatButton!!.setOnClickListener {
            var results = userDetails.getUsers(realm)
 for (i in 0 until results.size) {

     var firstName = results.get(i)!!.firstName

     val myString = firstName

     var lastName = results.get(i)!!.lastName

     val myStringLastName = lastName


     var email = results.get(i)!!.email

     val myStringEmail = email


     var mobNumber = results.get(i)!!.mobileNumber

     val myStringMobNbr = mobNumber

     et_first_name.text = SpannableStringBuilder(myString)
     et_last_name.text = SpannableStringBuilder(myStringLastName)
     et_email.text = SpannableStringBuilder(myStringEmail)
     et_mobile_nbr.text = SpannableStringBuilder(myStringMobNbr)


 }



            var userModel = User(userDetails.getNextPrimaryKey(realm), nameEditText!!.text.toString(), lastNameEditText!!.text.toString(), emailEditText!!.text.toString(), mobilNumberEditText!!.text.toString())
            userDetails.editUser(realm, userModel)


        }


        //save data

        saveAppButtonCompat!!.setOnClickListener {


            Log.e(nameEditText!!.text.toString(), "NameEditText")

            var userModel = User(userDetails.getNextPrimaryKey(realm), nameEditText!!.text.toString(), lastNameEditText!!.text.toString(), emailEditText!!.text.toString(), mobilNumberEditText!!.text.toString())

            userDetails.addOrUpdateUser(realm, userModel)


            Toast.makeText(this@DatabaseActivity, "The Data Should Be Added", Toast.LENGTH_LONG).show()
        }


        //display data

        displayAppButtonCompat!!.setOnClickListener {


            var data = ""
            var results = userDetails.getUsers(realm)

            for (i in 0 until results.size) {

                arrayList.add(i, results.get(i)!!.firstName)


            }

            displaySingleAppCompatTextView!!.text = arrayList.toString()

            Log.e(arrayList.toString(), "ArrayList::")





            results.forEach { result ->
                data += "$result \n --------------------------------------- \n"
            }
            displayAppCompatTextView!!.text = data
        }


    }
}
