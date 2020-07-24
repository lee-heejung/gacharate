package com.hj.gacharate

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // AdMob
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        var etRate:EditText = findViewById(R.id.etRate)
        var etNumber:EditText = findViewById(R.id.etNumber)
        var btnCalc:Button = findViewById(R.id.btnCalc)
        var tvRlt:TextView = findViewById(R.id.tvRlt)

        btnCalc.setOnClickListener {
            // Keyboard消す
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            // (1－(1－確率／100)^回数)＊100
            var div:Double = etRate.getText().toString().toDouble() / 100
            println(div)
            var rlt1:Double = 1 - div
            println(rlt1)

            var tmp:Double = rlt1
            var number:Int = etNumber.getText().toString().toInt()

            if (number > 1) {
                for (i in 1..number - 1) {
                    rlt1 = rlt1 * tmp
                }
            }

            var rlt2:Double = 1 - rlt1
            println(rlt2)

            var rlt3:Double = rlt2 * 100
            println(rlt3)
            var round = Math.round(rlt3 * 1000.0) / 1000.0
            tvRlt.setText(round.toString() + "%")
        }
    }

}
