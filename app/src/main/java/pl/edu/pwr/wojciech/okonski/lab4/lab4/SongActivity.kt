package pl.edu.pwr.wojciech.okonski.lab4.lab4

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_song.*


class SongActivity : AppCompatActivity() {
    private val song: Song by lazy {
        val songIndex = intent.extras.getInt(SONG_INDEX)
        songs[songIndex]
    }
    private val mediaPlayer: MediaPlayer by lazy { MediaPlayer.create(this, song.audioResource) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        prepareSongStuff()
    }

    private fun prepareSongStuff() {
        ivAlbumCover.setImageResource(song.coverResource)
        ibPlayPause.setOnClickListener { playOrPause() }
        seekBar.max = mediaPlayer.duration

        val handler = Handler()
        runOnUiThread(object : Runnable {
            override fun run() {
                val currentPosition = mediaPlayer.currentPosition / 1000
                seekBar.progress = currentPosition
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun playOrPause() {
        if (mediaPlayer.isPlaying)
            pause()
        else play()
    }

    private fun pause() {
        mediaPlayer.pause()
        ibPlayPause.setImageResource(android.R.drawable.ic_media_play)
    }

    private fun play() {
        mediaPlayer.start()
        ibPlayPause.setImageResource(android.R.drawable.ic_media_pause)
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }

    companion object {
        private val SONG_INDEX = "song index"

        fun start(context: Context, songIndex: Int) {
            val intent = Intent(context, SongActivity::class.java)
            intent.putExtra("song index", songIndex)
            context.startActivity(intent)
        }
    }
}
