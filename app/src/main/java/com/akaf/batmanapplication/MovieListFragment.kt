package com.akaf.batmanapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.akaf.batmanapplication.data.model.movielist.Movie
import com.akaf.batmanapplication.data.model.movielist.MovieList
import com.akaf.batmanapplication.interfaces.OnItemTouchListener
import com.akaf.batmanapplication.ui.main.adapter.movielist.AdapterMovieList
import com.akaf.batmanapplication.ui.main.viewmodel.MovieListViewModel
import com.akaf.batmanapplication.utils.BuildConfig.Companion.API_KEY
import com.akaf.batmanapplication.utils.BuildConfig.Companion.ERROR_BUNDLE
import com.akaf.batmanapplication.utils.BuildConfig.Companion.ITEM_ID_BUNDLE
import com.akaf.batmanapplication.utils.BuildConfig.Companion.MOVIE_LIST
import com.akaf.batmanapplication.utils.HandelErrorTools
import com.akaf.batmanapplication.utils.ScreenLayoutTools
import com.akaf.kotlinkoin.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.progressBar)
    lateinit var progressBar: View

    private val movieListViewModel: MovieListViewModel by viewModel()

    private lateinit var adapter: AdapterMovieList

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initView(view)
            initNavController()
            initAdapter()
            getMovieList()
        } catch (e: Exception) {
            HandelErrorTools.instance.handelError(e)
        }
    }

    private fun initNavController() {
        navController =
            Navigation.findNavController(requireActivity(), R.id.my_nav_fragment)
    }

    private fun getMovieList() {
        movieListViewModel.fetchMovieList(API_KEY, MOVIE_LIST)
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        it.data?.let { movieList -> renderList(movieList) }
                    }

                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }

                    Status.ERROR -> {
                        //Handle Error
                        progressBar.visibility = View.GONE
                        it.message?.let { message ->
                            navController.navigate(
                                R.id.action_movieListFragment_to_errorFragment,
                                getBundle(message)
                            )
                        }
                    }
                }
            })
    }

    private fun getBundle(error: String): Bundle? {
        val bundle = Bundle()
        bundle.putString(ERROR_BUNDLE, error)
        return bundle
    }

    private fun initAdapter() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(
            getSpanCount(),
            StaggeredGridLayoutManager.VERTICAL
        )
        recyclerView.layoutManager = staggeredGridLayoutManager
        adapter = AdapterMovieList()
        recyclerView.adapter = adapter
        adapter.setOnClickListener(object : OnItemTouchListener {
            override fun onItemTouchListener(movie: Movie) {
                initMovie(movie)
            }
        })
    }

    private fun initView(view: View) {
        ButterKnife.bind(this, view)
    }

    private fun getSpanCount(): Int {
        return ScreenLayoutTools.getInstance().screenLayout()
    }

    private fun initMovie(movie: Movie) {
        navController.navigate(
            R.id.action_movieListFragment_to_detailMovieFragment,
            getBundleDetailMovie(movie.imdbID)
        )
    }

    private fun renderList(movieList: MovieList) {
        adapter.addData(movieList.movies)
    }

    private fun getBundleDetailMovie(itemId: String): Bundle? {
        val bundle = Bundle()
        bundle.putString(ITEM_ID_BUNDLE, itemId)
        return bundle
    }

}