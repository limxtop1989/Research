package com.limxtop.research.graphic

import android.graphics.*
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.limxtop.research.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [XfermodeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class XfermodeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var mCat: ImageView? = null
    private var mAction: Button? = null

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
        return inflater.inflate(R.layout.fragment_xfermode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCat = view.findViewById<ImageView>(R.id.src)
        mAction = view.findViewById(R.id.action)
        mAction?.setOnClickListener { view: View ->
            run {
                action(view)
            }
        }
    }

    private fun action(view: View) {
        val maskBitmap: Bitmap = Bitmap.createBitmap(mCat!!.measuredWidth, mCat!!.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(maskBitmap)

        val vectorDrawable: VectorDrawable = resources.getDrawable(R.drawable.heart) as VectorDrawable
        vectorDrawable.setBounds(0, 0, mCat!!.measuredWidth, mCat!!.measuredHeight)
        vectorDrawable.draw(canvas)

        val resultBitmap: Bitmap = Bitmap.createBitmap(mCat!!.measuredWidth, mCat!!.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas2: Canvas = Canvas(resultBitmap)
        val paint: Paint = Paint()
        val source: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat)
        canvas2.drawBitmap(maskBitmap, 0f, 0f, paint)

        val mode: PorterDuff.Mode =  PorterDuff.Mode.SRC_IN// choose a mode
        paint.xfermode = PorterDuffXfermode(mode)

        canvas2.drawBitmap(source, 0f, 0f, paint)
        mCat?.setImageBitmap(resultBitmap)

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment XfermodeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                XfermodeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}