package com.akaf.batmanapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import butterknife.BindView
import butterknife.ButterKnife
import com.akaf.batmanapplication.utils.BuildConfig.Companion.ERROR_BUNDLE

class ErrorFragment : Fragment() {

    @BindView(R.id.txt_state_connect)
    lateinit var txtStateConnect: TextView

    @BindView(R.id.btn_retry)
    lateinit var btnRetry: TextView

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initNavController()
        initAction()
    }

    private fun initAction() {
        initSetView()
        initOnClick()
    }

    private fun initSetView() {
        val handleError = requireArguments().getString(ERROR_BUNDLE, "")
        txtStateConnect.text = handleError
    }

    private fun initOnClick() {
        btnRetry.setOnClickListener { initNavFragment() }

    }

    private fun initNavController() {
        navController =
            Navigation.findNavController(requireActivity(), R.id.my_nav_fragment)
    }

    private fun initNavFragment() {
        navController.navigateUp()
    }

    private fun initView(view: View) {
        ButterKnife.bind(this, view)
    }
}