package com.example.bai7_roomdatabase

import android.app.Application

class ContactApplication : Application(){
    override fun onCreate(){
        super.onCreate()
        Graph.provide(this)
    }
}