package com.balevanciaga.tvapp.custom.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balevanciaga.tvapp.custom.ext.logAction
import com.balevanciaga.tvapp.custom.ext.logMessage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Suppress("UNCHECKED_CAST")
abstract class BaseViewModel<ViewAction : BaseAction<ViewState>, ViewState> : ViewModel() {

    abstract var viewState: ViewState

    private val actionSubject: MutableSharedFlow<ViewAction> = MutableSharedFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.logMessage()
    }

    init {
        actionSubject
            .onEach { viewAction ->
                logAction(viewAction)
                onActionReceived(viewAction)
            }.launchIn(viewModelScope)
    }

    protected open fun onActionReceived(action: ViewAction) {}

    protected open fun onInitialBind(callback: () -> Unit) {}

    fun postAction(action: ViewAction) {
        viewModelScope.launch {
            viewState = action.updateData(viewState)
            actionSubject.emit(action)
        }
    }

    protected fun execute(
        coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
        call: suspend () -> Unit
    ): Job {
        return viewModelScope.launch(coroutineDispatcher + exceptionHandler) {
            call()
        }
    }

//    protected fun <Result> executeNetworkCall(
//        call: suspend () -> Result,
//        onSuccess: suspend (Result) -> Unit = {},
//        onError: suspend (Error: ErrorResponse?) -> Unit = {},
//        onBlank: suspend () -> Unit = {}
//    ) {
//        execute(coroutineDispatcher = Dispatchers.IO) {
//            try {
//                onSuccess(call())
//            } catch (e: Exception) {
//                if (e is CancellationException) throw e
//                when (e) {
//                    is ResponseException -> onError(e.error)
//                    is GeneralException.BlankResponseException -> onBlank()
//                    is GeneralException.UnknownException -> onError(UnknownError(e.code))
//                    is GeneralException.UnprocessableException -> onError(
//                        UnprocessableError(e.code, errorParser = e.errorParser)
//                    )
//                    is GeneralException -> {
//                        onError(null)
//                    }
//                }
//            }
//        }
//    }
}