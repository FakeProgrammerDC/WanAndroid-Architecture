package com.dongchao.home.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.dongchao.common.utils.*
import com.dongchao.core.lib.base.BaseFragment
import com.dongchao.core.lib.base.delegates.viewModels
import com.dongchao.home.HomeViewModel
import com.dongchao.home.R
import com.dongchao.home.banner.BannerFragment
import com.dongchao.home.databinding.FragemntViewBannerBinding
import com.dongchao.home.databinding.FragmentViewListBinding
import com.dongchao.wanandroid.ui.main.home.list.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentViewListBinding, ListViewModel>(
    R.layout.fragment_view_list,
    FragmentViewListBinding::bind
) {

    private val homeListAdapter: ListAdapter by lazy {
        ListAdapter(data = emptyMutableList())
    }

    private val homeViewModel: HomeViewModel by viewModels(
        HomeViewModel::class.java.kotlin,
        { this.requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        "homeViewModel = ${homeViewModel.hashCode()}".iClassTagLog<ListFragment>()

        with(viewBinding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = homeListAdapter
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                if (state.data.isNotEmpty()) {
                    "收到数据 =  ${state.data.size}".iLog()
                    checkMainThread()
                    homeListAdapter.setList(state.data)
                }
            }
        }
    }
}


internal class ListAdapter(
    layoutResId: Int = R.layout.item_home_list,
    data: MutableList<ArticleDetail>?
) :
    BaseQuickAdapter<ArticleDetail, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: ArticleDetail) {
        item?.let {
            holder.setText(R.id.title, "${it.title}")
        }
    }
}