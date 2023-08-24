package com.loc.cokopaging.paging


data class PagingState<K, T>(
    var currentKey: K?,
    var endPaging: Boolean = false,
    var data: List<T> = emptyList(),
    val isLoading: Boolean = false,
    val error: Exception? = null
)



