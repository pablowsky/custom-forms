package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputLabelView
import cl.datageneral.customforms.helpers.Disposition

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmLabelView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputLabelView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            title       = value.title

            initValueBox()
        }

    private fun initValueBox(){
        val valueBoxV = findViewById<TextView>(R.id.valueBoxVertical)
        val valueBoxH = findViewById<TextView>(R.id.valueBoxHorizontal)
        if(inputLabel?.layoutDisposition==Disposition.HORIZONTAL){
            valueBox = valueBoxH
            valueBoxH.visibility = View.VISIBLE
            valueBoxV.visibility = View.GONE
        }else{
            valueBox = valueBoxV
            valueBoxH.visibility = View.GONE
            valueBoxV.visibility = View.VISIBLE
        }

        valueBox?.text = inputLabel?.inputValue
    }

    private var valueBox: TextView? = null
    private var titleLabel: TextView
    private var mandatoryLabel: TextView


    var title:String?       = String()
        set(value) {
            value?.let {
                titleLabel.text   = value
            }
            field               = value
        }

    init {
        inflate(context, R.layout.pm_label_vertical_view, this)

        titleLabel      = findViewById(R.id.titleLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)
        mandatoryLabel.visibility = View.GONE
        displayWarning("")
    }
}