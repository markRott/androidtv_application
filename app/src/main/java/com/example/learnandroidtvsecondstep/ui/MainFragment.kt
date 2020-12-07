package com.example.learnandroidtvsecondstep.ui

import android.os.Bundle
import androidx.collection.SparseArrayCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.example.learnandroidtvsecondstep.R
import com.example.learnandroidtvsecondstep.entities.ui.MovieItem
import com.example.learnandroidtvsecondstep.image.ImageLoaderContract
import com.example.learnandroidtvsecondstep.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BrowseSupportFragment(), OnItemViewSelectedListener, OnItemViewClickedListener {

    private val mainViewModel: MainViewModel by viewModels()
    private val movieRowsMap = SparseArrayCompat<MovieRow>()
    private val mainAdapter = ArrayObjectAdapter(ListRowPresenter())

    @Inject lateinit var itemsFactory: ItemsFactory
    @Inject lateinit var imageLoader: ImageLoaderContract

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupUI()
        setupSearchButton()
        initClickListeners()

        itemsFactory.prepareLeftRows(movieRowsMap, mainAdapter)
        adapter = mainAdapter

        subscribeToMoviesData()
        mainViewModel.fetchAllCategories()
    }

    private fun setupUI() {
        title = "First app"
        val color = ContextCompat.getColor(requireContext(), R.color.blue)
        brandColor = color
    }

    private fun setupSearchButton() {
        searchAffordanceColor = resources.getColor(R.color.black)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        setOnSearchClickedListener { v -> }
    }

    private fun subscribeToMoviesData() {
        mainViewModel.moviesLD.observe(viewLifecycleOwner) { state ->
            val currAdapter: PaginationAdapter? = movieRowsMap[state.id]?.adapter
            currAdapter?.let { adapter ->
                adapter.page = state.data.page + 1
                adapter.totalPages = state.data.totalPages
                adapter.addAll(adapter.size(), state.data.results)
            }
        }
    }

    override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?) {

        when (item) {
            is MovieItem -> {
                imageLoader.loadImage(requireActivity(), "$BACKDROP_URL${item.backdropPath}")
//                val currPosition = (rowViewHolder as ListRowPresenter.ViewHolder).selectedPosition
//                val adapter = ((row as ListRow).adapter) as PaginationAdapter
//                if (currPosition % 5 == 0 && adapter.hasMoreData()) {
//                    mainViewModel.fetchById(row.headerItem.id.toInt(), adapter.page)
//                    Log.d(APP_TAG, "7777 Load more data for: ${row.headerItem.id.toInt()}")
//                }
            }
        }
    }

    override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?) {

        when (item) {
            is MovieItem -> {
            }
        }
    }

    private fun initClickListeners() {
        onItemViewClickedListener = this
        onItemViewSelectedListener = this
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}