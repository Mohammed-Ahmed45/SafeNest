package com.mohamed.safenest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.safenest.databinding.FragmentContactsBinding

class ContactsFragment : Fragment()
{


    lateinit var binding: FragmentContactsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }


    private fun initViews()
    {
        val contactList = listOf(
            Contacts(R.drawable.emergency_img, "Ambulance", "123"),
            Contacts(R.drawable.fire_ic, "Fire Department", "180"),
            Contacts(R.drawable.fire_ic, "Rescue Services", "126")
        )

        binding.rvContacts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvContacts.adapter = ContactsAdapter(contactList = contactList)

    }
}