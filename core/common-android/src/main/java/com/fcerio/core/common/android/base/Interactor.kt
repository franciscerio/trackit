package com.fcerio.core.common.android.base

import kotlinx.coroutines.flow.Flow

interface Interactor<in Action, out Result> {
    fun actionProcessor(actions: Flow<Action>): Flow<Result>
}
