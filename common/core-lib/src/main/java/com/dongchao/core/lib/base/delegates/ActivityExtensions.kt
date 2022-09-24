package com.dongchao.core.lib.base.delegates

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.ViewBindingProperty
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

import kotlin.reflect.KClass


/**
 * activity 活跃的时候才能收到数据流
 */
public inline fun ComponentActivity.launchStarted(
    crossinline block: suspend () -> Unit
) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}

@MainThread
public inline fun <VM : ViewModel> ComponentActivity.viewModel(
    viewModelClass: KClass<VM>,
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    return ViewModelLazy(viewModelClass, { viewModelStore }, factoryPromise)
}

//inline fun <A : ComponentActivity, T : ViewBinding> ComponentActivity.viewBinding(
//    crossinline vbFactory: (View) -> T,
//    crossinline viewProvider: (A) -> View = ::findRootView
//): ViewBindingDelegate<A, T> {
//    return viewBinding(vbFactory, viewProvider)
//}

//internal val EMPTY_VB_CALLBACK: (ViewBinding) -> Unit = { _ -> }
//
//@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
//fun <T : ViewBinding> emptyVbCallback():(T) -> Unit {
//    return EMPTY_VB_CALLBACK
//}
//
//@JvmName("viewBindingActivity")
//public inline fun <A : ComponentActivity, T : ViewBinding> ComponentActivity.viewBinding(
//    crossinline vbFactory: (View) -> T,
//    crossinline viewProvider: (A) -> View = ::findRootView
//): ViewBindingDelegate<A, T> {
//    return viewBinding(emptyVbCallback(), vbFactory, viewProvider)
//}
//
//@JvmName("viewBindingActivityWithCallbacks")
//public inline fun <A : ComponentActivity, T : ViewBinding> ComponentActivity.viewBinding(
//    noinline onViewDestroyed: (T) -> Unit = {},
//    crossinline vbFactory: (View) -> T,
//    crossinline viewProvider: (A) -> View = ::findRootView
//): ViewBindingDelegate<A, T> {
//    return viewBinding(onViewDestroyed) { activity -> vbFactory(viewProvider(activity)) }
//}
//
//@JvmName("viewBindingActivityWithCallbacks")
//public fun <A : ComponentActivity, VB : ViewBinding> ComponentActivity.viewBinding(
//    onViewDestroyed: (VB) -> Unit = {},
//    viewBinder: (A) -> VB
//): ViewBindingDelegate<A, VB> {
//    return ActivityViewBindingDelegate(onViewDestroyed, viewBinder = viewBinder)
//}
