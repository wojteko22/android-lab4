package pl.edu.pwr.wojciech.okonski.lab4.lab4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter: RecyclerView.Adapter<SongViewHolder> by lazy { SongsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}

val songs = arrayListOf(
        Song("The Messenger", R.drawable.ats, R.raw.the_messenger),
        Song("Pancerz", R.drawable.kielbie, R.raw.pancerz),
        Song("Kiedy Umieram", R.drawable.medeis, R.raw.kiedy_umieram),
        Song("I Hate Everything About You", R.drawable.tdg, R.raw.i_hate_everything_about_you)
)