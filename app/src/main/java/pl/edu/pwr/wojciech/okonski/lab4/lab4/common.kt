package pl.edu.pwr.wojciech.okonski.lab4.lab4

import android.widget.SeekBar

fun SeekBar.setOnProgressChangeByUserListener(f: (progress: Int) -> Unit) {
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            if (fromUser)
                f(progress)
        }
    })
}