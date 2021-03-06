package com.nadarm.imagesearcher.data.local

import android.database.Cursor
import com.nadarm.imagesearcher.data.model.SearchQuery
import com.nadarm.imagesearcher.data.repository.SearchQueryDataSource
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchQueryLocalDataSource @Inject constructor(
    private val dao: SearchQueryDao
) : SearchQueryDataSource.Local {

    override fun getSuggestions(query: String): Cursor {
        return this.dao.getQuery(query)
    }

    override fun addSearchedQuery(query: String): Completable {
        val searchQuery = SearchQuery(query, System.currentTimeMillis())
        return this.dao.insertQuery(searchQuery)
    }
}
