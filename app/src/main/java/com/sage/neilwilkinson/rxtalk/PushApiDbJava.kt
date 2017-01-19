package com.sage.neilwilkinson.rxtalk

import io.reactivex.Observable
import io.reactivex.functions.Function3

/**
 * Created by neil.wilkinson on 19/01/2017.
 */

class PushApiDbJava {
    fun syncDataPush() {
        val rxApi = RxApi()

        Observable.zip(rxApi.getAccounts(), rxApi.getCategories(), rxApi.getTransactions(),
                Function3<List<Account>, List<Category>, List<Transaction>, PushPullApiComparison.Result> {
                    accounts, categories, transactions -> PushPullApiComparison.Result(accounts, categories, transactions)
                })
    }
}
