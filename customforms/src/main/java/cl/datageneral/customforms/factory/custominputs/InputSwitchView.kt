package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.factory.jsonconverters.InputSwitchConverter
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSwitchView:InputBase() {
    override var vtype              = ViewTypes.SWITCH
    var textOff:String              = String()
    var textOn:String               = String()
    var mainValue:Boolean              = true
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override val isValid: Boolean   = true

    override var answer: Answer
        get() = InputSwitchConverter.prepareAnswer(this)
        set(value) {}

    override fun setJsonAnswer(answer: JSONObject)
            = InputSwitchConverter.parseAnswer(this, answer)
}