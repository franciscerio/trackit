package com.fcerio.features.tracks.domain

data class Link(
    val active: Boolean,
    val label: String,
    val url: String
) {

    companion object {

        fun empty(): Link {
            return Link(false, "", "")
        }
    }
}
