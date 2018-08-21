package com.andresfernandeznadalesgmail.robotinkotlin

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var color: Int = 0xFFFFFFFF.toInt()
    var imageChanged: Boolean = false

    private inner class myL: View.OnClickListener {

        override fun onClick(v: View) {
            when (v) {
                buttonRed -> {
                    //color = editText.text.toString().toInt()
                    val sn = editText.text.toString()
                    var ctl = sn.substring(2).toLong(16)
                    color = ctl.toInt()
                }
                imageView -> {
                    imageChanged = true
                    imageView.setImageResource(R.mipmap.ic_launcher_round)
                    if (radioButtonBlue.isChecked) color = Color.BLUE
                    if (radioButtonGreen.isChecked) color = Color.GREEN
                }
            }
            textView.setBackgroundColor(color)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            imageChanged=savedInstanceState.getBoolean("imgChanged", false)
            color=savedInstanceState.getInt("Color", color)
            textView.setBackgroundColor(color)
            if (imageChanged)imageView.setImageResource(R.mipmap.ic_launcher_round)
        }
        val myListener = myL()
        buttonRed.setOnClickListener(myListener)
        imageView.setOnClickListener(myListener)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("Color", color)
        outState?.putBoolean("imgChanged", imageChanged)
    }
}
