package com.sage.neilwilkinson.rxtalk

import com.sage.neilwilkinson.rxtalk.PushPullApiComparison.*
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import java.util.*
import kotlin.collections.find

/**
 * Async code using an API and DB.
 */
class PushApiDb {


    // Rx
    fun syncDataIncompletePush(): Observable<Result> {
        val rxApi = RxApi()
        val rxDatastore = RxDatabase()

        rxDatastore.clear()

        return Observable.zip(rxApi.getAccounts(), rxApi.getCategories(),
                BiFunction<List<Account>, List<Category>, Response> {
                    accounts: List<Account>, categories: List<Category> ->
                    Response(accounts, categories)
                })
                .flatMap {
                    response ->
                    rxDatastore.saveAccounts(response.accounts)
                            .flatMap { rxDatastore.saveCategories(response.categories) }
                }
                .flatMap {
                    Observable.zip(rxDatastore.loadAccounts(), rxDatastore.loadCategories(), rxApi.getTransactions(),
                            Function3<List<Account>, List<Category>, List<Transaction>, Result> {
                                accounts: List<Account>, categories: List<Category>, transactions: List<Transaction> ->
                                processTransactions(accounts, categories, transactions)
                            })
                }
    }


    inner class Response(val accounts: List<Account>, val categories: List<Category>)

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






















