package com.sage.neilwilkinson.rxtalk

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable
import io.reactivex.functions.Function3


/**
 * Click, Db, and Api Observable chain.
 */
class EventChain {

    fun connectButton(button: Button): Observable<PushPullApiComparison.Result> {
        val api = RxApi()
        val datastore = RxDataStore()
        return ButtonClickObservable(button)
                .flatMap {
                    Observable.zip(api.getTransactions(), api.getCategories(), datastore.getAccounts(),
                    Function3<List<Transaction>, List<Category>, List<Account>, PushPullApiComparison.Result> {
                        transactions, categories, accounts ->
                        PushPullApiComparison.Result(accounts, categories, transactions)
                    })
                }
    }
}


class Button {
    var clickListener: ClickListener? = null

    fun click() {
        clickListener?.onClick()
    }

    interface ClickListener {
        fun onClick()
    }
}

class ButtonClickObservable(val button: Button) : Observable<Any>() {
    override fun subscribeActual(observer: Observer<in Any>) {
        val listener = RxListener(button, observer)
        observer.onSubscribe(listener)
        button.clickListener = listener
    }


    inner class RxListener(val button: Button, val observer: Observer<in Any>) : MainThreadDisposable(), Button.ClickListener {
        override fun onClick() {
            observer.onNext(this)
        }

        override fun onDispose() {
            button.clickListener = null
        }
    }
}


