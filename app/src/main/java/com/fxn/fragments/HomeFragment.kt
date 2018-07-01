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
import com.fxn.utilities.RevealAnimationSetting
import com.fxn.utilities.Utility
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*


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
        //view.recycleView.layoutManager = LinearLayoutManager(activity)
        view.recycleView.adapter = HomeAdapter(activity!!.applicationContext) {
            //  Toast.makeText(activity, "${it.title} Clicked", Toast.LENGTH_SHORT).show()

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
        /*Select()
		.from(Item.class)
		.where("Category = ?", category.getId())
		.orderBy("Name ASC")
		.execute();*/
        homeholder.addall(list)
        /* var count: Int = 0;
         while (count < 20) {
             homeholder.addOne("asdadadsad asdasd\n asdsd asda sda sdasd as dasd asd asdasdasd a sdasdas dasda")
             homeholder.addOne("asdasd asdsd asda sda \n sdasd as dasd asd asdasdasd a sdasdas dasda")
             homeholder.addOne(" asd \nasdasdasd a sdasdas dasda")
             ++count
         }*/
        view.fab.setOnClickListener {
            //  registerCircularRevealAnimation(context, view, constructRevealSettings(), Color.parseColor("#EFAD32"), Color.WHITE)
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, InputFragment.newInstance(TextItem("", Utility.getCountedTime(Date()), 1), ""))
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

    private fun constructRevealSettings(): RevealAnimationSetting {
        return RevealAnimationSetting(
                Math.abs(fab.x + fab.width / 2) as Int,
                Math.abs(fab.y + fab.height / 2) as Int,
                homeContainer.width,
                homeContainer.height)
    }

}