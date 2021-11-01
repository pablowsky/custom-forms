package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.factory.jsonconverters.InputFilesConverter
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputFilesView:InputBase() {
    override var vtype              = ViewTypes.FILES
    var mainValues:ArrayList<String> = arrayListOf()
    var buttonText:String = String()
    var fileOptions = FileOptions()
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
            return (if(mandatory||fileOptions.minFiles>0){
                mainValues.isNotEmpty()
            }else{
                true
            }).also {
                showWarning = !it
            }
        }

    override var answer: Answer
        get() = InputFilesConverter.prepareAnswer(this)
        set(value) {}

    override fun setJsonAnswer(answer: JSONObject) = InputFilesConverter.parseAnswer(this, answer)
}