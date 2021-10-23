package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputLabelView
import cl.datageneral.customforms.helpers.Disposition
import cl.datageneral.customforms.helpers.MainListener

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmLabelView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputLabelView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            title       = value.title

            button.text = value.buttonText
            initValueBox()
        }

    private fun initValueBox(){
        val valueBoxV = findViewById<TextView>(R.id.valueBoxVertical)
        val valueBoxH = findViewById<TextView>(R.id.valueBoxHorizontal)
        if(inputLabel?.dialogData!=null || inputLabel?.showAsDialog==true){
            button.visibility   = View.VISIBLE
            valueBoxH.visibility = View.GONE
            valueBoxV.visibility = View.GONE
        }else {
            button.visibility   = View.GONE
            if (inputLabel?.layoutDisposition == Disposition.HORIZONTAL) {
                valueBox = valueBoxH
                valueBoxH.visibility = View.VISIBLE
                valueBoxV.visibility = View.GONE
            } else {
                valueBox = valueBoxV
                valueBoxH.visibility = View.GONE
                valueBoxV.visibility = View.VISIBLE
            }
        }

        valueBox?.text = inputLabel?.inputValue
    }

    private var valueBox: TextView? = null
    private var titleLabel: TextView
    private var mandatoryLabel: TextView
    private var button: Button
    var listener: MainListener?=null


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
        button          = findViewById(R.id.showValues)
        mandatoryLabel.visibility = View.GONE

        button.setOnClickListener {
            inputLabel?.let {
                if(inputLabel?.showAsDialog==true){
                    listener?.onShowDialog(it.viewId, it.inputValue?:"")
                }else {
                    listener?.onDataListClick(it.dialogData!!)
                }
            }
        }
    }
}