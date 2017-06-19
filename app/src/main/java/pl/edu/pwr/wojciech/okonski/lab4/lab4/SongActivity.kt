package pl.edu.pwr.wojciech.okonski.lab4.lab4

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_song.*
import kotlinx.android.synthetic.main.content_song.*


class SongActivity : AppCompatActivity() {
    private val song: Song by lazy {
        val songIndex = intent.extras.getInt(SONG_INDEX)
        songs[songIndex]
    }
    private val mediaPlayer: MediaPlayer by lazy { MediaPlayer.create(this, song.audioResource) }
    private var mediaPlayerReleased = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        prepareSongStuff()

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun prepareSongStuff() {
        ivAlbumCover.setImageResource(song.coverResource)
        prepareButtons()
        prepareSeekBarStuff()
    }

    private fun prepareButtons() {
        ibPlayPause.setOnClickListener { playOrPause() }
        ibBackwards.setOnClickListener { moveAudio(-SONG_DELTA_MILLIS) }
        ibForward.setOnClickListener { moveAudio(SONG_DELTA_MILLIS) }
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

    private fun moveAudio(delta: Int) {
        val currentPosition = mediaPlayer.currentPosition
        mediaPlayer.seekTo(currentPosition + delta)
    }

    private fun prepareSeekBarStuff() {
        seekBar.max = mediaPlayer.duration
        seekBar.setOnProgressChangeByUserListener { mediaPlayer.seekTo(it) }
        autoUpdateSeekBarIfMediaPlayerNotReleased()
    }

    private fun autoUpdateSeekBarIfMediaPlayerNotReleased() {
        if (!mediaPlayerReleased)
            autoUpdateSeekBar()
    }

    private fun autoUpdateSeekBar() {
        seekBar.progress = mediaPlayer.currentPosition
        seekBar.postDelayed({ autoUpdateSeekBarIfMediaPlayerNotReleased() }, 200)
    }


    override fun onStop() {
        super.onStop()
        mediaPlayer.release()
        mediaPlayerReleased = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        pause()
        outState.putInt("possition", mediaPlayer.currentPosition)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val position = savedInstanceState.getInt("possition")
        mediaPlayer.seekTo(position)
    }

    companion object {
        private val SONG_DELTA_MILLIS = 10000
        private val SONG_INDEX = "song index"

        fun start(context: Context, songIndex: Int) {
            val intent = Intent(context, SongActivity::class.java)
            intent.putExtra("song index", songIndex)
            context.startActivity(intent)
        }
    }
}
