package com.sage.neilwilkinson.rxtalk

import com.sage.neilwilkinson.rxtalk.PushPullApiComparison.*
import io.reactivex.Observable
import io.reactivex.functions.Function3
import java.util.*
import kotlin.collections.find

/**
 * Compare typical sync programming with async.
 */
class PushApiDb {


    // Rx
    fun syncDataIncompletePush(): Observable<Result> {
        val rxApi = RxApi()
        val rxDatastore = RxDataStore()

        return Observable.zip(rxDatastore.getAccounts(), rxDatastore.getCategories(), rxApi.getTransactions(),
                Function3<List<Account>, List<Category>, List<Transaction>, Result> {
                    accounts: List<Account>, categories: List<Category>, transactions: List<Transaction> ->
                    processTransactions(accounts, categories, transactions)
                })
    }


    private fun processTransactions(accounts: List<Account>,
                                    categories: List<Category>,
                                    transactions: List<Transaction>): PushPullApiComparison.Result {
        val completeTransactions = ArrayList<Transaction>()
        transactions.forEach { transaction ->

            val fullAccount = accounts.find { it.id == transaction.account.id }!!
            val fullCategory = categories.find { it.id == transaction.category.id }!!

            completeTransactions.add(transaction.copy(account = fullAccount, category = fullCategory))
        }

        return Result(accounts, categories, completeTransactions)
    }
}


class RxDataStore {
    fun getAccounts(): Observable<List<Account>> {
        return Observable.just(listOf(
                Account("1", "Current"),
                Account("2", "Cash")))
    }

    fun getCategories(): Observable<List<Category>> {
        return Observable.just(listOf(
                Category("1", "Sales"),
                Category("2", "Fuel")))
    }
}





















