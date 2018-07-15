package com.gmail.martsulgp.qulixsystemstestapp.data

import com.gmail.martsulgp.qulixsystemstestapp.model.entity.GifsData
import io.reactivex.Observable

interface GifsDataRequest {
    fun getTrending(
            api_key: String,
            limit: Int,
            offset: Int): Observable<GifsData>

    fun searchGifs(
            api_key: String,
            q: String,
            limit: Int,
            offset: Int): Observable<GifsData>

}