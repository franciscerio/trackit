package com.fcerio.core.common.android.base

import java.util.concurrent.atomic.AtomicLong

interface UiState

interface UiStateWithId : UiState {
    val uiStateId: Long

    fun resetId()
}

class UiStateWithIdDelegate : UiStateWithId {

    companion object {

        private val lastUiStateId = AtomicLong(1L)

        fun getNextId() = lastUiStateId.getAndIncrement()
    }

    override var uiStateId: Long = getNextId()
        private set

    override fun resetId() {
        uiStateId = getNextId()
    }
}
