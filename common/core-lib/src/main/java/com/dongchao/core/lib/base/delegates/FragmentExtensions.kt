package com.dongchao.core.lib.base.delegates

import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.dongchao.core.lib.base.BaseFragment
import kotlinx.coroutines.launch
import kotlin.reflect.KClass



public inline fun Fragment.launchStarted(
    crossinline block: suspend () -> Unit
) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}

@MainThread
public inline fun <VM : ViewModel> Fragment.viewModels(
    viewModelClass: KClass<VM>,
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val owner by lazy(LazyThreadSafetyMode.NONE) { ownerProducer() }
    return createViewModelLazy(
        viewModelClass,
        { owner.viewModelStore },
        {
            (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelCreationExtras
                ?: CreationExtras.Empty
        },
        factoryProducer ?: {
            (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory
                ?: defaultViewModelProviderFactory
        })
}

@JvmName("getFragmentViewModels")
@MainThread
public inline fun <VM : ViewModel> Fragment.getFragmentViewModels(
    viewModelClass: Class<VM>,
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
): VM {
    val owner by lazy(LazyThreadSafetyMode.NONE) { ownerProducer() }
    val factoryProducer =
        (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory
            ?: defaultViewModelProviderFactory
    val extrasProducer =
        (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelCreationExtras
            ?: CreationExtras.Empty

    return ViewModelProvider(
        owner.viewModelStore,
        factoryProducer,
        extrasProducer
    )[viewModelClass]
}



