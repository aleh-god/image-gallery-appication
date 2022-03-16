package by.godevelopment.imagegalleryapplication.presentation.fullview

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.godevelopment.imagegalleryapplication.R
import by.godevelopment.imagegalleryapplication.databinding.FragmentFullViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FullViewFragment : Fragment() {

    companion object {
        fun newInstance(): FullViewFragment = FullViewFragment()
        private const val KEY_BUNDLE = "pass-src"
    }

    private var _binding: FragmentFullViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FullViewViewModel by viewModels()

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullViewBinding.inflate(inflater, container, false)
        val src = requireArguments().getString(KEY_BUNDLE)
        setupToolbar()
        setupUi(src)
        setupEvent()
        return binding.root
    }

    private fun setupUi(src: String?) {
        if (src != null) {
            Glide.with(binding.root)
                .load(src)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.drawable.image_not_loaded)
                .placeholder(R.drawable.image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        showErrorResponse()
                        return false
                    }
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progress.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.imageFull)
        } else {
            showErrorResponse()
        }
    }

    private fun showErrorResponse() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.emit(getString(R.string.alert_error_loading))
        }
        binding.progress.visibility = View.GONE
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            inflateMenu(R.menu.toolbar_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_back -> {
                        parentFragmentManager.popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect {
                Snackbar.make(binding.root,
                    R.string.alert_error_loading,
                    Snackbar.LENGTH_LONG
                )
                    .setAction(getString(R.string.snackbar_btn_return))
                    { parentFragmentManager.popBackStack() }
                    .show()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}