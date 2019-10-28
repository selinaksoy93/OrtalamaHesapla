package com.example.myapplicationortalamahesapla

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var downtoupbutton = AnimationUtils.loadAnimation(this, R.anim.asagidangelenbuton)
        var uptodownballoon = AnimationUtils.loadAnimation(this, R.anim.yukaridangelenbalon)
        var uptodownbutton = AnimationUtils.loadAnimation(this, R.anim.yukariyagidenbalon)
        var downtoupballoon = AnimationUtils.loadAnimation(this, R.anim.asagiyagidenbuton)


        buttonfirst.animation = downtoupbutton
        fare.animation = uptodownballoon

        buttonfirst.setOnClickListener {
            buttonfirst.startAnimation(uptodownbutton)
            fare.startAnimation(downtoupballoon)

         object : CountDownTimer(1000,1000){
             override fun onFinish() {
                 var intent = Intent(applicationContext,MainActivity::class.java)
                 startActivity(intent)

             }

             override fun onTick(millisUntilFinished: Long) {

          }


         }
             .start()
        }
    }


}
