package cl.datageneral.customforms.factory

import android.content.res.Resources
import android.util.Log
import cl.datageneral.customforms.Json
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.jsonconverters.InputDatetimeConverter
import cl.datageneral.customforms.factory.jsonconverters.InputExternalConverter
import cl.datageneral.customforms.factory.jsonconverters.InputSelectConverter
import cl.datageneral.customforms.factory.jsonconverters.InputTextConverter
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class ViewFactory(var jsonInput: JSONObject) {

    private val type:ViewTypes
        get(){
            Log.e("type", jsonInput.toString())
            return when(Json.getText(jsonInput, "vtype")){
                "text"          -> ViewTypes.TEXT
                "select"        -> ViewTypes.SELECT
                "radiobutton"   -> ViewTypes.RADIOBUTTON
                "external_text" -> ViewTypes.EXTERNAL_DATA
                "date"          -> ViewTypes.DATE
                "time"          -> ViewTypes.TIME
                "datetime"      -> ViewTypes.DATETIME
                else -> throw Resources.NotFoundException("Property \"vtype\" was not found in the object")
            }
        }

    fun build(readOnly:Boolean): InputBase? {

        return when(type){
            ViewTypes.TEXT      -> InputTextConverter(jsonInput, readOnly).invoke()
            ViewTypes.SELECT    -> InputSelectConverter(jsonInput, readOnly).invoke()
            ViewTypes.DATETIME  -> InputDatetimeConverter(jsonInput, readOnly).invoke()
            ViewTypes.EXTERNAL_DATA -> InputExternalConverter(jsonInput, readOnly).invoke()
            else -> null
        }
    }
}

enum class ViewTypes{
    TEXT, SELECT, RADIOBUTTON, EXTERNAL_DATA, DATE, TIME, DATETIME
}