package com.adrian.thecrypto.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val HIGHEST = "HIGHEST"
    const val LOWEST = "LOWEST"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM crypto ")
        when (filter) {
            HIGHEST -> simpleQuery.append("ORDER BY price DESC")
            LOWEST -> simpleQuery.append("ORDER BY price ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}