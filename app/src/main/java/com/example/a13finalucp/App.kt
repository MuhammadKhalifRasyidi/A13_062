package com.example.a13finalucp

import android.app.Application
import com.example.a13finalucp.DI.AppContainer
import com.example.a13finalucp.DI.ContainerApplication

class App : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = ContainerApplication()
    }
}