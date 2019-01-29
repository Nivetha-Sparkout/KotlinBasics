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
import com.kotlinproject.app.User


class ItemFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)


        val users = ArrayList<User>()
        users.add(User("Paul", "Mr"))
        users.add(User("Jane", "Miss"))
        users.add(User("John", "Dr"))
        users.add(User("Amy", "Mrs"))
        users.add(User("guna", "Mrs"))
        users.add(User("savi", "Mrs"))
        users.add(User("nisha", "Mrs"))
        users.add(User("susi", "Mrs"))
        users.add(User("meha", "Mrs"))
        users.add(User("gayu", "Mrs"))
        users.add(User("pragiya", "Mrs"))
        users.add(User("suba", "Mrs"))
        users.add(User("nive", "Mrs"))
        users.add(User("praveen", "Mrs"))
        users.add(User("suga", "Mrs"))
        users.add(User("nashir", "Mrs"))


        val rv = view.findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        var adapter = ItemAdapter(users, activity)
        rv.adapter = adapter

        val touchHandler = ItemTouchHelper(SwipeHandler(adapter, 0, (ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)))
        touchHandler.attachToRecyclerView(rv)

        return view
    }


    class SwipeHandler(val adapter: ItemAdapter, dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.removeRow(viewHolder.position)
        }
    }


}
