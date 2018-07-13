package com.gmail.martsulgp.qulixsystemstestapp.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gmail.martsulgp.qulixsystemstestapp.BR
import com.gmail.martsulgp.qulixsystemstestapp.R
import com.gmail.martsulgp.qulixsystemstestapp.data.GifsData
import kotlinx.android.synthetic.main.activity_scrolling.*
import org.koin.android.ext.android.inject

class ScrollingActivity : AppCompatActivity() {

    private val gifsData : GifsData by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val viewModel = ScrollingViewModel(gifsData)

        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_scrolling)
        binding.setVariable(BR._all,viewModel)


    }



}
