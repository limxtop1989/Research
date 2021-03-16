package com.limxtop.research.animator.advance
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.Button
import com.limxtop.research.R
import kotlin.math.hypot

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [SlidePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SlidePageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    private var isViewHidden: Boolean = false
    private var button: Button? = null
    private var animationView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.button)
        updateButtonText()
        animationView = view.findViewById(R.id.circular_animation)

        // TODO: Why this doesn't work.
        button?.setOnClickListener(View.OnClickListener() { _ ->
            {
                circularAnimate(animationView!!)
            }
        })
        button?.setOnClickListener(View.OnClickListener() { _ -> circularAnimate(animationView!!) })
    }

    private val clickListener = View.OnClickListener { view ->
        circularAnimate(animationView!!)
    }

    private fun updateButtonText() {
        if (isViewHidden) {
            button?.text = "Reveal"
        } else {
            button?.text = "Hide"
        }
    }

    private fun circularAnimate(view: View) {
        val cx: Int = view.width / 2
        val cy: Int = view.height / 2
        val startRadius: Float = if (isViewHidden) { 0f } else { hypot(cx.toDouble(), cy.toDouble()).toFloat() }
        val endRadius: Float = if (isViewHidden) { hypot(cx.toDouble(), cy.toDouble()).toFloat() } else { 0f }
        val animator: Animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, startRadius, endRadius).also {
            it.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    animationView?.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    animationView?.visibility = if (isViewHidden) View.VISIBLE else View.GONE
                    isViewHidden = !isViewHidden
                    updateButtonText()
                }
            })
            it.duration = 2000
            it.start()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScreenSlidePageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String,) =
                SlidePageFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}