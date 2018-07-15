package com.gmail.martsulgp.qulixsystemstestapp.data

import com.gmail.martsulgp.qulixsystemstestapp.model.entity.GifsData
import com.gmail.martsulgp.qulixsystemstestapp.model.mappers.GifsDataMapper
import io.reactivex.Observable

class GifsDataRequestImpl : GifsDataRequest {

    private val giphyApi by lazy { GiphyApi.create() }

    override fun getTrending(
            api_key: String,
            limit: Int,
            offset: Int
    ): Observable<GifsData> = giphyApi.getTrending(
            api_key = api_key,
            limit = limit,
            offset = offset
    ).map { GifsDataMapper.map(gifsDataResponse = it) }

    override fun searchGifs(
            api_key: String,
            q: String,
            limit: Int,
            offset: Int
    ): Observable<GifsData> = giphyApi.searchGifs(
            api_key = api_key,
            q = q,
            limit = limit,
            offset = offset
    ).map { GifsDataMapper.map(gifsDataResponse = it) }
}