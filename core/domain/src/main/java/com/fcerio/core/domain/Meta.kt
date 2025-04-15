package com.fcerio.core.domain

data class Meta(
    val currentPage: Int,
    val from: Int,
    val lastPage: Int,
    val perPage: Int,
    val to: Int,
    val total: Int
) {

    companion object {

        fun empty(): Meta {
            return Meta(0, 0, 0, 0, 0, 0)
        }
    }
}
