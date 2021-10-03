package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputSwitchView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmSwitchView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputSwitchView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            title       = value.title

            switch.isChecked = value.sValue
        }

    private var switch: SwitchCompat
    private var titleLabel: TextView


    override var mainValue:String
        set(value){
            switch.isChecked = value=="true"
        }
        get(){
            return if(switch.isChecked){
                "true"
            }else{
                "false"
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
        inflate(context, R.layout.pm_switch_view, this)
        Log.e("switchView", "Init...")

        titleLabel      = findViewById(R.id.titleLabel)
        switch          = findViewById(R.id.pmswitch)
        val mandatoryLabel:TextView  = findViewById(R.id.mandatory)
        mandatoryLabel.visibility = View.GONE


        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            inputLabel?.sValue = isChecked
            Log.v("Switch State=","" + isChecked)
        }
    }
}