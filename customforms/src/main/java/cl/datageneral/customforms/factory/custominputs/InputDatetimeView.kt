package cl.datageneral.customforms.factory.custominputs

import android.util.Log
import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.inputs.PmDatetimeView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputDatetimeView:InputBase() {
    override var vtype              = ViewTypes.DATETIME
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
    var dateValue = String()
    var timeValue = String()

    override var value: String
        get() {
            val jObj = JSONObject().apply {
                put("date", dateValue)
                put("time", timeValue)
            }
            return jObj.toString()
        }
        set(value) {
            val jObj = JSONObject(value)
            if(jObj.has("date")){
                setValue(jObj.getString("date"), "DATE")
            }
            if(jObj.has("time")){
                setValue(jObj.getString("time"), "TIME")
            }
        }

    override fun setValue(value:String, subtype:String){
        //Log.e("setValue", value)
        if(subtype=="DATE"){
            dateValue = value
        }
        if(subtype=="TIME"){
            timeValue = value
        }
    }

    fun draw(view: PmDatetimeView): PmDatetimeView {
        return view.apply {
            viewId      = this@InputDatetimeView.viewId
            //datetimeListener = pDatetimeListener
            title       = this@InputDatetimeView.title
            readOnly    = this@InputDatetimeView.readOnly
            mandatory   = this@InputDatetimeView.mandatory
            mainValue   = this@InputDatetimeView.value
            dateValue   = this@InputDatetimeView.dateValue
            timeValue   = this@InputDatetimeView.timeValue
            displayWarning(warningMessage)
        }
    }
}