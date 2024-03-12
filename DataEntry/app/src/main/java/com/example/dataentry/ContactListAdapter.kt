package com.example.dataentry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactListAdapter(private val contacts: List<Contact>) :
    RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {


    class ContactViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.name)
        val phone: TextView = view.findViewById(R.id.phone)
        val image: ImageView = view.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_card, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.image.setImageResource(contacts[position].image)
        holder.name.text = contacts[position].name
        holder.phone.text = contacts[position].phone
    }

}