package com.balevanciaga.tvapp.custom.ext

import androidx.lifecycle.ViewModel

fun ViewModel.tag(): String = this::class.java.simpleName