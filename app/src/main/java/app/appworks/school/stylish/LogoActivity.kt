package app.appworks.school.stylish

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import app.appworks.school.stylish.databinding.ActivityLogoBinding

/**
 * Created by Wayne Chen in Jul. 2019.
 */
class LogoActivity : BaseActivity() {

    private lateinit var binding: ActivityLogoBinding
    private val totalDuration = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_logo)

        Handler().postDelayed({

            setLogoAnimation()

            Handler().postDelayed({

                finish()

            }, (totalDuration / 3 * 2).toLong())

        }, (totalDuration / 3).toLong())
    }

    public override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onBackPressed() {}

    private fun setLogoAnimation() {
        val duration = totalDuration / 3 * 2

        val rotateAnimation = RotateAnimation(0f, 1800f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = duration.toLong()
        rotateAnimation.fillAfter = true

        val translateAnimation = TranslateAnimation(0f, 0f,
                0f, resources.getDimensionPixelSize(R.dimen.duration_logo_translate).toFloat())
        translateAnimation.duration = duration.toLong()
        translateAnimation.fillAfter = true

        val alphaAnimation = AlphaAnimation(1f, 0f)
        alphaAnimation.duration = duration.toLong()
        alphaAnimation.fillAfter = true

        val scaleAnimation = ScaleAnimation(1f, 0.5f, 1f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = duration.toLong()
        scaleAnimation.fillAfter = true

        val animationSet = AnimationSet(false)
        animationSet.addAnimation(rotateAnimation)
        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(alphaAnimation)
        animationSet.addAnimation(scaleAnimation)
        animationSet.fillAfter = true

        binding.imageLogo.startAnimation(animationSet)
    }
}
