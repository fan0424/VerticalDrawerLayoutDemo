package com.wtk.verdrawerdemo

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by fan.feng on 2019/3/6.
 */
class MenuAdapter : RecyclerView.Adapter<MenuAdapter.MenuHolder>() {

    private var datas: ArrayList<MenuBean>? = null

    fun setDatas(datas: ArrayList<MenuBean>?){
        this.datas = datas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int)=
            MenuHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_menu, p0, false))

    override fun getItemCount() = datas?.size ?: 0

    override fun onBindViewHolder(p0: MenuHolder, p1: Int) {

        p0.rvChidMenu?.layoutManager = LinearLayoutManager(p0.rvChidMenu?.context)
        var adapter = ChidMenuAdapter()
        p0.rvChidMenu?.adapter = adapter
        adapter?.setDatas(datas!![p1].itemDatas)

        p0.tvPosition?.text = p1.toString()
    }


    class MenuHolder : RecyclerView.ViewHolder{

        var rvChidMenu: RecyclerView? = null
        var tvPosition: TextView? = null

        constructor(itemView: View): super(itemView){
            rvChidMenu = itemView.findViewById(R.id.rvChidMenu)
            tvPosition = itemView.findViewById(R.id.tvPosition)
        }
    }

}