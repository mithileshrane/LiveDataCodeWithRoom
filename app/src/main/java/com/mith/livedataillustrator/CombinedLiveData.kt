package com.mith.livedataillustrator

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer

class CombinedLiveData <T, K, S>(source1: LiveData<T>, source2: LiveData<K>, private val combine: (data1: T?, data2: K?) -> S)
    : MediatorLiveData<S>() {

    private var data1: T? = null
    private var data2: K? = null

    init {
        super.addSource(source1) {
            data1 = it
            value = combine(data1, data2)
        }
        super.addSource(source2) {
            data2 = it
            value = combine(data1, data2)
        }
    }

    override fun <T : Any?> addSource(source: LiveData<T>, onChanged: Observer<T>) {
        throw UnsupportedOperationException()
    }

    override fun <T : Any?> removeSource(toRemote: LiveData<T>) {
        throw UnsupportedOperationException()
    }
}