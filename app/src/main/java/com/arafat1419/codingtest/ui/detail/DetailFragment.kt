package com.arafat1419.codingtest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.arafat1419.codingtest.core.domain.domain.UserDomain
import com.arafat1419.codingtest.core.utils.setAvatarGenerator
import com.arafat1419.codingtest.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailFragment : Fragment() {

    // Initialize binding with null because we need to set it null again when fragment destroy
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    NavHostFragment.findNavController(this@DetailFragment).navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set option menu
        setHasOptionsMenu(true)

        // Set data user from arguments
        setData(args.userDomain)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Set click action to back icon in toolbar
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setData(user: UserDomain) {
        with(binding) {
            val avatar = user.name?.let { setAvatarGenerator(requireContext(), it) }

            Glide.with(requireContext())
                .load("")
                .apply(
                    RequestOptions.placeholderOf(avatar)
                        .error(avatar)
                )
                .into(imgUser)

            txtName.text = user.name
            txtUsername.text = user.username
            txtWebsite.text = user.website
            txtPhone.text = user.phone
            txtEmail.text = user.email

            val addressFormat =
                getString(com.arafat1419.codingtest.assets.R.string.full_address_format)
            txtFullAddress.text = String.format(
                addressFormat,
                user.address?.street,
                user.address?.suite,
                user.address?.city,
                user.address?.zipcode
            )

            txtCompanyName.text = user.company?.name

            val companyFormat =
                getString(com.arafat1419.codingtest.assets.R.string.company_phrase_format)
            txtCompanyPhrase.text = String.format(
                companyFormat,
                user.company?.catchPhrase
            )
            txtCompanyBs.text = user.company?.bs
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}