package com.fcerio.core.common.android.base

class Event<out O>(private val value: O?) {

    private var readInViewStateId: Long? = null

    fun get(uiStateWithId: UiStateWithId): O? {
        return when (readInViewStateId) {
            null -> {
                readInViewStateId = uiStateWithId.uiStateId
                value
            }
            else -> null
        }
    }

    override fun toString(): String {
        return "Event(value=$value, readInViewStateId=$readInViewStateId)"
    }

    @Suppress("RedundantIf")
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }

        other as Event<*>

        if (value != other.value) {
            return false
        }
        if (readInViewStateId != other.readInViewStateId) {
            return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = value?.hashCode() ?: 0
        result = 31 * result + (readInViewStateId?.hashCode() ?: 0)
        return result
    }

    companion object {
        fun <T> empty(): Event<T> = Event(null).apply {
            readInViewStateId = -1L
        }
    }
}
