package com.example.tbc_course_14v2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_course_14v2.databinding.MenViewBinding
import com.example.tbc_course_14v2.databinding.WomenViewBinding
import com.example.tbc_course_14v2.models.Content
import com.example.tbc_course_14v2.models.ContentInfo


typealias removeClick = (content: Content) -> Unit
typealias editClick = (content: Content, position: Int) -> Unit


class MyAdapter : ListAdapter<Content, RecyclerView.ViewHolder>(DiffCallBack()) {


    lateinit var removeClick: removeClick
    lateinit var editClick: editClick

    companion object SongViewTypes {
        const val MALE = 0
        const val FEMALE = 1
    }

    override fun getItemViewType(position: Int): Int {
        val gender = getItem(position)
        return when (gender.gender){
            true -> FEMALE
            false -> MALE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            MALE -> MaleViewHolder(
                MenViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
            else -> FemaleViewHolder(
                WomenViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is MaleViewHolder -> holder.bind()
            is FemaleViewHolder -> holder.bind()
        }
    }


    inner class MaleViewHolder(private val binding:MenViewBinding): RecyclerView.ViewHolder(binding.root){

        private lateinit var currentItem: Content
        fun bind(){
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

    inner class FemaleViewHolder(private val binding:WomenViewBinding): RecyclerView.ViewHolder(binding.root){
        private lateinit var currentItem: Content
        fun bind(){
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