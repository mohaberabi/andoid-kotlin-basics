package com.example.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class UserFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        // init all
        // not ui as the ui is still not inflated  as this is called before the onCreateView
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.user_fragment, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // ui is inflated and ready here for sure 100%
        // as this is called just before the onCreateView
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
    }


}