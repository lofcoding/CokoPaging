package com.loc.cokopaging.paging

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


abstract class RemotePagingSource<K, T>(
    private val initialKey: K
) {

    private val _pagingState =
        MutableStateFlow<PagingState<K, T>>(PagingState(currentKey = initialKey))

    val pagingState = _pagingState.asStateFlow()
    abstract suspend fun load()
    fun reset() {
        _pagingState.update {
            it.copy(
                currentKey = initialKey
            )
        }
    }

    fun updatePage(newPage: K?) {
        if (newPage == null) {
            _pagingState.update {
                it.copy(
                    endPaging = true
                )
            }
        } else {
            _pagingState.update {
                it.copy(
                    currentKey = newPage
                )
            }
        }
    }

    fun updateSuccess(data: List<T>) {
        _pagingState.update {
            it.copy(
                data = it.data + data
            )
        }
    }

    fun updateError(error: Exception) {
        _pagingState.update {
            it.copy(
                error = error
            )
        }
    }

    fun updateLoading(isLoading: Boolean) {
        _pagingState.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }
}
