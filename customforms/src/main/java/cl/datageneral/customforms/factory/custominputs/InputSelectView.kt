package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.helpers.ItemSelectedListener
import cl.datageneral.customforms.helpers.SpinnerItem
import cl.datageneral.customforms.inputs.PmSelectView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSelectView:InputBase() {
    override var vtype              = ViewTypes.SELECT
    var dataOrigin:String           = String()
    var options:ArrayList<SpinnerItem>   = ArrayList()
    var filteredOptions:ArrayList<SpinnerItem>   = ArrayList()
    var useFilteredOptions          = false
    var hasChildrens:Boolean        = false
    var hasParent:String            = String()
        set(value) {
            if(value.isNotEmpty()){
                useFilteredOptions = true
            }
            field = value
        }

    override var answer: JSONObject
        get() = super.answer
        set(value) {
            val selectedValue =  value.getString("value")
            this@InputSelectView.value = selectedValue
            for(opt in options){
                if(opt.value==selectedValue){
                    parentSelected = opt.parent
                }
            }
        }

    var parentSelected              = String()
        set(value){
            filteredOptions.clear()
            for(opt in options){
                if(opt.parent==value){
                    filteredOptions.add(opt)
                }
            }
            useFilteredOptions = true
            field = value
        }

    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    override val isValid: Boolean
        get(){
            return if(mandatory && value.isEmpty()){
                warningMessage = "Este campo es requerido."
                false
            }else{
                warningMessage = ""
                true
            }
        }
    override var value: String = String()


    fun draw(view: PmSelectView, pListener: ItemSelectedListener):PmSelectView{
        val nlist:ArrayList<SpinnerItem>   = ArrayList()
        nlist.add(0, SpinnerItem("Seleccione", ""))
        nlist.addAll(if(useFilteredOptions){
            this@InputSelectView.filteredOptions
        }else {
            this@InputSelectView.options
        })

        return view.apply {
            viewId          = this@InputSelectView.viewId
            title           = this@InputSelectView.title
            listener        = pListener
            readOnly        = this@InputSelectView.readOnly
            dataOrigin      = this@InputSelectView.dataOrigin
            hasChildrens    = this@InputSelectView.hasChildrens
            hasParent       = this@InputSelectView.hasParent
            parentSelected  = this@InputSelectView.parentSelected
            mandatory       = this@InputSelectView.mandatory
            options         = nlist
            mainValue       = this@InputSelectView.value
            displayWarning(warningMessage)
        }
    }
}