package com.kotlinproject.app.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kotlinproject.app.ApiClass
import com.kotlinproject.app.BaseRetrofit

import com.kotlinproject.app.R
import com.kotlinproject.app.model.CitiesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiFragment : Fragment() {

    var apiClass: ApiClass? = null

    var spinner: Spinner? = null


    var textView: TextView? = null


    var arrayList: ArrayList<String> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_api, container, false)
        apiClass = BaseRetrofit.getClient()!!.create(ApiClass::class.java)
        spinner = view.findViewById(R.id.citiesSpinner)

        //call api
        callApiMethod(activity)


        return view
    }

    public fun callApiMethod(activity: FragmentActivity?) {

        val cities: Call<CitiesResponse>
        cities = apiClass!!.getCities()

        cities.enqueue(object : Callback<CitiesResponse> {
            override fun onFailure(call: Call<CitiesResponse>?, t: Throwable?) {

                Log.e(t.toString(), "Failure")
            }

            override fun onResponse(call: Call<CitiesResponse>?, response: Response<CitiesResponse>?) {

                if (response!!.isSuccessful) {

                    setSpinner(response.body())
                }

                Log.e(response.toString(), "Sucess")
            }


        })


    }


    public fun setSpinner(body: CitiesResponse) {


        for (i in 0 until body.data!!.size) {

            arrayList.add(body.data!!.get(i).city_name!!)


            Log.e(body.data!!.get(i).city_name, "setSpinner" + "arrayList " + arrayList)
        }

        //set Value

        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, arrayList)
            spinner!!.adapter = arrayAdapter


            spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    Toast.makeText(activity, "Selected Spinner" + arrayList[position], Toast.LENGTH_LONG).show()
                }


            }
        }


    }


}

