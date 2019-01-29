package com.kotlinproject.app

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast

class ItemAdapter(val userList: ArrayList<User>, val activity: FragmentActivity?) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return userList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        setAnimation(holder.itemView)
        holder?.txtName?.text = userList[position].name
        holder?.txtTitle?.text = userList[position].title

        holder?.txtName.setOnClickListener {

            Toast.makeText(activity, holder.txtName.text, Toast.LENGTH_SHORT).show()

        }

    }

    private fun setAnimation(viewToAnimate: View) {
        if (viewToAnimate.animation == null) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.animation = animation
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(v)
    }

    fun removeRow(adapterPosition: Int) {
        Log.e(adapterPosition.toString(), "RemoveView")
        userList.removeAt(adapterPosition)
        notifyDataSetChanged()

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById<TextView>(R.id.txtName)
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
    }

}