package byvto.ru.calmsoul

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageLogotype: ImageView = findViewById(R.id.imageLogotype)
        imageLogotype.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.tollev2)
        )
        imageLogotype.setOnClickListener {
            val contentScreen: ImageView = findViewById(R.id.contentScreen)
            val random = List(4) { Random.nextInt(0, 255) }
            val randomText: TextView = findViewById(R.id.randomText)
            randomText.setText(random[0].toString() + random[1].toString() + random[2].toString() + random[3].toString())
            val colorScreen = Color.argb(random[0], random[1], random[2], random[3])
            contentScreen.setBackgroundColor(colorScreen)
        }
    }
}