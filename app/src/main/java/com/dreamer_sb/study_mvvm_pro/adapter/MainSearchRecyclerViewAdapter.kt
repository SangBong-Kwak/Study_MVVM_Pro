package com.dreamer_sb.study_mvvm_pro.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dreamer_sb.study_mvvm_pro.R
import com.dreamer_sb.study_mvvm_pro.databinding.ItemMainImageBinding
import com.squareup.picasso.Picasso

class MainSearchRecyclerViewAdapter : RecyclerView.Adapter<MainSearchRecyclerViewAdapter.ViewHolder>(){

    private val imageItemList = ArrayList<ImageItem>()
    data class ImageItem(var imageUrl:String, var documentUrl:String)

    inner class ViewHolder(private val binding : ItemMainImageBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind(item : ImageItem){
            itemView.run {
                Picasso.with(context).load(item.imageUrl).placeholder(R.drawable.ic_baseline_search_24).into(binding.itemMainImageView)
                binding.itemMainImageView.setOnClickListener{
                    ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse(item.documentUrl)), null)
                }
            }
        }
    }

    override fun getItemCount(): Int =imageItemList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemMainImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageItemList[position])
    }

    fun clearImageItem(){
        imageItemList.clear()
    }

    fun addImageItem(imageUrl: String, documentUrl : String){
        imageItemList.add(ImageItem(imageUrl, documentUrl))
    }
}
