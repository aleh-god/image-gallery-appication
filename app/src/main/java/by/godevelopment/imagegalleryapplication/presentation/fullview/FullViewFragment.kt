package by.godevelopment.imagegalleryapplication.presentation.fullview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.godevelopment.imagegalleryapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullViewFragment : Fragment() {

    companion object {
        fun newInstance() = FullViewFragment()
    }

    private lateinit var viewModel: FullViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_full_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FullViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}