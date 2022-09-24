package com.dongchao.wanandroid.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dongchao.common.utils.eLog
import com.dongchao.common.utils.iLog
import com.dongchao.core.lib.base.BaseActivity
import com.dongchao.core.lib.base.delegates.launchStarted
import com.dongchao.core.lib.router.AppRouter
import com.dongchao.home.HomeFragment
import com.dongchao.my.MyFragment
import com.dongchao.navigation.NavigationFragment
import com.dongchao.wanandroid.R
import com.dongchao.wanandroid.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint

/**
 *    @author : dong.chao
 *    @time   : 2022/9/7
 *    @desc   : 注入的 Activity/Fragment 一定要加 @AndroidEntryPoint
 */
//@Route(path = AppRouter.mainPath)
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    ActivityMainBinding::bind
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "onCreate mainViewModel.hashCode = ${viewModel.hashCode()}".eLog()

        viewBinding.apply {

            mainContentVp.adapter = MainFragmentAdapter(
                listOf(
                    HomeFragment.newInstance(),
                    NavigationFragment.newInstance(),
                    MyFragment.newInstance()
                ), supportFragmentManager, lifecycle
            )

            mainContentVp.isUserInputEnabled = false

            mainContentVp.setCurrentItem(0, false)

            mainBottom.setOnItemSelectedListener {
                onBottomItemSelect(it)
            }
        }
    }

    override fun initObserver() {
        launchStarted {
            with(viewModel) {
                testNet()
            }
        }
    }

    private fun onBottomItemSelect(item: MenuItem): Boolean {
        switchFragment(getFragmentIndexFromItemId(item.itemId))
        return true
    }

    private fun switchFragment(fragmentIndexFromItemId: Int) {
        viewBinding.mainContentVp.currentItem = fragmentIndexFromItemId
    }

    private fun getFragmentIndexFromItemId(itemId: Int): Int {
        return when (itemId) {
            R.id.navigation_home -> 0
            R.id.navigation_navigator -> 1
            R.id.navigation_my -> 2
            else -> 0
        }
    }

}

class MainFragmentAdapter(
    var items: List<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = items.size

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}

