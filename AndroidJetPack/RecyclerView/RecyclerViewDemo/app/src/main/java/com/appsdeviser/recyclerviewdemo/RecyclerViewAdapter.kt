package com.appsdeviser.recyclerviewdemo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_list_item.view.*

class RecyclerViewAdapter(private val personList : List<Person>, private val clickListener:(Person) -> Unit) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.recyclerview_list_item, parent, false)
        return CustomViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(personList[position], clickListener)
    }

}
class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

    fun bind(person : Person,clickListener:(Person) -> Unit){
        view.textViewName.text = person.name
        view.setOnClickListener {
            clickListener(person)
        }

    }
}