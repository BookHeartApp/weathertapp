package com.bogomolov.weathertapp.framework.services

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService

class ServiceWithThread : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        //Фоновый поток
        println("JOB SERVICE WORK IN THREAD")
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ServiceWithThread::class.java)
            enqueueWork(context, ServiceWithThread::class.java, 3322, intent)
        }
    }
}