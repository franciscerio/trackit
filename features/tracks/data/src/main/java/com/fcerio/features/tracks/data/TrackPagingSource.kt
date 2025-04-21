package com.fcerio.features.tracks.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fcerio.core.common.PAGE_LIMIT
import com.fcerio.features.tracks.domain.Track
import com.fcerio.features.tracks.network.models.payload.builder.TrackFilter
import javax.inject.Inject

class TrackPagingSource @Inject constructor(
    private val trackRepository: TrackRepository,
    private val query: String
) : PagingSource<Int, Track>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Track> {
        val nextPageNumber = params.key ?: 1

        val response = trackRepository.loadTracks(
            TrackFilter.Builder().page(nextPageNumber).limit(10).search(query)
        )

        return if (response.data.size >= PAGE_LIMIT) {
            LoadResult.Page(
                data = response.data,
                prevKey = null, // Only paging forward.
                nextKey = response.meta.currentPage + 1
            )
        } else {
            LoadResult.Error(
                Throwable("No more pagination! currentPage $nextPageNumber")
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
