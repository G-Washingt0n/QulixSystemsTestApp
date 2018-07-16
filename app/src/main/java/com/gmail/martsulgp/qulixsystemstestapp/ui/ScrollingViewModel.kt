package com.gmail.martsulgp.qulixsystemstestapp.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.databinding.ObservableField
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.gmail.martsulgp.qulixsystemstestapp.R
import com.gmail.martsulgp.qulixsystemstestapp.data.GifsDataRequest
import com.gmail.martsulgp.qulixsystemstestapp.model.entity.Data
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ScrollingViewModel(private val activity: AppCompatActivity, private val gifsData: GifsDataRequest) : ViewModel(), BaseViewModel {

    val adapter: ScrollingAdapter = ScrollingAdapter(listOf(), object : ScrollingAdapter.Callback {
        override fun onReferenceClick(path: String) {
            if (path != "") {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(path))
                activity.startActivity(browserIntent)
            } else Toast.makeText(activity, "Replace with your own action", Toast.LENGTH_LONG).show()
        }

        override fun onItemClicked(item: Data) {
            Toast.makeText(activity, "Replace with your own action", Toast.LENGTH_LONG).show()
        }
    })

    private val api_key = activity.getString(R.string.api_key)
    private final val limit = 10
    private var offset = 0
    private var isLoading = false
    private var isSearchRequest = false
    val request: ObservableField<String> = ObservableField()

    @SuppressLint("StaticFieldLeak")
    lateinit var searchBtn: ImageView

    @SuppressLint("StaticFieldLeak")
    lateinit var progressBar: ProgressBar

    override fun initialise() {
    }

    override fun pause() {
    }

    override fun resume() {
        searchBtn = activity.findViewById(R.id.searchBtn)
        searchBtn.setOnClickListener { view -> onSearchClick() }
        progressBar = activity.findViewById(R.id.progressBar)


        simpleRequest(offset)
    }

    override fun destroy() {
    }

    fun searchRequest(searchRequest: String, offset: Int) {
        isSearchRequest = true
        gifsData.searchGifs(api_key, searchRequest, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showProgressBar(true)
                    isLoading = true;
                }
                .doOnTerminate {
                    showProgressBar(false)
                    isLoading = false
                }
                .subscribe(
                        {
                            if (offset == 0)
                                adapter.items = it.data
                            else
                                adapter.items += it.data
                            adapter.notifyDataSetChanged()
                            this.offset = it.pagination.offset
                        },
                        { Log.d("Error!!", it.message) }
                )
    }

    fun simpleRequest(offset: Int) {
        isSearchRequest = false
        gifsData.getTrending(api_key, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showProgressBar(true)
                    isLoading = true;
                }
                .doOnTerminate {
                    showProgressBar(false)
                    isLoading = false
                }
                .subscribe(
                        {
                            if (offset == 0)
                                adapter.items = it.data
                            else
                                adapter.items += it.data
                            adapter.notifyDataSetChanged()
                            this.offset = it.pagination.offset
                        },
                        { Log.d("Error!!", it.message) }
                )
    }

    fun showProgressBar(b: Boolean) {
        progressBar.visibility = if (b) View.VISIBLE else View.GONE
    }

    fun onSearchClick() {
        if (request.get() == "" || request.get() == null) {
            simpleRequest(offset = 0)
        } else {
            searchRequest(request.get()!!, offset = 0)
        }
    }

    val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = recyclerView.layoutManager?.getChildCount() ?: 0;
            val totalItemCount = recyclerView.layoutManager?.getItemCount() ?: 0;
            val firstVisibleItem = (recyclerView.layoutManager!! as LinearLayoutManager).findFirstVisibleItemPosition()

            if (!isLoading) {
                if ((visibleItemCount + firstVisibleItem) >= totalItemCount
                        && firstVisibleItem >= 0
                        && totalItemCount >= offset) {
                    offset += totalItemCount
                    if (isSearchRequest)
                        searchRequest(request.get()!!, offset)
                    else simpleRequest(offset)
                }
            }
        }


    }

}