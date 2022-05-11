package org.d3if0031.diary.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.d3if0031.diary.util.Constant.DAY_IN_SECONDS
import org.d3if0031.diary.util.Constant.HOUR_IN_SECONDS
import org.d3if0031.diary.util.Constant.MINUTE_IN_SECONDS
import org.d3if0031.diary.util.Constant.MONTH_IN_SECONDS
import org.d3if0031.diary.util.Constant.WEEK_IN_SECONDS
import org.d3if0031.diary.util.Constant.YEAR_IN_SECONDS

@BindingAdapter("app:timestamp")
fun formatTimestamp(textView: TextView, timestamp: Long) {
    val seconds = (System.currentTimeMillis() - timestamp) / 1000
    textView.text = when {
        (seconds < MINUTE_IN_SECONDS) -> "$seconds seconds ago"
        (seconds < HOUR_IN_SECONDS) -> "${seconds / MINUTE_IN_SECONDS} minutes ago"
        (seconds < DAY_IN_SECONDS) -> "${seconds / HOUR_IN_SECONDS} hours ago"
        (seconds < WEEK_IN_SECONDS) -> "${seconds / DAY_IN_SECONDS} days ago"
        (seconds < MONTH_IN_SECONDS) -> "${seconds / WEEK_IN_SECONDS} weeks ago"
        (seconds < YEAR_IN_SECONDS) -> "${seconds / MONTH_IN_SECONDS} months ago"
        else -> "${seconds / YEAR_IN_SECONDS} years ago"
    }
}
