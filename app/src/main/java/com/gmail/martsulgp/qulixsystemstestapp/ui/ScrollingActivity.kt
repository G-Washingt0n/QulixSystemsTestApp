package com.gmail.martsulgp.qulixsystemstestapp.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.gmail.martsulgp.qulixsystemstestapp.R
import com.gmail.martsulgp.qulixsystemstestapp.data.GifsDataRequest
import com.gmail.martsulgp.qulixsystemstestapp.databinding.ActivityScrollingBinding
import com.gmail.martsulgp.qulixsystemstestapp.model.entity.Data
import kotlinx.android.synthetic.main.activity_scrolling.*
import org.koin.android.ext.android.inject


class ScrollingActivity : BaseActivity() {
    private val gifsData: GifsDataRequest by inject()
    private lateinit var linearLayoutManager: LinearLayoutManager
    //    @BindView(R.id.content)
//    lateinit var viewGroup: ViewGroup
//
//    init {
//        ButterKnife.bind(this, viewGroup)
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { viewGroup ->
            Snackbar.make(viewGroup, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val adapter = ScrollingAdapter(listOf(), object : ScrollingAdapter.Callback {
            override fun onReferenceClick(path: String) {
                if (path != "") {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(path))
                    startActivity(browserIntent)
                } else Toast.makeText(this@ScrollingActivity, "Replace with your own action", Toast.LENGTH_LONG).show()
            }

            override fun onItemClicked(item: Data) {
//                    if (item.user.profileUrl != "") {
//                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.user.profileUrl))
//                        startActivity(browserIntent)
//                    } else
                Toast.makeText(this@ScrollingActivity, "Replace with your own action", Toast.LENGTH_LONG).show()

//                Snackbar.make( , "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            }
        })

        val viewModel = ScrollingViewModel(this, adapter, gifsData)
        this.viewModel = viewModel
        val binding: ActivityScrollingBinding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = adapter


    }


}

