package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var names= listOf<String>("地地","叉叉","菲菲","彭彭","辰子")
    lateinit var timer:Timer
    var index=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
     private fun init(){
        mNametextView.text=names[0]
        mstartbutton.text="START"
        mstartbutton.setOnClickListener{
            if(mstartbutton.text.toString()=="START") {
                mstartbutton.text = "STOP"
                timer = Timer()
                timer.schedule(object :TimerTask(){
                    override fun run(){
                        index ==if(index+1>names.size-1) index=0 else index++
                        Log.v("pxd","$index")
                        mNametextView.text=names[index]
                    }
                },0,100)
            }else {
                mstartbutton.text = "START"
                timer.cancel()
            }
        }
    }


























}