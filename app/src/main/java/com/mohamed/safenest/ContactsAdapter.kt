package com.mohamed.safenest

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.safenest.databinding.ContactsItemsBinding

class ContactsAdapter(val contactList: List<Contacts>) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>()
{
    class ContactsViewHolder(val binding: ContactsItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder
    {
        val binding =
                ContactsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun getItemCount(): Int = contactList.size
    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int)
    {
        val contact = contactList[position]
        holder.binding.tvImage.text = contact.title
        holder.binding.emergencyIc.setImageResource(contact.img)

        holder.binding.phoneIc.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${contact.phoneNumber}")
            holder.itemView.context.startActivity(intent)
        }
    }
}