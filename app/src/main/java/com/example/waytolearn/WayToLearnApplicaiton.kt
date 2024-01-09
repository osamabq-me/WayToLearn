package com.example.waytolearn

import android.app.Application

class WayToLearnApplicaiton:Application() {

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}