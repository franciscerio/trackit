package com.fcerio.features.tracks.domain

data class Links(
    val first: String,
    val last: String,
    val next: String,
    val prev: Any
) {

    companion object {

        fun empty(): Links {
            return Links("", "", "", Any())
        }
    }
}
