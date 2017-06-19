package pl.edu.pwr.wojciech.okonski.lab4.lab4

import android.widget.SeekBar

fun SeekBar.setOnProgressChangeListener(f: (progress: Int, fromUser: Boolean) -> Unit) {
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            f(progress, fromUser)
        }
    })
}