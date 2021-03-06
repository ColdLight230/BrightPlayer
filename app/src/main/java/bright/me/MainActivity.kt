package bright.me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import bright.me.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = ffmpegConfigureInfo()
    }

    /**
     * A native method that is implemented by the 'me' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun ffmpegConfigureInfo(): String

    companion object {
        // Used to load the 'me' library on application startup.
        init {
            System.loadLibrary("me")
        }
    }
}