package com.mohamed.safenest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.safenest.databinding.FragmentAlertBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlertFragment : Fragment()
{

    private var _binding: FragmentAlertBinding? = null
    private val binding get() = _binding!!
    private lateinit var alertAdapter: AlertAdapter
    private val handler = Handler(Looper.getMainLooper())

    private val updateTask = object : Runnable
    {
        override fun run()
        {
            fetchAlerts()
            handler.postDelayed(this, 5000)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentAlertBinding.inflate(inflater, container, false)

        setupRecyclerView()
        fetchAlerts()
        startAutoRefresh()

        return binding.root
    }

    private fun setupRecyclerView()
    {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        alertAdapter = AlertAdapter { alertId -> deleteAlert(alertId) }
        binding.recyclerView.adapter = alertAdapter
    }

    private fun fetchAlerts()
    {
        RetrofitClient.instance.getAlerts().enqueue(object : Callback<List<Alert>>
        {
            override fun onResponse(call: Call<List<Alert>>, response: Response<List<Alert>>)
            {
                if (response.isSuccessful)
                {
                    response.body()?.let { alertAdapter.setData(it) }
                }
            }

            override fun onFailure(call: Call<List<Alert>>, t: Throwable)
            {
                Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
                Log.e("Error", t.message.toString())
            }
        })
    }

    private fun deleteAlert(alertId: Int)
    {
        RetrofitClient.instance.deleteAlert(alertId).enqueue(object : Callback<DeleteModel>
        {
            override fun onResponse(call: Call<DeleteModel>, response: Response<DeleteModel>)
            {
                if (response.isSuccessful)
                {
                    Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT)
                        .show()
                    fetchAlerts()
                }
            }

            override fun onFailure(call: Call<DeleteModel>, t: Throwable)
            {
                Toast.makeText(requireContext(), "failed delete", Toast.LENGTH_SHORT).show()
                Log.e("Error", t.message.toString())
            }
        })
    }

    private fun startAutoRefresh()
    {
        handler.postDelayed(updateTask, 5000)
    }

    private fun stopAutoRefresh()
    {
        handler.removeCallbacks(updateTask)
    }

    override fun onResume()
    {
        super.onResume()
        startAutoRefresh()
    }

    override fun onPause()
    {
        super.onPause()
        stopAutoRefresh()
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        stopAutoRefresh()
        _binding = null
    }
}
