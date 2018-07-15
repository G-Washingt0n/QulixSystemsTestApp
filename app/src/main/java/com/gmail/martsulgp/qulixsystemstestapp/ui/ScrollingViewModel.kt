package com.gmail.martsulgp.qulixsystemstestapp.ui

import android.databinding.ObservableField
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gmail.martsulgp.qulixsystemstestapp.R
import com.gmail.martsulgp.qulixsystemstestapp.data.GifsDataRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ScrollingViewModel(private val activity: AppCompatActivity, private val adapter: ScrollingAdapter, private val gifsData: GifsDataRequest) : BaseViewModel {

    private val api_key = activity.getString(R.string.api_key)
    private final val limit = 10
    private var offset = 0

    lateinit var request: ObservableField<String>

    override fun initialise() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pause() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resume() {
        simpleRequest(offset)
//        searchRequest("",offset)
    }

    override fun destroy() {
    }

    fun searchRequest(searchRequest: String, offset: Int) {
        gifsData.searchGifs(api_key, searchRequest, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgressBar(true) }
                .doOnTerminate { showProgressBar(false) }
                .subscribe(
                        {
                            adapter.items = it.data
                            adapter.notifyDataSetChanged()
                            Log.d("Success!!", it.data[0].title)
                        },
                        { Log.d("Error!!", it.message) }
                )
    }

    fun simpleRequest(offset: Int) {
        gifsData.getTrending(api_key, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgressBar(true) }
                .doOnTerminate { showProgressBar(false) }
                .subscribe(
                        {
                            adapter.items = it.data
                            adapter.notifyDataSetChanged()
                            Log.d("Success!!", it.pagination.totalCount.toString())
                        },
                        { Log.d("Error!!", it.message) }
                )
    }

    fun showProgressBar(b: Boolean) {

    }
}