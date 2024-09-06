package com.example.rafapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rafapp.databinding.FragmentDashboardBinding
import com.example.rafapp.viewmodel.WeatherStationViewModel
import com.example.rafapp.adapter.WeatherStationAdapter
import com.example.rafapp.ui.activities.MainActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherStationViewModel by viewModels()
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

        // Configuración del RecyclerView
        adapter = WeatherStationAdapter()
        binding.weatherStationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.weatherStationRecyclerView.adapter = adapter

        // Observador para actualizar la lista
        viewModel.weatherStations.observe(viewLifecycleOwner, Observer { stations ->
            if (stations.isNotEmpty()) {
                val latestStation = stations.first()
                (activity as? MainActivity)?.updateWeatherSummary(
                    lastComm = latestStation.dates.lastCommunication ?: "--/--/----",
                    temp = latestStation.meta?.airTemp?.toString() ?: "--.-",
                    humidity = latestStation.meta?.rh?.toString() ?: "--.-",
                    maxTemp = latestStation.meta?.airTemp?.toString() ?: "--.-",
                    minTemp = latestStation.meta?.airTemp?.toString() ?: "--.-"
                )
                adapter.submitList(stations)
            } else {
                Toast.makeText(context, "No se encontraron datos", Toast.LENGTH_SHORT).show()
            }
        })


        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        })

        // Inicializa la carga de datos
        refreshData()
    }

    fun refreshData() {
        viewModel.fetchWeatherStations()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
