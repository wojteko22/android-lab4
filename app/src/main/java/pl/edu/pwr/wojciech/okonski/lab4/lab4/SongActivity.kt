package pl.edu.pwr.wojciech.okonski.lab4.lab4

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_song.*


class SongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        val songIndex = intent.extras.getInt(SONG_INDEX)
        val song = songs[songIndex]
        val coverResource = song.coverResource
        ivAlbumCover.setImageResource(coverResource)

        val mediaPlayer = MediaPlayer.create(this, song.audioResource)
        mediaPlayer.start()
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
