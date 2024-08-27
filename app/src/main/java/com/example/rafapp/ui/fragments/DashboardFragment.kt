package com.example.rafapp.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rafapp.adapters.WeatherStationAdapter
import com.example.rafapp.databinding.FragmentDashboardBinding
import com.example.rafapp.ui.viewmodels.WeatherStationViewModel
import androidx.lifecycle.Observer

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val weatherStationViewModel: WeatherStationViewModel by viewModels()

    private lateinit var adapter: WeatherStationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = WeatherStationAdapter(emptyList())
        binding.weatherStationRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.weatherStationRecyclerView.adapter = adapter

        weatherStationViewModel.weatherStations.observe(viewLifecycleOwner, Observer { weatherStations ->
            if (weatherStations != null && weatherStations.isNotEmpty()) {
                adapter.updateData(weatherStations)
                // Puedes agregar logs aquí para ver si los datos están siendo recibidos y procesados.
                Log.d("DashboardFragment", "WeatherStations received: $weatherStations")
            } else {
                Log.d("DashboardFragment", "WeatherStations is empty or null")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
