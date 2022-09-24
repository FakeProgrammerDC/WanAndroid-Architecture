package com.dongchao.wanandroid.ui.main.home.list

import com.dongchao.common.utils.eLog
import com.dongchao.core.lib.base.*
import com.dongchao.core.network.bean.NetworkResponse
import com.dongchao.core.network.extensions.requestGet
import com.dongchao.home.list.Article
import com.dongchao.home.list.ArticleDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class ListState(
    override val isLoading: Boolean = false,
    override val data: List<ArticleDetail> = emptyList(),
    val curPage: Int = 0
) :
    BaseUiState<List<ArticleDetail>>()

@HiltViewModel
class ListViewModel @Inject constructor() : BaseViewModel<ListState, DefaultUiEffect>() {


    override fun createInitialState() = ListState()

    init {
        "HomeListViewModel init ".eLog()
         getHomeArticle(0)
    }

    fun getHomeArticle(index: Int) = launch {

        "HomeListViewModel getHomeArticle ".eLog()

        setState {
            copy(isLoading = true)
        }

        /**
         * Expected BEGIN_ARRAY but was BEGIN_OBJECT
         * json 类型解析错了,返回对象给成 list
         */
        // https://www.wanandroid.com/article/list/0/json
        var responseArticles = iHttpProcessor.requestGet<Article>(
            "article/list/$index/json",
        )

        if (responseArticles is NetworkResponse.Success) {
            setState {
                copy(data = responseArticles.data.datas)
            }
        }
    }
}