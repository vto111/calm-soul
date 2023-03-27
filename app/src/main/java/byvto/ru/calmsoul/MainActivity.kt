package byvto.ru.calmsoul

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.IOException
import kotlin.random.Random

class MainActivity(var fileOggName: String = "0", var list: Array<out String>? = emptyArray(), var randomNumbers: List<out Int>? = emptyList()) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val imageLogotype: ImageView = findViewById(R.id.imageLogotype)
            imageLogotype.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.tollev_white)
            )
            getRadom()
            clickLogo(imageLogotype, initMPlayer())

        } catch (e: IOException) {
            Log.v("List error:", "can't list")
        }

    }
    fun getRadom() {
        val countOgg = list?.count()?.toString()
        countOgg?.let {
            randomNumbers = List(2) { Random.nextInt(0, 3) }
            println(it.toInt())

        }

    }
    fun setFileOgg(){
        if("ogg/" + randomNumbers!![0] + ".ogg" != fileOggName){
            fileOggName = "ogg/" + randomNumbers!![0] + ".ogg"
        } else {
            fileOggName = "ogg/" + randomNumbers!![1] + ".ogg"
        }
    }
    private fun initMPlayer(): MediaPlayer {
        setFileOgg()
        val player = MediaPlayer()
        setFileName(player)
        return player
    }

    fun setFileName(player: MediaPlayer) {
        val afd = assets.openFd(fileOggName)
        player.reset()
        player.setDataSource(afd.fileDescriptor,afd.startOffset,afd.length)
        player.prepare()
    }

    private fun clickLogo(imageLogotype: ImageView, player: MediaPlayer){
        imageLogotype.setOnClickListener {

            if (!player.isPlaying){
                setBackGround()
                player.start()
            } else {
                player.stop()
                getRadom()
                setFileOgg()
                setFileName(player)
                println(fileOggName)

            }
        }
    }

    fun setBackGround(){
        val contentScreen: ImageView = findViewById(R.id.contentScreen)
        val random = List(4) { Random.nextInt(0, 255) }
        val randomText: TextView = findViewById(R.id.randomText)
        randomText.setText(random[0].toString() + random[1].toString() + random[2].toString() + random[3].toString())
        val colorScreen = Color.argb(random[0], random[1], random[2], random[3])
//        contentScreen.setBackgroundColor(colorScreen)
        contentScreen.setBackgroundColor(colorScreen)

    }

}