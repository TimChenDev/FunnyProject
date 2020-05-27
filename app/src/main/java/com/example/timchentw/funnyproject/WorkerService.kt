package com.example.timchentw.funnyproject

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.need.path.to.IWorker
import com.need.path.to.IWorkerCallback

/**
 *  author: Tim Chen
 *  time  : 2020-04-30
 *  desc  :
 */
class WorkerService: Service() {

    private var callback: IWorkerCallback? = null

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    /**
     *  在這裏實作工具人要做的事情
     */
    private val mBinder: Binder = object: IWorker.Stub() {

        override fun cleanRoom() {

            // 打掃房間
            Log.v("WorkerService", "打掃房間中")

            Log.v("WorkerService", "打掃完房間了")

            // 打掃完畢, 透過聯絡方式通知對方說我打掃完了
            Log.v("WorkerService", "欸，我打掃完了")
            callback?.finishCleanRoom()
        }

        override fun buildComputer(_order: String?) {
            // 組電腦
            Log.v("WorkerService", "組電腦中")

            // 組完電腦, 透過聯絡方式通知對方說我組完電腦了
            Log.v("WorkerService", "組完電腦了")
            callback?.finishComputer()
        }

        override fun addLine(_callback: IWorkerCallback?) {
            // 加Line, 留下聯絡方式, 之後都透過這個聯絡方式跟對方說話
            callback = _callback
        }
    }
}