package com.fxn.fragments


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fxn.lineup.R
import com.fxn.models.TextItem
import com.fxn.utilities.CustomTypefaceSpan
import com.fxn.utilities.Utility
import kotlinx.android.synthetic.main.fragment_input.view.*
import java.text.SimpleDateFormat
import java.util.*

class InputFragment : Fragment(), TextWatcher {


    private var mParam1: TextItem? = null
    private var mParam2: String? = null
    var text: String? = ""

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        fun newInstance(param1: TextItem, param2: String): InputFragment {
            val fragment = InputFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getSerializable(ARG_PARAM1) as TextItem?
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_input, container, false)
        text = mParam1!!.title
        view.main_input.setText(text)
        view.main_input.addTextChangedListener(this)
        var s: String = SimpleDateFormat("MMM dd HH:mm:aa").format(Date()).toString()
        var str: Spannable = Spannable.Factory.getInstance().newSpannable(s)
        str.setSpan(CustomTypefaceSpan(ResourcesCompat.getFont(activity!!.applicationContext, R.font.quicksand_bold)),
                0, 6,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        view.back_date.setText(str, TextView.BufferType.SPANNABLE)
        view.back.setOnClickListener { activity!!.onBackPressed() }
        //  view.viewPager.adapter = ColorAdapter(fragmentManager!!, 5)
        datepickerwork(view)
        view.main_input.setOnTouchListener { view, motionEvent -> false }
        return view
    }

    private fun datepickerwork(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minutes = c.get(Calendar.MINUTE)

        val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { _, yeardate, monthOfYear, dayOfMonth ->
            TimePickerDialog(activity, TimePickerDialog.OnTimeSetListener { _, hourtime, mintime ->
                val c1 = Calendar.getInstance()
                c1.set(Calendar.YEAR, yeardate)
                c1.set(Calendar.MONTH, monthOfYear)
                c1.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                c1.set(Calendar.HOUR_OF_DAY, hourtime)
                c1.set(Calendar.MINUTE, mintime)
                var s: String = SimpleDateFormat("MMM dd HH:mm:aa").format(c1.time).toString()
                var str: Spannable = Spannable.Factory.getInstance().newSpannable(s)
                str.setSpan(CustomTypefaceSpan(ResourcesCompat.getFont(activity!!.applicationContext, R.font.quicksand_bold)),
                        0, 6,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                view.alarm.setText(str, TextView.BufferType.SPANNABLE)
            }, hour, minutes, true).show()
        }, year, month, day)
        view.alarm.setOnClickListener { dpd.show() }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        if (mParam1 == null && text!!.length != 0) {
            TextItem(text!!, Utility.getCountedTime(Date()), 1).save()
        } else if (text!!.length == 0 && mParam1 != null) {
            mParam1!!.delete()
            return
        }
        mParam1!!.color = 0
        mParam1!!.title = text
        mParam1!!.save()
    }

    override fun afterTextChanged(p0: Editable?) {}

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        text = p0.toString()
    }

}
