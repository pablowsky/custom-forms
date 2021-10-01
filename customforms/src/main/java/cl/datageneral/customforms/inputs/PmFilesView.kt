package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputFilesView
import cl.datageneral.customforms.factory.custominputs.InputSignatureView
import cl.datageneral.customforms.helpers.InputClickListener
import cl.datageneral.customforms.helpers.LabelListener

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmFilesView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputFilesView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            title       = value.title

            button.text = value.buttonText
        }

    private var titleLabel: TextView
    private var mandatoryLabel: TextView
    private var button: Button
    private var warningLabel: TextView
    override var mainValue:String = String()
    var listener: InputClickListener?=null

    override var mandatory: Boolean = false
        set(value) {
            if(value){
                mandatoryLabel.visibility = View.VISIBLE
            }else{
                mandatoryLabel.visibility = View.GONE
            }
            field = value
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
            warningLabel.visibility = View.INVISIBLE
        }
    }

    var title:String?       = String()
        set(value) {
            value?.let {
                titleLabel.text   = value
            }
            field               = value
        }

    init {
        inflate(context, R.layout.pm_files_view, this)

        titleLabel      = findViewById(R.id.titleLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)
        button          = findViewById(R.id.button)
        warningLabel    = findViewById(R.id.warningLabel)
        mandatoryLabel.visibility = View.GONE

        button.setOnClickListener {
            inputLabel?.let {
                listener?.onClick(inputLabel!!.viewId, arrayListOf())
            }
        }
        displayWarning("")
    }
}