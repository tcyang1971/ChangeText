package tw.edu.pu.csim.tcyang.changetext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var btnA: Button
    lateinit var btnB: Button
    lateinit var txv: TextView
    var size: Float = 20f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txv = findViewById(R.id.txv)
        txv.textSize = size
        txv.setOnClickListener(object:OnClickListener{
            override fun onClick(p0: View?) {
                txv.text = "短按"
            }

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