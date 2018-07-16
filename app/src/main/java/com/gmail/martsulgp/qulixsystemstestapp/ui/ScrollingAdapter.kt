package com.gmail.martsulgp.qulixsystemstestapp.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.gmail.martsulgp.qulixsystemstestapp.R
import com.gmail.martsulgp.qulixsystemstestapp.model.entity.Data
import com.gmail.martsulgp.qulixsystemstestapp.utils.GlideApp
import com.squareup.picasso.Picasso
import pl.droidsonroids.gif.GifImageView


class ScrollingAdapter(var items: List<Data>, var callback: Callback) : RecyclerView.Adapter<ScrollingAdapter.ScrollingHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ScrollingHolder(LayoutInflater.from(p0.context).inflate(R.layout.scrolling_item, p0, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ScrollingHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ScrollingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.userAvatar)
        lateinit var userAvatar: ImageView
        @BindView(R.id.userName)
        lateinit var userName: TextView
        @BindView(R.id.userReference)
        lateinit var userReference: TextView
        @BindView(R.id.itemTitle)
        lateinit var itemTitle: TextView
        //  @BindView(R.id.gifImage) //conflict between 3rd party library $ ButterKnife
        var gifImage: GifImageView = itemView.findViewById(R.id.gifImage)


        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(item: Data) {
            itemTitle.text = item.title
            userName.text = item.user.userRealName
            userReference.text = item.user.profileUrl
            if (item.user.avatar != "") {
                Picasso.with(userAvatar.context)
                        .load(item.user.avatar)
                        .into(userAvatar)
            }
            if (item.image.smallImage.imageUrl != "") {
                GlideApp.with(gifImage.context)
                        .asGif()
                        .load(item.image.smallImage.imageUrl)
                        .into(gifImage)
//                Picasso.with(gifImage.context)
//                        .load(item.image.smallImage.imageUrl)
//                        .into(gifImage)
            }
            userReference.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    callback.onReferenceClick(items[adapterPosition].user.profileUrl)
                }
            }

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    callback.onItemClicked(items[adapterPosition])
                }
            }
        }

    }

    interface Callback {
        fun onItemClicked(item: Data)
        fun onReferenceClick(path: String)
    }
}