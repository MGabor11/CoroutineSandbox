package com.mgabor.coroutine

import android.os.Looper
import androidx.annotation.Keep
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

@Keep
internal class LoggingHandler : AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler {
    override fun handleException(context: CoroutineContext, exception: Throwable) {

        // Since we don't want to crash and Coroutines will call the current thread's handler, we
        // install a noop handler and then reinstall the existing one once coroutines calls the new
        // handler.
        Thread.currentThread().apply {
            // _Do_ crash the main thread to ensure we're not left in a bad state
            if (isMain) return@apply

            val removed = uncaughtExceptionHandler
            uncaughtExceptionHandler = if (removed == null) {
                ResettingHandler
            } else {
                Thread.UncaughtExceptionHandler { t, _ ->
                    t.uncaughtExceptionHandler = removed
                }
            }
        }
    }

    private object ResettingHandler : Thread.UncaughtExceptionHandler {
        override fun uncaughtException(t: Thread, e: Throwable) {
            t.uncaughtExceptionHandler = null
        }
    }
}

val Thread.isMain get() = this === Looper.getMainLooper().thread