package pl.edu.pwr.wojciech.okonski.lab4.lab4

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.song_row.view.*

class SongViewHolder(private val view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
    private val albumCover: ImageView = view.ivAlbumCover
    private val title: TextView = view.tvTitle

    fun init(song: Song) {
        title.text = song.title
        albumCover.setImageResource(song.coverResource)
        view.setOnClickListener {
            SongActivity.start(context, adapterPosition)
        }
    }
}
