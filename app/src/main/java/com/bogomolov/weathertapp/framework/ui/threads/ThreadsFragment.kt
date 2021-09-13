package com.bogomolov.weathertapp.framework.ui.threads

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.bogomolov.weathertapp.R
import com.bogomolov.weathertapp.databinding.FragmentThreadsBinding
import com.bogomolov.weathertapp.framework.services.MyForegroundService
import com.bogomolov.weathertapp.framework.services.UsualService
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import com.bogomolov.weathertapp.framework.services.BoundService.ServiceBinder
import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.os.IBinder
import android.content.ServiceConnection
import com.bogomolov.weathertapp.framework.services.BoundService


class ThreadsFragment : Fragment(), CoroutineScope by MainScope() {

    private var _binding: FragmentThreadsBinding? = null
    private val binding get() = _binding!!

    private var isBound = false
    private var boundService: ServiceBinder? = null

    private val testReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i("SERVICE", "FROM SERVICE:" +
                    " ${intent?.getBooleanExtra(MyForegroundService.INTENT_SERVICE_DATA,
                        false)}")
        }
    }

    // Обработка соединения с сервисом
    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        // При соединении с сервисом
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            boundService = service as ServiceBinder
            isBound = boundService != null
            Log.i("SERVICE", "BOUND SERVICE")
            Log.i("SERVICE", "next fibonacci: ${service.nextFibonacci}")
        }

        // При разрыве соединения с сервисом
        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            boundService = null
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(!isBound) {
            val bindServiceIntent = Intent(context, BoundService::class.java)
            activity?.bindService(bindServiceIntent, boundServiceConnection, BIND_AUTO_CREATE)
        }
        activity?.registerReceiver(testReceiver, IntentFilter(MyForegroundService.INTENT_ACTION_KEY))
    }

    override fun onStop() {
        activity?.unregisterReceiver(testReceiver)
        if(isBound) {
            activity?.unbindService(boundServiceConnection)
        }
        super.onStop()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val valueFromView = binding.editText.text.toString().toInt()
            launch {
                val job = async(Dispatchers.Default) {
                    startCalculations(valueFromView)
                }
                val result = job.await()
                binding.textView.text = result
                binding.mainContainer.addView(AppCompatTextView(it.context).apply {
                    text = getString(R.string.in_main_thread)
                    textSize = resources.getDimension(R.dimen.main_container_text_size)
                })
            }
        }

        val handlerThread = HandlerThread(getString(R.string.my_handler_thread))
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        handler.post({})

        UsualService.start(requireContext())
        MyForegroundService.start(requireContext())
    }

    private fun startCalculations(seconds: Int): String {
        val date = Date()
        var diffInSec: Long
        do {
            val currentDate = Date()
            val diffInMs: Long = currentDate.time - date.time
            diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs)
        } while (diffInSec < seconds)
        return diffInSec.toString()
    }

    companion object {
        fun newInstance() = ThreadsFragment()
    }
}