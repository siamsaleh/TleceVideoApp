package com.schooling.telecevideoapp.service

import android.content.Context
import android.net.wifi.WifiManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
       Worker(appContext, workerParams) {
   override fun doWork(): Result {

       ContextCompat.getMainExecutor(applicationContext).execute {

           /*Toast.makeText(applicationContext, "Work Manager Started...", Toast.LENGTH_SHORT).show()*/
           val wifiManager: WifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

           if (wifiManager.isWifiEnabled){
               /*Toast.makeText(applicationContext, "WIFI enable", Toast.LENGTH_SHORT).show()*/
           }else{
               /*Toast.makeText(applicationContext, "Wifi Disable", Toast.LENGTH_SHORT).show()*/
           }
       }

       return Result.success()
   }
}
