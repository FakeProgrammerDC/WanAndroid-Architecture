//package com.dongchao.core.lib.base.viewbinding
//
//import android.os.Handler
//import android.os.Looper
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.annotation.CallSuper
//import androidx.annotation.MainThread
//import androidx.annotation.RestrictTo
//import androidx.lifecycle.*
//import androidx.viewbinding.ViewBinding
//
//import kotlin.properties.ReadOnlyProperty
//import kotlin.reflect.KProperty
//
//private const val LIFECYCLE_VIEW_BINDING_DELEGATE: String = "LifecycleViewBindingProperty"
//
//interface ViewBindingDelegate<in R : Any, out VB : ViewBinding> : ReadOnlyProperty<R, VB> {
//
//    /**
//     *  回收内存防止泄露
//     */
//    @MainThread
//    fun clear()
//}
//
//@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
//public abstract class LifecycleViewBindingDelegate<in R : Any, out VB : ViewBinding>(
//    private val viewBinder: (R) -> VB,
//    private val onViewDestroyed: (VB) -> Unit,
//) : ViewBindingDelegate<R, VB> {
//
//    private var viewBinding: VB? = null
//
//    protected abstract fun getLifecycleOwner(thisRef: R): LifecycleOwner
//
//    @MainThread
//    public override fun getValue(thisRef: R, property: KProperty<*>): VB {
//        "getValue in".iLog(LIFECYCLE_VIEW_BINDING_DELEGATE)
//        viewBinding?.let { return it }
//
//        val lifecycle = getLifecycleOwner(thisRef).lifecycle
//        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
//            "getValue State == Lifecycle.State.DESTROYED".iLog(LIFECYCLE_VIEW_BINDING_DELEGATE)
//            this.viewBinding = null
//            // 已经执行了 onDestroy()
//            IllegalStateException("Lifecycle 执行了 onDestroy ,依旧调用了 viewBinding").printErrStackTrace(LIFECYCLE_VIEW_BINDING_DELEGATE)
//            // 不缓存 viewBinder 实例
//            return viewBinder(thisRef)
//        } else {
//            "getValue init viewBinder".iLog(LIFECYCLE_VIEW_BINDING_DELEGATE)
//            val viewBinding = viewBinder(thisRef)
//            lifecycle.addObserver(object : LifecycleEventObserver {
//                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//                    if (event == Lifecycle.Event.ON_DESTROY) {
//                        postClear()
//                    }
//                }
//            })
//            this.viewBinding = viewBinding
//            return viewBinding
//        }
//    }
//
//    @MainThread
//    @CallSuper
//    public override fun clear() {
//        "clear".iLog(LIFECYCLE_VIEW_BINDING_DELEGATE)
//        checkMainThread()
//        val viewBinding = viewBinding
//        this.viewBinding = null
//        if (viewBinding != null) {
//            onViewDestroyed(viewBinding)
//        }
//    }
//
//    internal fun postClear() {
//        "postClear".iLog(LIFECYCLE_VIEW_BINDING_DELEGATE)
//        if (!mainHandler.post { clear() }) {
//            clear()
//        }
//    }
//
//    private companion object {
//        private val mainHandler = Handler(Looper.getMainLooper())
//    }
//}
//
//@RestrictTo(RestrictTo.Scope.LIBRARY)
//public class ActivityViewBindingDelegate<in A : ComponentActivity, out VB : ViewBinding>(
//    onViewDestroyed: (VB) -> Unit,
//    viewBinder: (A) -> VB
//) : LifecycleViewBindingDelegate<A, VB>(viewBinder, onViewDestroyed) {
//
//    override fun getLifecycleOwner(thisRef: A): LifecycleOwner {
//        return thisRef
//    }
//
//}
//
