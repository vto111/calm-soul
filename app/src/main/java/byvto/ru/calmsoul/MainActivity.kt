package byvto.ru.calmsoul

import android.graphics.Color
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.IOException
import kotlin.random.Random

class MainActivity(var fileOggName: String = "ogg/0.ogg", var list: Array<out String>? = emptyArray(), var status: String = "start") : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val imageLogotype: ImageView = findViewById(R.id.imageLogotype)
            imageLogotype.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.tollev_white)
            )

            list = assets.list("ogg")
            setNameOggFile()
            val afd = assets.openFd(fileOggName)
            println(fileOggName)
            val player = MediaPlayer()
            player.setDataSource(afd.fileDescriptor,afd.startOffset,afd.length);
            player.prepare()
            clickLogo(imageLogotype, player)

        } catch (e: IOException) {
            Log.v("List error:", "can't list")
        }

    }

    fun initMPlayer(): MediaPlayer {
        println(fileOggName)
        val afd = assets.openFd(fileOggName)
        val player = MediaPlayer()
        player.reset()
        player.setDataSource(afd.fileDescriptor,afd.startOffset,afd.length);
        player.prepare()
        return player
    }

    fun setNameOggFile(){
        val countOgg = list?.count().toString()
        val fileNumberRandom = List(2) { Random.nextInt(0, countOgg.toInt()) }
        if(fileNumberRandom[0].toString() !== fileOggName || status == "start"){
            fileOggName  = "ogg/" + fileNumberRandom[0] + ".ogg"
        }
        else{
            fileOggName  = "ogg/" + fileNumberRandom[1] + ".ogg"
        }

    }
    fun clickLogo(imageLogotype: ImageView, player: MediaPlayer){
        imageLogotype.setOnClickListener {
            if(status != "start"){
                setNameOggFile()
                val player = initMPlayer()
                player.start()
                player.setOnCompletionListener(OnCompletionListener {
                    setBackGround()
                })
            } else {
                player.start()
                player.setOnCompletionListener(OnCompletionListener {
                    status = ""
                    setBackGround()
                })
            }

        }
    }

    fun setBackGround(){
        val contentScreen: ImageView = findViewById(R.id.contentScreen)
        val random = List(4) { Random.nextInt(0, 255) }
        val randomText: TextView = findViewById(R.id.randomText)
        randomText.setText(":selectSound" + random[0].toString() + random[1].toString() + random[2].toString() + random[3].toString())
        val colorScreen = Color.argb(random[0], random[1], random[2], random[3])
        contentScreen.setBackgroundColor(colorScreen)
    }

}