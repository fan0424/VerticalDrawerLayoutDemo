package com.wtk.verdrawerdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by fan.feng on 2019/3/6.
 */
class ChidMenuAdapter : RecyclerView.Adapter<ChidMenuAdapter.ChildHolder>() {

    private var datas: ArrayList<String>? = null

    fun setDatas(value: ArrayList<String>?){
        datas = value
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            ChildHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_child_menu, p0, false))

    override fun getItemCount() = datas?.size ?: 0

    override fun onBindViewHolder(p0: ChildHolder, p1: Int) {

        p0.tvCildMenu?.text = datas!![p1]

    }


    class ChildHolder : RecyclerView.ViewHolder{

        var tvCildMenu: TextView? = null

        constructor(itemView: View): super(itemView){
            tvCildMenu = itemView.findViewById(R.id.tvCildMenu)
        }
    }

}