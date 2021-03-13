package com.limxtop.research.animator.advance

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.TextView
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
        val reveal = view.findViewById<TextView>(R.id.hide)
        reveal.setOnClickListener(View.OnClickListener {
            reveal: View -> hide(reveal)
           })
    }

    private fun hide(view: View) {
        val cx: Int = view.width / 2
        val cy: Int = view.height / 2
        val originalRadius: Float = hypot(cx.toDouble(), cy.toDouble()).toFloat()
        val animator: Animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, originalRadius, 0f).also {
//            it.addListener(AnimatorListenerAdapter)
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