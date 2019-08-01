package app.appworks.school.stylish.util

import android.content.Context
import android.graphics.Rect
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.TouchDelegate
import android.view.View
import app.appworks.school.stylish.StylishApplication

/**
 * Updated by Wayne Chen in Mar. 2019.
 */
object Util {

    /**
     * Increase touch area of the view/button .
     * @param view
     */
    fun setTouchDelegate(view: View) {
        val parent = view.parent as View  // button: the view you want to enlarge hit area
        parent.post {
            val rect = Rect()
            view.getHitRect(rect)
            rect.top -= 100    // increase top hit area
            rect.left -= 100   // increase left hit area
            rect.bottom += 100 // increase bottom hit area
            rect.right += 100  // increase right hit area
            parent.touchDelegate = TouchDelegate(rect, view)
        }
    }

    /**
     * Determine and monitor the connectivity status
     *
     * https://developer.android.com/training/monitoring-device-state/connectivity-monitoring
     */
    fun isInternetConnected(): Boolean {
        val cm = StylishApplication.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getString(resourceId: Int): String {
        return StylishApplication.instance.getString(resourceId)
    }
}
