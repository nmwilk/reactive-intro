package com.sage.neilwilkinson.rxtalk

/**
 * Created by neil.wilkinson on 18/01/2017.
 */
data class Account(val id: String, val name: String = "")

data class Transaction(val amount: Double, val account: Account, val category: Category)

data class Category(val id: String, val name: String = "")
