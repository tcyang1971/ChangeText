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
    }
}