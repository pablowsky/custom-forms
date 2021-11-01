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
    @Deprecated("Obsoleto seguir ejemplo de LabelView")
    override var value:String       = String()
    /*override var answer: JSONObject
        get() {
            return JSONObject().apply {
                put("view_id", viewId)
                put("value", value)
            }
        }
        set(value) {
            this@InputBase.value =  value.getString("value")
        }*/
    override var answer:Answer = Answer(JSONObject())

    override fun setValue(value: String, subtype: String) {}

    override fun setJsonAnswer(answer: JSONObject) {
        TODO("Not yet implemented")
    }
}

data class TextOptions(
    var minChars:Int = MIN_CHARS,
    var maxChars:Int = MAX_CHARS,
    var externalText:Boolean = EXTERNAL_TEXT,
    var maxLines:Int = MAX_LINES
)

data class FileOptions(
    var minFiles:Int = MIN_FILES,
    var maxFiles:Int = MAX_FILES
)

const val MIN_FILES = 0
const val MAX_FILES = 10
const val MIN_CHARS = 0
const val MAX_CHARS = 250
const val MAX_LINES = 1
const val EXTERNAL_TEXT = false

data class Answer(
    var json : JSONObject,
    var files: ArrayList<String> = ArrayList()
)

interface InputBaseI{
    var readOnly:Boolean
    var title:String
    var warningMessage:String
    var mandatory:Boolean
    var vtype:ViewTypes
    var viewId:String
    var value:String
    val isValid:Boolean
    var answer:Answer
    fun setJsonAnswer(answer:JSONObject)
    fun setValue(value:String, subtype:String)
}