package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MyAwesomeAdapter(private val list: Array<String>, private val ctx: Context) : BaseAdapter() {

    private var showPosition: Int = -1 // default is -1

    override fun getCount(): Int {
        return list.count()
    }

    override fun getItem(position: Int): String {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position + 0L
    }

    fun setPositionToShow(position: Int) {
        if (position < 0 || position >= list.count()) return
        showPosition = position
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // if View already exists it will be reused as convertView
        val view: View = convertView ?: inflater.inflate(R.layout.my_item, parent, false)

        val rootView: ConstraintLayout = view.findViewById(R.id.rootView)
        val textView: TextView = view.findViewById(R.id.textView)

        if (showPosition == position) {
            rootView.setBackgroundColor(
                ctx.resources.getColor(
                    android.R.color.holo_red_dark,
                    ctx.theme
                )
            )
            textView.setTextColor(ctx.resources.getColor(android.R.color.white, ctx.theme))
        } else {
            rootView.setBackgroundColor(ctx.resources.getColor(android.R.color.white, ctx.theme))
            textView.setTextColor(ctx.resources.getColor(android.R.color.holo_red_dark, ctx.theme))
        }

        textView.text = getItem(position)

        return view
    }
}