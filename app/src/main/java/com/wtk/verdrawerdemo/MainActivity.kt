package com.wtk.verdrawerdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvMenu?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var adapter = MenuAdapter()
        rvMenu?.adapter = adapter

        var pagerSnap = PagerSnapHelper()
        pagerSnap.attachToRecyclerView(rvMenu)

        adapter?.setDatas(createDatas())

        rvMenu?.addOnScrollListener(object: SnapPageScrollListener(){
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                Log.e("fff", "onPageScrolled      position: $position    positionOffset: $positionOffset  positionOffsetPixels: $positionOffsetPixels  ")
            }

            override fun onPageSelected(position: Int) {
                Log.e("fff", "onPageSelected      position: $position")
            }
        })

        btnScroll?.setOnClickListener {
            rvMenu.scrollToPosition(12)
        }
    }


    private fun createDatas(): ArrayList<MenuBean>{

        var datas = ArrayList<MenuBean>()

        for ( i in 0 .. 12){

            var menuBean = MenuBean()
            var childDatas = ArrayList<String>()

            for ( j in 0 .. 20){

                val ss = "这是一段子菜单的第 $j"

                childDatas.add(ss)
            }

            menuBean.itemDatas = childDatas
            datas.add(menuBean)

        }

        return datas
    }

}
