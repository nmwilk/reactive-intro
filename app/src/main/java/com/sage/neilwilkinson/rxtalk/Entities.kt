package com.sage.neilwilkinson.rxtalk

/**
 * Entities used in the demo code.
 */
data class Account(val id: String, val name: String = "")

data class Transaction(val amount: Double, val account: Account, val category: Category)

data class Category(val id: String, val name: String = "")
