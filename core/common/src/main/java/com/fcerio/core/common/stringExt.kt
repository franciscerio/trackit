package com.fcerio.core.common

fun String.toBearerToken(): String {
    return "Bearer $this"
}