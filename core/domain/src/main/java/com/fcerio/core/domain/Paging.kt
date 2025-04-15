package com.fcerio.core.domain

data class Paging<T>(
    val data: T,
    val meta: Meta
)
