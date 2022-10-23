package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.factory.jsonconverters.InputNumericConverter
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputNumericView:InputBase() {
    override var vtype      = ViewTypes.NUMERIC
    var hint:String         = String()
    var mainValue:String    = String()
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String = String()
    var showWarning:Boolean         = false
    var minValue:Double = 0.0
    var maxValue:Double = 1000.0
    override val isValid: Boolean
        get(){
            return (!(mandatory && mainValue.isEmpty())).also {
                showWarning = !it
            }
        }
    //var textOptions:TextOptions = TextOptions(MIN_CHARS, MAX_CHARS, EXTERNAL_TEXT)

    override var value2: Any = String()
        get() = mainValue
        set(value) {
            field = value
            if(value is String){
                mainValue = value
            }
        }

    override var answer: Answer
        get() = InputNumericConverter.prepareAnswer(this)
        set(value) {}

    override fun setJsonAnswer(answer: JSONObject)
            = InputNumericConverter.parseAnswer(this, answer)
}