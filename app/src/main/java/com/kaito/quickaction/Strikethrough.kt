package com.kaito.quickaction

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Strikethrough : AppCompatActivity(){
    private val strike = '\u0336'
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread(Runnable {
            val searchTarget = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("STRIKETHROUGH", modifyText(searchTarget))
            clipboard.primaryClip = clip
        }).start()

        finish()
    }

    private fun modifyText(s : String): String {
        val split = s.map { it.toString() }.toMutableList()
        var text = ""
        for (i in 0 until split.size){
            text = text + split[i] + strike
        }
        return text
    }
}
