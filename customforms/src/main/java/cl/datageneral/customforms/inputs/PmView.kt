package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
open class PmView(fContext: Context, attrs: AttributeSet?=null):PmViewI, LinearLayout(fContext, attrs) {
    override fun displayWarning(msg: String) {}

    override fun checkRequired(): Boolean {
        return true
    }

    override var mandatory:Boolean        = false

    override var viewId: String = String()
    override val isValid: Boolean= true

    override var mainValue: String = String()
    //override fun setValue(value:String){}

    override var answer: JSONObject
        get() {
            return JSONObject().apply {
                put("view_id", viewId)
                put("value", mainValue)
            }
        }
        set(value) {
            mainValue =  value.getString("value")
        }
}

interface PmViewI{
    var viewId:String
    var mandatory:Boolean
    fun displayWarning(msg:String)
    fun checkRequired(): Boolean
    val isValid: Boolean
    //fun setValue(value:String)
    var answer: JSONObject
    var mainValue :String

}