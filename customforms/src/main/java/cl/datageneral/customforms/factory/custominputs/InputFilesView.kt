package cl.datageneral.customforms.factory.custominputs

import android.util.Log
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.ViewTypes

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputFilesView:InputBase() {
    override var vtype              = ViewTypes.FILES
    var mainValues:ArrayList<String> = arrayListOf()
    var buttonText:String = String()
    var maxFiles:Int = 0
    var minFiles:Int = 0
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    var showWarning:Boolean         = false
    override var value2: Any = emptyList<String>()
        get() = mainValues
        set(value) {
            field = value

            if(value is List<*>) {
                mainValues.addAll(value.map { it.toString() })
            }
        }

    override val isValid: Boolean
        get(){
            return (if(mandatory){
                mainValues.isNotEmpty()
            }else{
                true
            }).also {
                showWarning = !it
            }
        }
}