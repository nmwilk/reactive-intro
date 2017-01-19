package com.sage.neilwilkinson.rxtalk

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

/**
 * Created by neil.wilkinson on 18/01/2017.
 */
class Database {
    var accounts: List<Account> = listOf()
    var transactions: List<Transaction> = listOf()

    fun saveAccounts(accounts: List<Account>): Flowable<List<Account>> {
        return Flowable.create ({
            // save the objects and return result into new List
            this.accounts = accounts

            it.onNext(this.accounts)
            it.onComplete()

        }, BackpressureStrategy.BUFFER)
    }

    fun saveTransactions(transactions: List<Transaction>): Flowable<List<Transaction>> {
        return Flowable.create({
            // save the objects and return result into new List
            this.transactions = transactions

            it.onNext(this.transactions)
            it.onComplete()

        }, BackpressureStrategy.BUFFER)
    }

    fun loadAccounts(): Flowable<List<Account>> {
        return Flowable.create({
            it.onNext(accounts)
            it.onComplete()

        }, BackpressureStrategy.BUFFER)
    }

    fun loadTransactions(): Flowable<List<Transaction>> {
        return Flowable.create({
            it.onNext(transactions)
            it.onComplete()

        }, BackpressureStrategy.BUFFER)
    }
}