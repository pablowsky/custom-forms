package cl.datageneral.customforms.factory.custominputs

import android.util.Log
import cl.datageneral.customforms.factory.ViewTypes
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
abstract class InputBase:InputBaseI {
    override var mandatory: Boolean = false
    override var readOnly: Boolean  = false
    override var title:String       = String()
    override var warningMessage:String       = String()
    override var viewId: String     = String()
    override var value:String       = String()
    override var answer: JSONObject
        get() {
            return JSONObject().apply {
                put("view_id", viewId)
                put("value", value)
            }
        }
        set(value) {
            this@InputBase.value =  value.getString("value")
        }

    override fun setValue(value: String, subtype: String) {}
}

interface InputBaseI{
    var readOnly:Boolean
    var title:String
    var warningMessage:String
    var mandatory:Boolean
    var vtype:ViewTypes
    var viewId:String
    var value:String
    val isValid:Boolean
    val answer:JSONObject
    fun setValue(value:String, subtype:String)
}