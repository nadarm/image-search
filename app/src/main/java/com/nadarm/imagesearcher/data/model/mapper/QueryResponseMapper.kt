package com.nadarm.imagesearcher.data.model.mapper

import com.nadarm.imagesearcher.data.model.response.Document
import com.nadarm.imagesearcher.data.model.response.ImageSearchResponse
import com.nadarm.imagesearcher.data.model.response.Meta
import com.nadarm.imagesearcher.domain.model.ImageDocument
import com.nadarm.imagesearcher.domain.model.QueryResponse
import com.nadarm.imagesearcher.domain.model.SearchMeta
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class QueryResponseMapper @Inject constructor() {

    fun mapFromData(query: String, documents: List<Document>, page: Int): List<ImageDocument> {
        var index = 0
        return documents.map {
            ImageDocument(
                query,
                it.thumbnailUrl,
                it.imageUrl,
                it.docUrl,
                it.displaySiteName,
                page,
                index++
            )
        }
    }

    fun mapFromData(query: String, page:Int, meta: Meta): SearchMeta {
        return SearchMeta(
            query,
            page,
            meta.totalCount,
            meta.totalCount,
            meta.isEnd
        )
    }


    fun mapFromData(query: String, imageSearchResponse: ImageSearchResponse, page: Int): QueryResponse {
        return QueryResponse(
            this.mapFromData(query, page, imageSearchResponse.meta),
            this.mapFromData(query, imageSearchResponse.documents, page)
        )
    }

}