package com.dongchao.wanandroid.copy

import com.dongchao.core.lib.base.BaseActivity
import com.dongchao.wanandroid.R
import com.dongchao.wanandroid.databinding.ActivityCopyBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *    @author : dong.chao
 *    @time   : 2022/9/24
 *    @desc   : Activity 模版类用于复制
 */
@AndroidEntryPoint
class CopyActivity :BaseActivity<ActivityCopyBinding, CopyViewModel>(
    R.layout.activity_copy,
    ActivityCopyBinding::bind
){
    override fun initObserver() {
        TODO("Not yet implemented")
    }
}

//@AndroidEntryPoint
//class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
//    R.layout.activity_main,
//    ActivityMainBinding::bind
//) {