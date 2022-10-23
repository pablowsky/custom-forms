package cl.datageneral.customforms.helpers

import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils


/**
 * Created by Pablo Molina on 23-10-2022. s.pablo.molina@gmail.com
 */
/*class InputFilterMinMax(private var min: Double, private var max: Double) : InputFilter {
    //    constructor(min:String, max:String) {
//        this.min = Integer.parseInt(min)
//        this.max = Integer.parseInt(max)
//    }
    override fun filter(source:CharSequence, start:Int, end:Int, dest: Spanned, dstart:Int, dend:Int): CharSequence? {

        try {

            Log.e("Filter", "$dest - $source")
            val newVal = dest.toString() + source.toString()
            val input = newVal.toDouble()

            if (newVal.matches("0\\d+.*".toRegex()))
                return if (TextUtils.isEmpty(source))
                    dest.subSequence(dstart, dend)
                else
                    ""

            // check if there are leading zeros
            //val input = Double.(dest.toString() + source.toString())
            //val input = newVal.toDouble()
            if (isInRange( min, max, input ) )
                return if (TextUtils.isEmpty(source)) dest.subSequence(dstart, dend) else null
//            if (isInRange(min, max, input))
//                return null
        } catch (nfe:NumberFormatException) {}
        return ""
    }
    private fun isInRange(a:Double, b:Double, c:Double):Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}*/
class MinMaxInputFilter : InputFilter {
    private var mMinValue: Double
    private var mMaxValue: Double

    constructor(min: Double?, max: Double?) {
        mMinValue = min ?: MIN_VALUE_DEFAULT
        mMaxValue = max ?: MAX_VALUE_DEFAULT
    }

    constructor(min: Int?, max: Int?) {
        mMinValue = min?.toDouble() ?: MIN_VALUE_DEFAULT
        mMaxValue = max?.toDouble() ?: MAX_VALUE_DEFAULT
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val replacement = source.subSequence(start, end).toString()
            val newVal = (dest.subSequence(0, dstart).toString() + replacement
                    + dest.subSequence(dend, dest.length).toString())

            // check if there are leading zeros
            if (newVal.matches("0\\d+.*".toRegex())) return if (TextUtils.isEmpty(source)) dest.subSequence(
                dstart,
                dend
            ) else ""

            // check range
            val input = newVal.toDouble()
            return if (!isInRange(
                    mMinValue,
                    mMaxValue,
                    input
                )
            ) if (TextUtils.isEmpty(source)) dest.subSequence(dstart, dend) else "" else null
        } catch (nfe: NumberFormatException) {
            //LOGE("inputfilter", "parse")
        }
        return ""
    }

    private fun isInRange(a: Double, b: Double, c: Double): Boolean {
        return if (b > a) c >= a && c <= b else c >= b && c <= a
    }

    companion object {
        private const val MIN_VALUE_DEFAULT = Double.MIN_VALUE
        private const val MAX_VALUE_DEFAULT = Double.MAX_VALUE
    }
}