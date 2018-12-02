package com.fxn.fragments


import android.os.Bundle
import android.support.transition.Fade
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import com.activeandroid.query.Select
import com.fxn.adapters.HomeAdapter
import com.fxn.lineup.R
import com.fxn.models.TextItem
import com.fxn.utilities.Utility
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_home, container, false)

        view.recycleView.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
        view.recycleView.adapter = HomeAdapter(activity!!.applicationContext) {
            exitTransition = Fade()
            val input = InputFragment.newInstance(it, "")
            input.enterTransition = Fade()
            activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, input)
                    .addToBackStack(null)
                    .commit()
        }
        var list: MutableList<TextItem> = Select().from(TextItem::class.java).execute<TextItem>()

        var homeholder: HomeAdapter = (view.recycleView.adapter as HomeAdapter)

        homeholder.addall(list)

        view.fab.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, InputFragment.newInstance(TextItem("", Utility.getDateTimeString(), 1), ""))
                    .addToBackStack("").commit()
        }
        return view
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        fun newInstance(param1: String, param2: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}