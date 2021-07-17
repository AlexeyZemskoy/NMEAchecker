package com.example.nmeachecker

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.FILL_PARENT
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var scrollBox: ScrollView
    private var refPoint: Long = 0
    private fun addNewStateInfo(string: String) {
        val time = System.currentTimeMillis() - refPoint
        val list = scrollBox.findViewWithTag<LinearLayout>("list")

        try {
            val initializedNote = list.findViewWithTag<TextView>("initialized")
            initializedNote.text = "${initializedNote.text}: $time milliseconds"
            initializedNote.tag = "updated"
        }
        catch (ex: Exception) {
            println("Exception caught: $ex")
        }

        val updatedNote = TextView(this)
        updatedNote.text = string
        updatedNote.textSize = 20f
        updatedNote.tag = "initialized"
        list.addView(updatedNote)
    }
    private fun init(context: Context) {
        scrollBox = ScrollView(context)
        val list = LinearLayout(context)
        list.orientation = LinearLayout.VERTICAL
        list.tag = "list"
        list.setPadding(20, 20, 20, 20)

        val log = TextView(context)
        log.text = "State Log:"
        log.textSize = 22f
        log.setTextColor(Color.BLACK)

        list.addView(log)

        val buttonParams = RelativeLayout.LayoutParams(150, 50)
        buttonParams.setMargins(450, 20, 30, 30)
        val clearLog = Button(context)
        clearLog.text = "Clear Log"
        clearLog.layoutParams = buttonParams
        clearLog.setOnClickListener {
            val list = scrollBox.findViewWithTag<LinearLayout>("list")
            list.removeAllViews()
            val log = TextView(context)
            log.text = "State Log:"
            log.textSize = 22f
            log.setTextColor(Color.BLACK)
            list.addView(log)
        }

        val container = RelativeLayout(context)
        container.addView(clearLog)
        container.addView(list)
        scrollBox.addView(container)
        addContentView(scrollBox, LayoutParams(FILL_PARENT, FILL_PARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(this)
        addNewStateInfo("onCreate")
        refPoint = System.currentTimeMillis()
    }
}