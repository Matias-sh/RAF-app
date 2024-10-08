package com.example.rafapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rafapp.databinding.FragmentDashboardBinding
import com.example.rafapp.viewmodel.WeatherStationViewModel
import com.example.rafapp.adapter.WeatherStationAdapter

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
        adapter = WeatherStationAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.weatherStations.observe(viewLifecycleOwner, Observer { stations ->
            adapter.submitList(stations)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
