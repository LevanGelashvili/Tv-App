package com.balevanciaga.tvapp.custom.ext

import android.view.Window
import androidx.core.view.WindowCompat

@Suppress("DEPRECATION")
fun Window.drawUnderStatusBar() {
    WindowCompat.setDecorFitsSystemWindows(this, false)
}