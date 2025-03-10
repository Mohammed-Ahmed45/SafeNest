package com.mohamed.safenest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.safenest.databinding.ItemAlertBinding

class AlertAdapter(private val onDeleteClick: (Int) -> Unit) :
    RecyclerView.Adapter<AlertAdapter.AlertViewHolder>()
{

    private var alertList: MutableList<Alert> = mutableListOf()

    fun setData(newList: List<Alert>)
    {
        alertList.clear()
        alertList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder
    {
        val binding = ItemAlertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlertViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int)
    {
        val alert = alertList[position]
        holder.bind(alert)

        holder.binding.deleteAlertButton.setOnClickListener {
            onDeleteClick(alert.id)
            removeAlert(position)
        }
    }

    override fun getItemCount(): Int = alertList.size

    private fun removeAlert(position: Int)
    {
        if (position in alertList.indices)
        {
            alertList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    class AlertViewHolder(val binding: ItemAlertBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(alert: Alert)
        {
            binding.sensorId.text = "Sensor ID: ${alert.sensorID}"
            binding.alertType.text = "Type: ${alert.type}"
            binding.alertStatus.text = "Status: ${alert.status}"
            binding.alertTime.text = "Time: ${alert.createdAt}"
            binding.alertGuidance.text = "Guidance: ${alert.guidance}"
        }
    }
}
