package com.mohamed.safenest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HelpFragment : Fragment()
{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val stepsList = listOf(
            "1- Assess the Situation: Quickly determine the nature of the danger.",
            "2- Call for Help: Dial emergency services immediately.",
            "3- Evacuate if Necessary: If there’s a fire or gas leak, evacuate.",
            "4- Secure the Area: If there’s an intruder, lock yourself in a safe room.",
            "5- First Aid: If someone is injured, provide first aid.",
            "6- Stay Informed: Keep a radio or phone for updates.",
            "7- Emergency Kit: Have an emergency kit with essentials."
        )

        val adapter = HelpAdapter(stepsList)
        recyclerView.adapter = adapter

        return view
    }
}
