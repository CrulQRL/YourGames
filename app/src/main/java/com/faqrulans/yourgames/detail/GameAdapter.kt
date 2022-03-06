package com.faqrulans.yourgames.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.databinding.ItemListGameBinding

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

    private var listData = ArrayList<GameUI>()

    fun setData(newListData: List<GameUI>?) {
        if (newListData == null) return
        if (listData.isEmpty()) {
            listData.addAll(newListData)
            notifyItemRangeInserted(0, newListData.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_game, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListGameBinding.bind(itemView)
        fun bind(data: GameUI) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.backgroundImage)
                    .centerCrop()
                    .into(ivItemListGame)
                txtItemListGameName.text = data.name
                txtItemListGameDate.text = data.released
                txtItemListGameRate.text = data.rating
            }
        }
    }
}
