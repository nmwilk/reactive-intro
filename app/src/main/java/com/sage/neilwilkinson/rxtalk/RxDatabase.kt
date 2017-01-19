package com.sage.neilwilkinson.rxtalk

import io.reactivex.Observable


/**
 * Created by neil.wilkinson on 18/01/2017.
 */
class RxDatabase {
    var transactions: List<Transaction> = listOf()
    var accounts: List<Account> = listOf(
            Account("1", "Current"),
            Account("2", "Cash"))
    var categories: List<Category> = listOf(
            Category("1", "Sales"),
            Category("2", "Fuel"))

    fun clear() {
        accounts = listOf()
        categories = listOf()
    }

    fun saveAccounts(accounts: List<Account>): Observable<List<Account>> {
        return Observable.create({
            // save the objects and return result into new List
            this.accounts = accounts

            it.onNext(this.accounts)
            it.onComplete()

        })
    }

    fun saveTransactions(transactions: List<Transaction>): Observable<List<Transaction>> {
        return Observable.create({
            // save the objects and return result into new List
            this.transactions = transactions

            it.onNext(this.transactions)
            it.onComplete()

        })
    }

    fun saveCategories(categories: List<Category>): Observable<List<Category>> {
        return Observable.create({
            // save the objects and return result into new List
            this.categories = categories

            it.onNext(this.categories)
            it.onComplete()

        })
    }

    fun loadAccounts(): Observable<List<Account>> {
        return Observable.create({
            it.onNext(accounts)
            it.onComplete()

        })
    }

    fun loadCategories(): Observable<List<Category>> {
        return Observable.create({
            it.onNext(categories)
            it.onComplete()

        })
    }

    fun loadTransactions(): Observable<List<Transaction>> {
        return Observable.create({
            it.onNext(transactions)
            it.onComplete()

        })
    }
}