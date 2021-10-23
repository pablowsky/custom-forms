package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputSingleButtonView
import cl.datageneral.customforms.helpers.MainListener

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmSingleButtonView(val readOnly:Boolean, context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputSingleButtonView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            title       = value.title

            initReadonly()
        }

    private var singleButton: Button
    var listener: MainListener?=null

    private fun initReadonly(){
        if(inputLabel?.onlyShowOnReadOnly==true && readOnly){
            singleButton.visibility = View.GONE
        }else{
            singleButton.visibility = View.VISIBLE
        }
    }

    var title:String?       = String()
        set(value) {
            value?.let {
                singleButton.text   = value
            }
            field               = value
        }

    init {
        inflate(context, R.layout.pm_singlebutton_view, this)
        Log.e("switchView", "Init...")

        singleButton = findViewById(R.id.single_button)
        singleButton.setOnClickListener {
            inputLabel?.let {
                listener?.onClick(it.viewId, "A")
            }
        }
    }
}