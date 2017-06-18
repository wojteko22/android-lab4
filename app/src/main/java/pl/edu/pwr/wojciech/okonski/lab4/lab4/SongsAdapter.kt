package pl.edu.pwr.wojciech.okonski.lab4.lab4

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class SongsAdapter(/*private val listener: OnItemClickListener*/) : RecyclerView.Adapter<SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.song_row, parent, false)
        return SongViewHolder(itemView/*, listener*/)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.title.text = song.title
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}