package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.factory.jsonconverters.InputCheckboxConverter
import cl.datageneral.customforms.helpers.SelectableItem
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputCheckboxView:InputBase() {
    override var vtype              = ViewTypes.CHECKBOX
    var items:ArrayList<SelectableItem> = ArrayList()
    override var readOnly: Boolean  = false
    override var title: String      = String()
    var showWarning:Boolean         = false
    override val isValid: Boolean
        get(){
            return (!(mandatory && selectedItems.isEmpty())).also {
                showWarning = !it
            }
        }

    val selectedItems: HashMap<String, String> = HashMap()

    override var answer: Answer
        get() = InputCheckboxConverter.prepareAnswer(this)
        set(value) {}

    override fun setJsonAnswer(answer: JSONObject) = InputCheckboxConverter.parseAnswer(this, answer)
}