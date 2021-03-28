package com.limxtop.research.graphic

import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import com.limxtop.research.R
import com.limxtop.research.touch.LogUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ColorMatrixColorFilterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ColorMatrixColorFilterFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var mDst: ImageView
    private lateinit var mHue: SeekBar
    private lateinit var mSaturation: SeekBar
    private lateinit var mBrightness: SeekBar

    private val mColorMatrix: ColorMatrix = ColorMatrix()
    private val mHueMatrix: ColorMatrix = ColorMatrix()
    private val mSaturationMatrix: ColorMatrix = ColorMatrix()
    private val mBrightnessMatrix: ColorMatrix = ColorMatrix()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_matrix_color_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDst = view.findViewById(R.id.dst)
        mHue = view.findViewById(R.id.hue)
        mSaturation = view.findViewById(R.id.saturation)
        mBrightness = view.findViewById(R.id.brightness)

        mHue.setOnSeekBarChangeListener(this)
        mSaturation.setOnSeekBarChangeListener(this)
        mBrightness.setOnSeekBarChangeListener(this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ColorMatrixColorFilterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ColorMatrixColorFilterFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when(seekBar?.id) {

            R.id.hue -> {

            }

            R.id.saturation -> {
                updateDstImage(1f, 1f, progress as Float)
            }

            R.id.brightness -> {
                updateDstImage(1f, 1f, progress / 5f)
            }
        }
    }

    private fun updateDstImage(hueValue: Float, saturation: Float, brightness: Float) {

        mBrightnessMatrix.reset()
        mBrightnessMatrix.setScale(brightness, brightness, brightness, 1f)

        mColorMatrix.reset()
        mColorMatrix.postConcat(mBrightnessMatrix)
        mDst.colorFilter = ColorMatrixColorFilter(mColorMatrix)

        mDst.invalidate()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        LogUtils.d("ColorMatrix", "onStartTrackingTouch", "")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        //
        LogUtils.d("ColorMatrix", "onEndTrackingTouch", "")
    }
}