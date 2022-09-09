package com.balevanciaga.tvapp.custom.data.paginator

class Paginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> List<Item>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) {
    private var currentKey = initialKey
    private var isMakingRequest = false

    suspend fun loadNextItems() {
        if (isMakingRequest) return
        isMakingRequest = true

        onLoadUpdated(true)
        val items = onRequest(currentKey)

        isMakingRequest = false
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }
}