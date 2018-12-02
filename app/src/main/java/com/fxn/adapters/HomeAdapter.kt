package com.fxn.adapters

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fxn.lineup.R
import com.fxn.models.ColorGenerator
import com.fxn.models.TextItem
import com.fxn.utilities.CustomTypefaceSpan
import com.fxn.utilities.Utility
import kotlinx.android.synthetic.main.home_list_row.view.*
import java.util.*

/**
 * Created by akshay on 12/05/18.
 */
class HomeAdapter(var context: Context, var listner: (TextItem) -> Unit) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var list: ArrayList<TextItem> = ArrayList()

    fun addall(List: MutableList<TextItem>) {
        list.clear()
        list.addAll(List)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list.get(position), listner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.home_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(user: TextItem, listner: (TextItem) -> Unit) = with(itemView) {
            var s: String? = user.title
            if (s!!.length > 45) s = s.substring(0, 45) + "..."
            var str: Spannable = Spannable.Factory.getInstance().newSpannable(s)
            var length: Int = s.indexOf("\n")
            if (length <= 0) length = s.length
            //var res = ColorGenerator(user.color)
            var res = ColorGenerator(adapterPosition % 4)
            itemView.main.setBackgroundResource(res.getDrawable()!!)
            itemView.title.setTextColor(res.getColor(context))
            itemView.date.setTextColor(res.getColor(context))
            str.setSpan(CustomTypefaceSpan(ResourcesCompat.getFont(context, R.font.quicksand_bold)), 0, length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            itemView.title.setText(str, TextView.BufferType.SPANNABLE)
            itemView.date.text = Utility.getCountedTime(user.date)
            itemView.setOnClickListener { listner(user) }
        }
    }
}