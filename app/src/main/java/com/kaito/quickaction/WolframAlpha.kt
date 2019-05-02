package com.kaito.quickaction

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WolframAlpha : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread(Runnable {
            val searchTarget = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wolframalpha.com/input/?i=$searchTarget")))
        }).start()

        finish()
    }
}
