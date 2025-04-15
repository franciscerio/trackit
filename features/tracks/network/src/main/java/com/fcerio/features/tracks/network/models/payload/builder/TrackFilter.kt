package com.fcerio.features.tracks.network.models.payload.builder

class TrackFilter(
    private val search: String? = null,
    private val isFavorite: Int? = null,
    private val limit: Int? = null,
    private val page: Int? = null
) {

    companion object {
        fun default(): TrackFilter.Builder {
            return TrackFilter.Builder().limit(10).page(1)
        }
    }

    private constructor(builder: Builder) : this(
        builder.search,
        builder.isFavorite,
        builder.limit,
        builder.page
    )

    fun toMap(): Map<String, List<String>> {
        val map = mutableMapOf<String, List<String>>()
        search?.let {
            map["search"] = listOf(it)
        }

        isFavorite?.let {
            map["filter[only_favorites]"] = listOf(it.toString())
        }

        limit?.let {
            map["per_page"] = listOf(it.toString())
        }

        page?.let {
            map["page"] = listOf(it.toString())
        }
        return map
    }

    class Builder {

        var search: String? = null
        var isFavorite: Int? = null
        var limit: Int? = 10
        var page: Int? = null

        fun search(search: String) = apply {
            this.search = search
        }

        fun favorite(isFavorite: Boolean) = apply {
            this.isFavorite = if (isFavorite) 1 else 0
        }

        fun limit(limit: Int) = apply {
            this.limit = limit
        }

        fun page(page: Int) = apply {
            this.page = page
        }

        fun build() = TrackFilter(this)
    }
}
