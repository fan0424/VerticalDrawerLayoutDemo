package com.wtk.verdrawerdemo

import android.support.v7.widget.RecyclerView
import android.support.annotation.CallSuper
import android.support.v7.widget.SnapHelper
import android.view.View


/**
 * Created by fan.feng on 2019/3/6.
 */
abstract class SnapPageScrollListener : RecyclerView.OnScrollListener() {

    var currentPosition = RecyclerView.NO_POSITION

    protected var snapHelper: SnapHelper? = null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        if (snapHelper == null) {
            val flingListener = recyclerView.onFlingListener
            if (flingListener is SnapHelper) {
                snapHelper = flingListener
            }
        }
        var snapView: View? = null
        var position = RecyclerView.NO_POSITION
        if (layoutManager != null) {
            snapView = snapHelper!!.findSnapView(layoutManager)
            if (snapView != null) {
                position = layoutManager.getPosition(snapView!!)
            }
        }
        if (position == RecyclerView.NO_POSITION) {
            return
        }

        if (currentPosition != position) {
            currentPosition = position
            onPageSelected(position)
        }

        val snapViewDistance = snapHelper!!.calculateDistanceToFinalSnap(layoutManager!!, snapView!!)
        var positionOffset = 0f
        var positionOffsetPixels = 0
        if (snapViewDistance != null) {
            if (layoutManager.canScrollHorizontally()) {
                positionOffsetPixels = snapViewDistance[0]
                positionOffset = snapViewDistance[0].toFloat() / snapView!!.getWidth()
            } else {
                positionOffsetPixels = snapViewDistance[1]
                positionOffset = snapViewDistance[1].toFloat() / snapView!!.getHeight()
            }
        }

        val targetPosition: Int
        val targetPositionOffset: Float
        val targetPositionOffsetPixels: Int

        if (positionOffset <= 0) {
            targetPosition = position
            targetPositionOffset = Math.abs(positionOffset)
            targetPositionOffsetPixels = Math.abs(positionOffsetPixels)
        } else {
            targetPosition = position - 1
            val lastView = layoutManager.findViewByPosition(targetPosition)
            var lastViewDistance: IntArray? = IntArray(2)
            if (lastView != null) {
                lastViewDistance = snapHelper!!.calculateDistanceToFinalSnap(layoutManager, lastView)
            }
            var lastViewPositionOffset = 0f
            var lastViewPositionOffsetPixels = 0
            if (lastViewDistance != null) {
                if (layoutManager.canScrollHorizontally()) {
                    lastViewPositionOffsetPixels = lastViewDistance[0]
                    lastViewPositionOffset = lastViewDistance[0].toFloat() / snapView!!.getWidth()
                } else {
                    lastViewPositionOffsetPixels = lastViewDistance[1]
                    lastViewPositionOffset = lastViewDistance[1].toFloat() / snapView!!.getHeight()
                }
            }
            targetPositionOffset = Math.abs(lastViewPositionOffset)
            targetPositionOffsetPixels = Math.abs(lastViewPositionOffsetPixels)
        }

        onPageScrolled(targetPosition, targetPositionOffset, targetPositionOffsetPixels)
    }

    @CallSuper
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (currentPosition == RecyclerView.NO_POSITION) {
            return
        }

        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            onPageScrolled(currentPosition, 0f, 0)
        }
    }

    abstract fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)

    abstract fun onPageSelected(position: Int)

}