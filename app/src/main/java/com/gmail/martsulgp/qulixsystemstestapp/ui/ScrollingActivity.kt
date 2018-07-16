package com.gmail.martsulgp.qulixsystemstestapp.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.gmail.martsulgp.qulixsystemstestapp.R
import com.gmail.martsulgp.qulixsystemstestapp.data.GifsDataRequest
import com.gmail.martsulgp.qulixsystemstestapp.databinding.ActivityScrollingBinding
import org.koin.android.ext.android.inject


class ScrollingActivity : BaseActivity() {
    private val gifsData: GifsDataRequest by inject()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ScrollingViewModel(this, gifsData)
        this.viewModel = viewModel
        val binding = DataBindingUtil.setContentView<ActivityScrollingBinding>(this, R.layout.activity_scrolling)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = viewModel.adapter
        binding.recyclerView.addOnScrollListener(viewModel.onScrollListener)
        binding.viewModel = viewModel

    }
}

