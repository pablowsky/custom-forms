package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputFilesView
import cl.datageneral.customforms.helpers.MainListener

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmFilesView(val readOnly:Boolean, context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputFilesView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            titleLabel.text = value.title
            button.text     = value.buttonText

            initIndicator()
            displayWarning(value.showWarning)
            if(readOnly){
                initMandatory(false)
            }else{
                initMandatory(value.mandatory||value.fileOptions.minFiles>0)
            }
        }

    private var titleLabel: TextView
    private var mandatoryLabel: TextView
    private var button: Button
    private var warningLabel: TextView
    private var signatureIndicator: TextView
    var listener: MainListener?=null

    private fun initMandatory(value:Boolean) {
        if(value){
            mandatoryLabel.visibility = View.VISIBLE
        }else{
            mandatoryLabel.visibility = View.GONE
        }
    }

    private fun initIndicator(){
        val sCounter = inputLabel?.mainValues?.size
        if(sCounter==0){
            signatureIndicator.visibility = View.GONE
        }else{
            signatureIndicator.text = context.getString(R.string.fileloaded, sCounter)
            signatureIndicator.visibility = View.VISIBLE
        }
    }

    fun displayWarning(value: Boolean) {
        if(value) {
            val min = context.getString(R.string.is_required)
            val max = context.getString(R.string.is_required)
            val minmax = context.getString(R.string.minmax, inputLabel?.fileOptions?.minFiles, inputLabel?.fileOptions?.maxFiles)
            warningLabel.text = minmax
            warningLabel.visibility = View.VISIBLE
        }else{
            warningLabel.text = ""
            warningLabel.visibility = View.GONE
        }
    }

    init {
        inflate(context, R.layout.pm_files_view, this)

        titleLabel      = findViewById(R.id.titleLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)
        button          = findViewById(R.id.button)
        warningLabel    = findViewById(R.id.warningLabel)
        signatureIndicator  = findViewById(R.id.signatureIndicator)
        mandatoryLabel.visibility = View.GONE

        button.setOnClickListener {
            inputLabel?.let {
                listener?.onClick(it.viewId, it.mainValues, readOnly, it.fileOptions)
            }
        }
    }
}