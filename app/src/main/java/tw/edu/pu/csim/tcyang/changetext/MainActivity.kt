package tw.edu.pu.csim.tcyang.changetext

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var btnA: Button
    lateinit var btnB: Button
    lateinit var txv: TextView
    var size: Float = 20f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var img: ImageView = findViewById(R.id.img)
        img.setOnTouchListener(object:OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val vibrator =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        // API 31以上
                        val vibratorManager = getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
                        vibratorManager.defaultVibrator
                    }
                    else {  //API < 31
                        getSystemService(VIBRATOR_SERVICE) as Vibrator
                    }

                if (event?.action == MotionEvent.ACTION_DOWN){
                    txv.text = "手指按下"
                    //持續震動5秒
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        //API >= 26
                        vibrator.vibrate(
                            VibrationEffect.createOneShot(5000,
                            VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        vibrator.vibrate(5000)
                    }
                }
                else if(event?.action == MotionEvent.ACTION_UP){
                    txv.text = "手指彈開"
                    vibrator.cancel()
                }
                return true
            }
        })

        txv = findViewById(R.id.txv)
        txv.textSize = size
        txv.setOnClickListener({
                txv.text = "Lambda短按"
        })

        txv.setOnLongClickListener({
            txv.text = "Lambda長按"
            true //可以試試看false會長按後觸發短按
        })

        btnA = findViewById(R.id.btnA)
        btnA.setOnClickListener(this)

        btnB = findViewById(R.id.btnB)
        btnB.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == btnA) {
            size++
        }
        else{
            size--
        }
        txv.textSize = size

        if (size >= 30) { btnA.isEnabled = false }
        else if (size <= 10) { btnB.isEnabled = false }
        else{
            btnA.isEnabled = true
            btnB.isEnabled = true
        }
    }
}