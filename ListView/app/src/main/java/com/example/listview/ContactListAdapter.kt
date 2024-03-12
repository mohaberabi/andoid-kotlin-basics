package com.example.listview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactListAdapter(private val contacts: List<Contact>) :
    RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {


    class ContactListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.name)
        val bio: TextView = view.findViewById(R.id.bio)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_item,
            parent, false
        )
        return ContactListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {

        holder.image.setImageResource(contacts[position].imageRes)

        holder.bio.text = contacts[position].bio
        holder.name.text = contacts[position].name

    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}