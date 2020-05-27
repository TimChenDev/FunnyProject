package com.example.timchentw.funnyproject

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.need.path.to.IWorker
import com.need.path.to.IWorkerCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var worker: IWorker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEvent()

        bindWorkerService()
    }

    private fun bindWorkerService() {
        val intent = Intent(this, WorkerService::class.java)
        bindService(intent, mWorkerConnection, Context.BIND_AUTO_CREATE)
    }

    /**
     * 建立與工具人之間的連結, 與取消連結
     */
    private val mWorkerConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

            // 跟工具人要聯絡資訊
            worker = IWorker.Stub.asInterface(service)
            // 加好友建立聯絡方式(Callback)
            worker?.addLine(mWorkerCallback)
        }

        override fun onServiceDisconnected(name: ComponentName?) {

            // 斷開聯絡方式
            worker = null
        }
    }

    /**
     * 工具人說話的餘地, 實例化 callback
     */
    private val mWorkerCallback = object : IWorkerCallback.Stub() {
        override fun finishComputer() {
            Toast.makeText(baseContext, "工具人：組完電腦了, 要一起去吃飯嗎", Toast.LENGTH_SHORT).show()
        }

        override fun finishCleanRoom() {
            Toast.makeText(baseContext, "工具人：房間打掃完了, 要一起去吃飯嗎", Toast.LENGTH_SHORT).show()

        }
    }

    private fun initEvent() {
        btn_start_clean_room.setOnClickListener {

            // 叫工具人去打掃房間
            worker?.cleanRoom()
        }

        btn_build_computer.setOnClickListener {

            // 叫工具人去組電腦
            worker?.buildComputer("i5 8450")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // 工具人用完要刪除好友
        unbindService(mWorkerConnection)
    }
}
