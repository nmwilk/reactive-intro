package com.sage.neilwilkinson.rxtalk

import io.reactivex.Observable
import io.reactivex.functions.Function3

/**
 * Compare typical sync programming with async with respect to a remote API.
 */
class PushPullApiComparison {




    // Traditional serial
    fun syncDataPull(pullSyncCallback: PullSyncCallback) {
        val api = Api()

        api.getAccounts(object : Api.Callback<List<Account>> {

            override fun success(accounts: List<Account>) {
                api.getCategories(object : Api.Callback<List<Category>> {

                    override fun success(categories: List<Category>) {
                        api.getTransactions(object : Api.Callback<List<Transaction>> {

                            override fun success(transactions: List<Transaction>) {
                                pullSyncCallback.complete(Result(accounts, categories, transactions))
                            }
                        })
                    }
                })
            }
        })
    }

    interface PullSyncCallback {
        fun complete(result: Result)
    }

    data class Result(val accounts: List<Account>, val categories: List<Category>, val transactions: List<Transaction>)




















    var allAccounts: List<Account>? = null
    var allTransactions: List<Transaction>? = null
    var allCategories: List<Category>? = null
    var callback: PullSyncCallback? = null

    // Traditional parallel
    fun syncDataPullParallel(pullSyncCallback: PullSyncCallback) {
        callback = pullSyncCallback
        val api = Api()

        api.getAccounts(object : Api.Callback<List<Account>> {

            override fun success(accounts: List<Account>) {
                allAccounts = accounts
                checkComplete()
            }
        })
        api.getCategories(object : Api.Callback<List<Category>> {

            override fun success(categories: List<Category>) {
                allCategories = categories
                checkComplete()
            }
        })

        api.getTransactions(object : Api.Callback<List<Transaction>> {

            override fun success(transactions: List<Transaction>) {
                allTransactions = transactions
                checkComplete()
            }
        })
    }

    fun checkComplete() {
        if (allTransactions != null && allAccounts != null && allCategories != null) {
            callback?.complete(Result(allAccounts!!, allCategories!!, allTransactions!!))
        }
    }
















    // Rx
    fun syncDataPush() {
        val rxApi = RxApi()

        Observable.zip(rxApi.getAccounts(), rxApi.getCategories(), rxApi.getTransactions(),
                Function3<List<Account>, List<Category>, List<Transaction>, Result> {
                    accounts, categories, transactions ->
                    Result(accounts, categories, transactions)
                }).subscribe { result -> println(result) }
    }



}





















// API

open class Api {
    fun getAccounts(callback: Callback<List<Account>>) {
        // do a network request...

        callback.success(listOf(
                Account("1", "Current"),
                Account("2", "Cash")))
    }

    fun getCategories(callback: Callback<List<Category>>) {
        // do a network request...

        callback.success(listOf(
                Category("1", "Sales"),
                Category("2", "Fuel")))
    }

    fun getTransactions(callback: Callback<List<Transaction>>) {
        // do a network request...

        callback.success(listOf(
                Transaction(99.99, Account("1"), Category("1")),
                Transaction(50.00, Account("2"), Category("1")),
                Transaction(20.00, Account("2"), Category("2"))))
    }

    interface Callback<in T> {
        fun success(result: T)
    }
}
























// Rx API

// Note that these should be wrapped in a defer to not execute immediately.

class RxApi {
    val api = Api()

    fun getAccounts(): Observable<List<Account>> {
        return Observable.create {
            api.getAccounts(object : Api.Callback<List<Account>> {
                override fun success(result: List<Account>) {
                    it.onNext(result)
                    it.onComplete()
                }
            })
        }
    }

    fun getCategories(): Observable<List<Category>> {
        return Observable.create({
            api.getCategories(object : Api.Callback<List<Category>> {
                override fun success(result: List<Category>) {
                    it.onNext(result)
                    it.onComplete()
                }
            })
        })
    }

    fun getTransactions(): Observable<List<Transaction>> {
        return Observable.create({
            api.getTransactions(object : Api.Callback<List<Transaction>> {
                override fun success(result: List<Transaction>) {
                    it.onNext(result)
                    it.onComplete()
                }
            })
        })
    }
}