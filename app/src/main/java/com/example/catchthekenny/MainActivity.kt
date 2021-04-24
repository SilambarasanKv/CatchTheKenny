package com.example.catchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

var score: Int = 0
var imageArray = ArrayList<ImageView> ()
var handler: Handler = Handler()
var runnable: Runnable = Runnable {  }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = 0

        imageArray = arrayListOf(imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9)

        hideimages()

        object : CountDownTimer(15000, 1000) {
            override fun onFinish() {
                txvTime.text = "Time: 0"
                handler.removeCallbacks(runnable)
                    for (image in imageArray) {
                        image.visibility = View.INVISIBLE
                    }
            }

            override fun onTick(millisUntilFinished: Long) {
                txvTime.text = "Time: " + millisUntilFinished / 1000
            }

        }.start()

    }

    fun hideimages() { //

        runnable = object : Runnable {
            override fun run() {

                for (image in imageArray) { //first
                    image.visibility = View.INVISIBLE
                }

                var random = Random()
                val index = random.nextInt(8 - 0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)
            }

        }

        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score++
        txvScore.text = "Score: " + score
    }

}