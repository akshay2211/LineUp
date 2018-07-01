package com.fxn.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fxn.lineup.R
import com.fxn.models.ColorGenerator

class ColorBackgroundFragment : Fragment() {

    private var mParam1: Int? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getInt(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_color_background, container, false)
        view.setBackgroundColor(ColorGenerator(mParam1!!).getColor(activity!!))
        return view
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: Int, param2: String): ColorBackgroundFragment {
            val fragment = ColorBackgroundFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
