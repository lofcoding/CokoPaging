package com.loc.cokopaging.paging


class Pager<K, T>(
    remotePagingSourceFactory: () -> RemotePagingSource<K, T>
) {
    private val remotePagingSource = remotePagingSourceFactory()

    val state = remotePagingSource.pagingState
    suspend fun load() {
        val state = remotePagingSource.pagingState.value
        if (!state.endPaging && !state.isLoading) { // this condition to make sure not to load new data while loading = true or if the paging was ended
            remotePagingSource.load()
        }
    }

    suspend fun reset() {
        remotePagingSource.reset()
    }

}