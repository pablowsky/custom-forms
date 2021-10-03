package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputSignatureView
import cl.datageneral.customforms.helpers.MainListener

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmSignatureView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputSignatureView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            titleLabel.text = value.title
            button.text     = value.buttonText

            initMandatory(value.mandatory)
            displayWarning(value.warningMessage)
            initIndicator()
        }

    private var titleLabel: TextView
    private var signatureIndicator: TextView
    private var mandatoryLabel: TextView
    private var button: Button
    private var warningLabel: TextView
    override var mainValue:String = String()
    var listener: MainListener?=null

    private fun initMandatory(value:Boolean) {
        if(value){
            mandatoryLabel.visibility = View.VISIBLE
        }else{
            mandatoryLabel.visibility = View.GONE
        }
    }

    private fun initIndicator(){
        if(inputLabel?.mainValue.isNullOrEmpty()){
            signatureIndicator.visibility = View.GONE
        }else{
            signatureIndicator.visibility = View.VISIBLE
        }
    }

    override val isValid: Boolean
        get(){
            return if(mandatory && mainValue.isEmpty()){
                displayWarning(context.getString(R.string.is_required))
                false
            }else{
                displayWarning("")
                true
            }
        }

    override fun displayWarning(msg:String) {
        if(msg.isNotEmpty()) {
            warningLabel.text = msg
            warningLabel.visibility = View.VISIBLE
        }else{
            warningLabel.text = ""
            warningLabel.visibility = View.GONE
        }
    }

    init {
        inflate(context, R.layout.pm_signature_view, this)

        titleLabel      = findViewById(R.id.titleLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)
        button          = findViewById(R.id.button)
        warningLabel    = findViewById(R.id.warningLabel)
        signatureIndicator    = findViewById(R.id.signatureIndicator)
        mandatoryLabel.visibility = View.GONE

        button.setOnClickListener {
            inputLabel?.let {
                listener?.onClick(inputLabel!!.viewId, arrayListOf(inputLabel!!.mainValue))
            }
        }
        displayWarning("")
    }
}