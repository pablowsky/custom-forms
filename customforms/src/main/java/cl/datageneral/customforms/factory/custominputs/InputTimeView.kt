package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputTimeView:InputBase() {
    override var vtype              = ViewTypes.TIME
    var hint:String                 = String()
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    override val isValid: Boolean
        get(){
            return if(mandatory && value.isEmpty()){
                val format = if(hint.isNotEmpty()){
                    "($hint)"
                }else{
                    ""
                }
                warningMessage = "Este campo es requerido. $format"
                false
            }else{
                warningMessage = ""
                true
            }
        }

    var timeValue = String()

    override var value2:Any = ""
        set(value) {
            field = value
            if(value is String){
                timeValue = value
            }
        }

    override var value: String
        get() {
            val jObj = JSONObject().apply {
                put("time", timeValue)
            }
            return jObj.toString()
        }
        set(value) {
            val jObj = JSONObject(value)
            if(jObj.has("time")){
                setValue(jObj.getString("time"), "TIME")
            }
        }

    override fun setValue(value:String, subtype:String){
        if(subtype=="TIME"){
            timeValue = value
        }
    }

}