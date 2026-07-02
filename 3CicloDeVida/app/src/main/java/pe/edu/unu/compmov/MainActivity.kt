package pe.edu.unu.compmov

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.unu.compmov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSecondActivity.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        Log.i("LifeCycle","onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.i("LifeCycle","onStart")
        mediaPlayer = MediaPlayer.create(this, R.raw.super_mario)
    }

    override fun onResume() {
        super.onResume()
        Log.i("LifeCycle","onResume")
        mediaPlayer?.start()
        mediaPlayer?.seekTo(position)
    }

    override fun onPause() {
        super.onPause()
        Log.i("LifeCycle","onPause")
        mediaPlayer?.let{ player -> // ejecuta el bloque de codigo sobre la variable mediaPlayer
            player.pause()
            position = player.currentPosition
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i("LifeCycle","onStop")
        mediaPlayer?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle","onDestroy")
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LifeCycle","onRestart")
    }

}