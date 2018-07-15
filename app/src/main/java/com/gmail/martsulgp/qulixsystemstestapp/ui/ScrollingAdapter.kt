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
import com.squareup.picasso.Picasso

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

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(item: Data) {
            itemTitle.text = item.title
            userName.text = item.user.userRealName
            userReference.text = item.user.profileUrl
            Picasso.with(userAvatar.context)
                    .load(item.user.avatar)
                    .into(userAvatar)
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