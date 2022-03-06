package com.faqrulans.core.ui.developer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faqrulans.core.R
import com.faqrulans.core.databinding.ItemListDeveloperBinding

class DeveloperAdapter : RecyclerView.Adapter<DeveloperAdapter.ListViewHolder>() {

    private var listData = ArrayList<DeveloperUI>()
    var onItemClick: ((DeveloperUI) -> Unit)? = null

    fun setData(newListData: List<DeveloperUI>?) {
        if (newListData == null) return
        when {
            listData.isEmpty() -> {
                listData.addAll(newListData)
                notifyItemRangeInserted(0, newListData.size)
            }
            newListData.size == listData.size -> {
                newListData.forEachIndexed { index, developerUI ->
                    if (developerUI.isFavorite != listData[index].isFavorite) {
                        listData[index] = developerUI
                        notifyItemChanged(index)
                    }
                }
            }
            else -> {
                listData.clear()
                notifyItemRangeRemoved(0, listData.size)
                listData.addAll(newListData)
                notifyItemRangeInserted(0, newListData.size)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_developer, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListDeveloperBinding.bind(itemView)
        fun bind(data: DeveloperUI) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageBackground)
                    .centerCrop()
                    .into(ivItemListDeveloper)
                txtItemListDeveloperName.text = data.name
                txtItemListGamesCount.text = itemView.context.getString(
                    R.string.developer_games_count, data.gamesCount.toString()
                )
                constraintItemListDeveloper.setBackgroundColor(
                    ContextCompat.getColor(binding.root.context, data.backgroundColor)
                )
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }
}
