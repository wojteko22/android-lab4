package pl.edu.pwr.wojciech.okonski.lab4.lab4

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.song_row.view.*

class SongViewHolder(view: View/*, listener: OnItemClickListener*/) : RecyclerView.ViewHolder(view) {
    val albumCover: ImageView = view.ivAlbumCover
    val title: TextView = view.tvTitle

    init {
        /*view.setOnClickListener { listener.onItemClick(adapterPosition) }
        view.setOnLongClickListener {
            consume { listener.onItemLongClick(adapterPosition) }
        }*/
    }

    fun setSongInfo(song: Song) {
        title.text = song.title
        albumCover.setImageResource(song.coverResource)
    }
}

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}