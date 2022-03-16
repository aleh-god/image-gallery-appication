package by.godevelopment.imagegalleryapplication.presentation.tableview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.godevelopment.imagegalleryapplication.R
import by.godevelopment.imagegalleryapplication.databinding.ItemRvBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class TableAdapter(
    private val imagesList: List<String>,
    private val onClick: (String) -> Any
) : RecyclerView.Adapter<TableAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableAdapter.ItemViewHolder {
        return ItemViewHolder(ItemRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TableAdapter.ItemViewHolder, position: Int) {
        holder.binding.apply {
            val src = imagesList[position]
            Glide.with(root)
                .load(src)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.drawable.image_not_loaded)
                .placeholder(R.drawable.image)
                .into(image)

            root.setOnClickListener {
                onClick.invoke(src)
            }
        }
    }

    override fun getItemCount(): Int = imagesList.size
}