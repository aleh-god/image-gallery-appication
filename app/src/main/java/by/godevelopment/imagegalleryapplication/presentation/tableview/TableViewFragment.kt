package by.godevelopment.imagegalleryapplication.presentation.tableview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.godevelopment.imagegalleryapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableViewFragment : Fragment() {

    companion object {
        fun newInstance() = TableViewFragment()
    }

    private lateinit var viewModel: TableViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_table_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TableViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}