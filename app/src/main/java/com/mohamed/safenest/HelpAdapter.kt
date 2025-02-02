package com.mohamed.safenest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class HelpAdapter(private val stepsList: List<String>) :
    RecyclerView.Adapter<HelpAdapter.StepViewHolder>()
{

    class StepViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val tvStep: TextView = view.findViewById(R.id.tvStep)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.help_items, parent, false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int)
    {
        holder.tvStep.text = stepsList[position]
    }

    override fun getItemCount(): Int
    {
        return stepsList.size
    }
}
