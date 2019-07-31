package app.appworks.school.stylish.util

import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View

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
}
