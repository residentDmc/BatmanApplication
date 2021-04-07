package com.akaf.batmanapplication.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.akaf.batmanapplication.R
import com.akaf.batmanapplication.data.model.movielist.Movie
import com.akaf.batmanapplication.data.model.movielist.MovieList
import com.akaf.batmanapplication.data.repository.db.MovieListDbRepository
import com.akaf.batmanapplication.database.MovieDatabase
import com.akaf.batmanapplication.databinding.FragmentMovieListBinding
import com.akaf.batmanapplication.interfaces.OnItemTouchListener
import com.akaf.batmanapplication.ui.main.adapter.movielist.AdapterMovieList
import com.akaf.batmanapplication.ui.main.viewmodel.MovieListViewModel
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.API_KEY
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.ITEM_ID_BUNDLE
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.MOVIE_LIST
import com.akaf.batmanapplication.utils.tools.HandelErrorTools
import com.akaf.batmanapplication.utils.manager.GridLayoutCountManager
import com.akaf.batmanapplication.utils.tools.ThrowableTools
import com.akaf.batmanapplication.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding

    private val screenLayoutTools: GridLayoutCountManager by inject()
    private val toastTools: ToastTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val adapter: AdapterMovieList by inject()
    private val movieDatabase: MovieDatabase by inject()
    private val sssa: MovieListDbRepository by inject()
    private val movieListViewModel: MovieListViewModel by viewModel()
    private lateinit var navController: NavController


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initNavController()
            initAdapter()
            getMovieList()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initNavController() {
        navController =
            Navigation.findNavController(requireActivity(), R.id.my_nav_fragment)
    }

    private fun getMovieList() {
        initShowLoading()
        movieListViewModel.fetchMovieList(API_KEY, MOVIE_LIST)
            .observe(requireActivity(), this::initResultMoveList)
    }

    private fun initShowLoading() {
        binding.recyclerView.visibility=View.GONE
        binding.progressBar.visibility=View.VISIBLE
    }

    private fun initHideLoadingAndShowList() {
        binding.recyclerView.visibility=View.VISIBLE
        binding.progressBar.visibility=View.GONE
    }

    private fun initHideLoading() {
        binding.progressBar.visibility=View.GONE
    }

    private fun initResultMoveList(it: Any) {
        when (it) {
            is ArrayList<*> -> initMovieList(it)
            is Throwable -> initThrowable(it)
        }
    }

    private fun initMovieList(it: ArrayList<*>) {
        initHideLoadingAndShowList()
        val movieList: List<Movie> = it.filterIsInstance<Movie>()
        adapter.updateList(movieList)
    }

    private fun initThrowable(it: Throwable) {
        initHideLoading()
        val message = throwableTools.getThrowableError(it)
        handelErrorTools.handelError(it)
        toastTools.toast(message)
    }

    private fun initAdapter() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(
            getSpanCount(),
            StaggeredGridLayoutManager.VERTICAL
        )
        binding.recyclerView.layoutManager = staggeredGridLayoutManager
        binding.recyclerView.adapter = adapter
        adapter.setOnClickListener(object : OnItemTouchListener {
            override fun onItemTouchListener(movie: Movie) {
                initMovie(movie)
            }
        })
    }

    private fun getSpanCount(): Int {
        return screenLayoutTools.getColumnWidth()
    }

    private fun initMovie(movie: Movie) {
        navController.navigate(
                R.id.action_movieListFragment_to_detailMovieFragment,
            getBundleDetailMovie(movie.imdbID)
        )
    }

    private fun getBundleDetailMovie(itemId: String): Bundle {
        val bundle = Bundle()
        bundle.putString(ITEM_ID_BUNDLE, itemId)
        return bundle
    }

}