package com.limxtop.research.graphic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.limxtop.research.R
import com.limxtop.research.utils.RLog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BitmapTransitionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BitmapTransitionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var mImageView: ImageView? = null
    private var mTransition: Button? = null
    private var mBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bitmap_transition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImageView = view.findViewById<ImageView>(R.id.image_view)
        mTransition = view.findViewById(R.id.transition)
        mTransition?.setOnClickListener({ view -> transition(view)})
    }

    private fun decodeBitmap() {
        RLog.d("hello2")
        val source: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat)
        val width: Int? = mImageView?.width
        val measureWidth: Int = mImageView!!.measuredWidth
        val measureHeight: Int = mImageView!!.measuredHeight
        val translateM: Matrix = Matrix()
        val dx: Float = 0f
        val dy: Float = (mImageView!!.measuredHeight - source.height) / 2f
        translateM.postTranslate(dx, dy)


        val mapM: Matrix  = mImageView!!.getImageMatrix()
        val viewRect: RectF = RectF(0f, 0f, measureWidth.toFloat(), measureHeight.toFloat())
        val drawableRect: RectF = RectF(0f, 0f, source.width.toFloat(), source.height.toFloat())
        mapM.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.START);
        mapM.postRotate(90f)
        // M' = T(dx, dy) * M, the M matrix takes effect first and then the translate matrix.
        mapM.postTranslate(dx, dy)
        mImageView!!.setImageMatrix(mapM)
//        mImageView?.setImageMatrix(matrix)
        mImageView?.setImageBitmap(source)
    }

    private fun transition(view: View) {
        decodeBitmap()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BitmapTransitionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                BitmapTransitionFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}