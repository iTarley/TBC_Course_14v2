package com.example.tbc_course_14v2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_course_14v2.R
import com.example.tbc_course_14v2.databinding.GridViewBinding
import com.example.tbc_course_14v2.models.Content
import com.example.tbc_course_14v2.models.ContentInfo


typealias removeClick = (content: Content) -> Unit
typealias editClick = (content: Content, position: Int) -> Unit


class MyAdapter : ListAdapter<Content, MyAdapter.ViewHolder>(DiffCallBack()) {


    lateinit var removeClick: removeClick
    lateinit var editClick: editClick


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder =
        ViewHolder(
            GridViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.bind()

    }

    inner class ViewHolder(private val binding: GridViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentItem: Content
        fun bind() {
            currentItem = getItem(adapterPosition)
            submitList(ContentInfo.logoContent.toList())
            binding.apply {
                nameView.text = currentItem.name
                descriptionView.text = currentItem.description

                imageButtonRemove.setOnClickListener {
                    removeClick(
                        currentItem
                    )
                }
                imageButtonEdit.setOnClickListener {
                    editClick(
                        currentItem, adapterPosition
                    )
                }


            }

        }

    }

    class DiffCallBack : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem == newItem
        }
    }
}