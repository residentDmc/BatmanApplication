package com.akaf.batmanapplication.ui.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.moviedetail.Rating
import com.akaf.batmanapplication.data.model.movielist.MovieList
import com.akaf.batmanapplication.databinding.FragmentDetailMovieBinding
import com.akaf.batmanapplication.ui.main.adapter.genreList.AdapterGenre
import com.akaf.batmanapplication.ui.main.adapter.movielist.AdapterMovieList
import com.akaf.batmanapplication.ui.main.adapter.ratingList.AdapterRating
import com.akaf.batmanapplication.ui.main.viewmodel.MovieDetailViewModel
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.API_KEY
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.ITEM_ID_BUNDLE
import com.akaf.batmanapplication.utils.tools.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding
    private val toastTools: ToastTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val adapterGenre: AdapterGenre by inject()
    private val adapterRating: AdapterRating by inject()
    private val throwableTools: ThrowableTools by inject()
    private val glideTools: GlideTools by inject()
    private val splitterTools: SplitterTools by inject()
    private val adapter: AdapterMovieList by inject()
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()




    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            getMovieDetail()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun getMovieDetail() {
        initShowLoading()
        getItemId()?.let {
            movieDetailViewModel.getMovieDetail(API_KEY, it).observe(
                requireActivity(), this::initResultMovieDetail)
        }
    }

    private fun initShowLoading() {
        binding.vDetailParent.visibility=View.GONE
        binding.prgMovieDetail.visibility=View.VISIBLE
    }

    private fun initHideLoadingAndShowDetail() {
        binding.vDetailParent.visibility=View.VISIBLE
        binding.prgMovieDetail.visibility=View.GONE
    }

    private fun initHideLoading() {
        binding.prgMovieDetail.visibility=View.GONE
    }

    private fun initResultMovieDetail(it: Any){
        when (it) {
            is MovieDetail -> initMovieDetail(it)
            is Throwable -> initThrowable(it)
        }
    }

    private fun initThrowable(it: Throwable) {
        initHideLoading()
        val message = throwableTools.getThrowableError(it)
        handelErrorTools.handelError(it)
        toastTools.toast(message)
    }

    private fun initMovieDetail(movieDetail: MovieDetail) {
        initHideLoadingAndShowDetail()
        glideTools.displayImageOriginal(binding.imgPoster, movieDetail.poster)
        initAdapterGenre(getGenreList(movieDetail.genre))
        initAdapterRatings(movieDetail.ratings)
        binding.txtTitleAndYear.text = movieDetail.titleAndYear
        binding.txtRated.text = movieDetail.rated
        binding.txtRuntime.text = movieDetail.runtime
        binding.txtReleased.text = movieDetail.released
        binding.txtPlot.text = movieDetail.plot
        binding.txtRating.text = movieDetail.imdbRating
        binding.txtVotes.text = movieDetail.imdbVotes
        binding.txtMetascore.text = movieDetail.metascore
        binding.txtDirector.text = movieDetail.director
        binding.txtWriter.text = movieDetail.writer
        binding.txtActors.text = movieDetail.actors
        binding.txtBoxOffice.text = movieDetail.boxOffice
        binding.txtWebsite.text = movieDetail.website
        binding.txtCountry.text = movieDetail.country
        binding.txtLanguage.text = movieDetail.language
        binding.txtProduction.text = movieDetail.production
        binding.txtAwards.text = movieDetail.awards
    }

    private fun initAdapterRatings(ratings: List<Rating>) {
        binding.rcRating.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcRating.setHasFixedSize(true)
        binding.rcRating.adapter = adapterRating
        adapterRating.updateList(ratings)
    }

    private fun initAdapterGenre(genreList: List<String>) {
        binding.rcGenre.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rcGenre.setHasFixedSize(true)
        binding.rcGenre.adapter = adapterGenre
        adapterGenre.updateList(genreList)
    }

    private fun getGenreList(genre: String): List<String> {
        return splitterTools.splitterStringList(genre)
    }

    private fun getItemId(): String? {
        return requireArguments().getString(ITEM_ID_BUNDLE, "")
    }
}