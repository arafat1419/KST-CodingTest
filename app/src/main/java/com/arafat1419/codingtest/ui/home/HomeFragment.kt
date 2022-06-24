package com.arafat1419.codingtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arafat1419.codingtest.assets.R
import com.arafat1419.codingtest.core.data.Resource
import com.arafat1419.codingtest.core.ui.UsersAdapter
import com.arafat1419.codingtest.core.utils.setRecyclerView
import com.arafat1419.codingtest.databinding.FragmentHomeBinding
import com.arafat1419.codingtest.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    // Initialize binding with null because we need to set it null again when fragment destroy
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Initialize viewModel with koin
    private val mainViewModel: MainViewModel by viewModel()

    // usersAdapter using lazy so adapter only initialize when variable has used
    private val usersAdapter: UsersAdapter by lazy {
        UsersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set recycler view
        setRecyclerView(binding.rvHome, LinearLayoutManager(requireContext()), usersAdapter)

        // Get users from view model and display when resource is success
        mainViewModel.getUsers().observe(viewLifecycleOwner) { result ->
            if (result.data != null) {
                when (result) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        usersAdapter.setUsers(result.data)
                    }
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Error -> Toast.makeText(
                        context, getString(R.string.check_your_connection), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // Item click action, will navigate to DetailFragment with userDomain as arguments
        usersAdapter.onItemClicked = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}