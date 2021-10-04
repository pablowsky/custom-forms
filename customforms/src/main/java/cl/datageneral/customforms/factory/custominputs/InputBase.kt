package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
abstract class InputBase:InputBaseI {
    open var value2:Any = ""
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

data class TextOptions(
    var minChars:Int = MIN_CHARS,
    var maxChars:Int = MAX_CHARS,
    var externalText:Boolean = EXTERNAL_TEXT,
    var maxLines:Int = MAX_LINES
)

const val MIN_CHARS = 0
const val MAX_CHARS = 250
const val MAX_LINES = 1
const val EXTERNAL_TEXT = false



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