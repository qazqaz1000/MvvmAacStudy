package com.nckim.mvvmaacstudy.views.main

interface ItemTouchHelperListener {
    fun onItemMove(fromPosition : Int, toPosition : Int) : Boolean
    fun onItemSwipe(position : Int)
}