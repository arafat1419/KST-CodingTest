package com.arafat1419.codingtest.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arafat1419.codingtest.core.domain.domain.UserDomain
import com.arafat1419.codingtest.core.utils.setAvatarGenerator
import com.arafat1419.codingtest.databinding.BottomMapUserLayoutBinding
import com.arafat1419.codingtest.databinding.FragmentMapBinding
import com.arafat1419.codingtest.ui.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.OnPointAnnotationClickListener
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment() {

    // Initilize binding with null because we need to set it null again when fragment destroy
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    // Initialize viewModel with koin
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get data users from viewModel
        mainViewModel.getUsers().observe(viewLifecycleOwner) { result ->
            if (!result.data.isNullOrEmpty()) {

                // Set camera focus on first user
                val firstUser = result.data?.get(0)?.address?.geo
                if (firstUser != null) {
                    binding.mapView.getMapboxMap().apply {
                        loadStyleUri(Style.MAPBOX_STREETS)
                        setCamera(
                            CameraOptions.Builder()
                                .center(
                                    Point.fromLngLat(
                                        firstUser.lng.toString().toDouble(),
                                        firstUser.lat.toString().toDouble()
                                    )
                                )
                                .zoom(2.0)
                                .build()
                        )
                    }
                }

                // Add annotation on every users
                binding.progressBar.visibility = View.VISIBLE
                result.data?.forEach { user ->
                    addAnnotationToMap(user)
                }
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun addAnnotationToMap(user: UserDomain) {
        // Create an instance of the Annotation API and get the PointAnnotationManager.
        bitmapFromDrawableRes(
            requireContext(),
            com.arafat1419.codingtest.assets.R.drawable.ic_baseline_pin_drop_24
        )?.let { bitmap ->
            val userGeo = user.address?.geo

            val annotationApi = binding.mapView.annotations
            val pointAnnotationManager = annotationApi.createPointAnnotationManager()
            // Set options for the resulting symbol layer.
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                // Define a geographic coordinate.
                .withPoint(
                    Point.fromLngLat(
                        userGeo?.lng.toString().toDouble(),
                        userGeo?.lat.toString().toDouble()
                    )
                )
                // Specify the bitmap that assigned to the point annotation
                // The bitmap will be added to map style automatically.
                .withIconImage(bitmap)
            // Add the resulting pointAnnotation to the map.
            pointAnnotationManager.addClickListener(OnPointAnnotationClickListener {
                bottomSheet(user)
                true
            })
            pointAnnotationManager.create(pointAnnotationOptions)
        }
    }

    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
            // copying drawable object to not manipulate on the same reference
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }

    // Display bottom sheet and show user data
    private fun bottomSheet(user: UserDomain) {
        val sheetBinding = BottomMapUserLayoutBinding.inflate(LayoutInflater.from(context))
        val builder = BottomSheetDialog(requireContext())

        with(sheetBinding) {
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

            // Navigate to detail user with userDomain as arguments
            btnVisit.setOnClickListener {
                findNavController().navigate(
                    MapFragmentDirections.actionMapFragmentToDetailFragment(user)
                )
                builder.dismiss()
            }
        }

        builder.apply {
            setCancelable(true)
            setContentView(sheetBinding.root)
            show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}