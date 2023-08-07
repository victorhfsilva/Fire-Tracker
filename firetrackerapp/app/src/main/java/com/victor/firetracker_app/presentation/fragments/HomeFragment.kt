package com.victor.firetracker_app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.victor.firetracker_app.data.repository.LiveDataManager
import com.victor.firetracker_app.databinding.HomeFragmentBinding
import com.victor.firetracker_app.presentation.viewmodels.HomeViewModel
import com.victor.firetracker_app.presentation.viewmodels.HomeViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels(){
        HomeViewModelFactory(requireActivity().application, requireView())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun updateData() {
        LiveDataManager.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                LiveDataManager.temperature.observe(viewLifecycleOwner, Observer { temperature ->
                    binding.temperatureCircleView.setTemperature(temperature)
                })
                LiveDataManager.isOnFire.observe(viewLifecycleOwner, Observer { isOnFire ->
                    if (isOnFire) binding.tvIsOnFire.visibility = View.VISIBLE
                    else binding.tvIsOnFire.visibility = View.INVISIBLE
                })
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateData()
        viewModel.startPeriodicDataRequest()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
