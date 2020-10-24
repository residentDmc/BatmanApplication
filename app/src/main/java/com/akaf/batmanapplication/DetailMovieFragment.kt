package com.akaf.batmanapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.moviedetail.Rating
import com.akaf.batmanapplication.ui.main.adapter.genreList.AdapterGenre
import com.akaf.batmanapplication.ui.main.adapter.ratingList.AdapterRating
import com.akaf.batmanapplication.ui.main.viewmodel.MovieDetailViewModel
import com.akaf.batmanapplication.utils.BuildConfig
import com.akaf.batmanapplication.utils.BuildConfig.Companion.API_KEY
import com.akaf.batmanapplication.utils.BuildConfig.Companion.ITEM_ID_BUNDLE
import com.akaf.batmanapplication.utils.GlideTools
import com.akaf.batmanapplication.utils.HandelErrorTools
import com.akaf.batmanapplication.utils.SplitterTools
import com.akaf.kotlinkoin.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {

    @BindView(R.id.prg_movie_detail)
    lateinit var prgMovieDetail: ProgressBar

    @BindView(R.id.txt_title_and_year)
    lateinit var txtTitleAndYear: TextView

    @BindView(R.id.txt_rated)
    lateinit var txtRated: TextView

    @BindView(R.id.txt_released)
    lateinit var txtReleased: TextView

    @BindView(R.id.txt_runtime)
    lateinit var txtRuntime: TextView

    @BindView(R.id.txt_plot)
    lateinit var txtPlot: TextView

    @BindView(R.id.txt_rating)
    lateinit var txtRating: TextView

    @BindView(R.id.txt_votes)
    lateinit var txtVotes: TextView

    @BindView(R.id.txt_metascore)
    lateinit var txtMetascore: TextView

    @BindView(R.id.txt_director)
    lateinit var txtDirector: TextView

    @BindView(R.id.txt_writer)
    lateinit var txtWriter: TextView

    @BindView(R.id.txt_actors)
    lateinit var txtActors: TextView

    @BindView(R.id.txt_box_office)
    lateinit var txtBoxOffice: TextView

    @BindView(R.id.txt_website)
    lateinit var txtWebsite: TextView

    @BindView(R.id.txt_country)
    lateinit var txtCountry: TextView

    @BindView(R.id.txt_language)
    lateinit var txtLanguage: TextView

    @BindView(R.id.txt_production)
    lateinit var txtProduction: TextView

    @BindView(R.id.txt_awards)
    lateinit var txtAwards: TextView

    @BindView(R.id.v_detail_parent)
    lateinit var vDetailParent: View

    @BindView(R.id.img_poster)
    lateinit var imgPoster: ImageView

    @BindView(R.id.rc_genre)
    lateinit var rcGenre: RecyclerView

    @BindView(R.id.rc_rating)
    lateinit var rcRating: RecyclerView

    private lateinit var navController: NavController

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initView(view)
            initNavController()
            getMovieDetail()
        } catch (e: Exception) {
            HandelErrorTools.instance.handelError(e)
        }
    }

    private fun getMovieDetail() {
        getItemId()?.let {
            movieDetailViewModel.fetchMovieDetail(API_KEY, it).observe(
                viewLifecycleOwner,
                { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            prgMovieDetail.visibility = View.GONE
                            vDetailParent.visibility = View.VISIBLE
                            resource.data?.let { movieDetail -> setDataResult(movieDetail) }
                        }

                        Status.LOADING -> {
                            prgMovieDetail.visibility = View.VISIBLE
                            vDetailParent.visibility = View.GONE
                        }

                        Status.ERROR -> {
                            //Handle Error
                            prgMovieDetail.visibility = View.GONE
                            resource.message?.let { message ->
                                navController.navigate(
                                    R.id.action_detailMovieFragment_to_errorFragment,
                                    getBundle(message)
                                )
                            }
                        }
                    }
                })
        }
    }

    private fun getBundle(error: String): Bundle? {
        val bundle = Bundle()
        bundle.putString(BuildConfig.ERROR_BUNDLE, error)
        return bundle
    }

    private fun setDataResult(movieDetail: MovieDetail) {
        GlideTools.instanceGlide.displayImageOriginal(imgPoster, movieDetail.poster)
        initAdapterGenre(getGenreList(movieDetail.genre))
        initAdapterRatings(movieDetail.ratings)
        txtTitleAndYear.text = movieDetail.titleAndYear
        txtRated.text = movieDetail.rated
        txtRuntime.text = movieDetail.runtime
        txtReleased.text = movieDetail.released
        txtPlot.text = movieDetail.plot
        txtRating.text = movieDetail.imdbRating
        txtVotes.text = movieDetail.imdbVotes
        txtMetascore.text = movieDetail.metascore
        txtDirector.text = movieDetail.director
        txtWriter.text = movieDetail.writer
        txtActors.text = movieDetail.actors
        txtBoxOffice.text = movieDetail.boxOffice
        txtWebsite.text = movieDetail.website
        txtCountry.text = movieDetail.country
        txtLanguage.text = movieDetail.language
        txtProduction.text = movieDetail.production
        txtAwards.text = movieDetail.awards
    }

    private fun initAdapterRatings(ratings: List<Rating>) {
        rcRating.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcRating.setHasFixedSize(true)
        val adapter = AdapterRating()
        rcRating.adapter = adapter
        adapter.updateList(ratings)
    }

    private fun initAdapterGenre(genreList: List<String>) {
        rcGenre.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcGenre.setHasFixedSize(true)
        val adapter = AdapterGenre()
        rcGenre.adapter = adapter
        adapter.updateList(genreList)
    }

    private fun getGenreList(genre: String): List<String> {
        return SplitterTools.getInstance().splitterStringList(genre)
    }

    private fun getItemId(): String? {
        return requireArguments().getString(ITEM_ID_BUNDLE, "")
    }

    private fun initView(view: View) {
        ButterKnife.bind(this, view)
    }

    private fun initNavController() {
        navController =
            Navigation.findNavController(requireActivity(), R.id.my_nav_fragment)
    }
}