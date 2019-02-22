package com.kotlinproject.app.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.kotlinproject.app.ItemAdapter
import com.kotlinproject.app.R
import com.kotlinproject.app.model.User


class ItemFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)


        val users = ArrayList<UserProfile>()
        users.add(UserProfile("Paul", "Mr"))
        users.add(UserProfile("Jane", "Miss"))
        users.add(UserProfile("John", "Dr"))
        users.add(UserProfile("Amy", "Mrs"))
        users.add(UserProfile("guna", "Mrs"))
        users.add(UserProfile("savi", "Mrs"))
        users.add(UserProfile("nisha", "Mrs"))
        users.add(UserProfile("susi", "Mrs"))
        users.add(UserProfile("meha", "Mrs"))
        users.add(UserProfile("gayu", "Mrs"))
        users.add(UserProfile("pragiya", "Mrs"))
        users.add(UserProfile("suba", "Mrs"))
        users.add(UserProfile("nive", "Mrs"))
        users.add(UserProfile("praveen", "Mrs"))
        users.add(UserProfile("suga", "Mrs"))
        users.add(UserProfile("nashir", "Mrs"))


        val rv = view.findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        var adapter = ItemAdapter(users, activity)
        rv.adapter = adapter

        val touchHandler = ItemTouchHelper(SwipeHandler(adapter, 0, (ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)))
        touchHandler.attachToRecyclerView(rv)

        return view
    }


    class SwipeHandler(val adapter: ItemAdapter, dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
        override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
            return false
        }


        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.removeRow(viewHolder.position)
        }
    }


}
