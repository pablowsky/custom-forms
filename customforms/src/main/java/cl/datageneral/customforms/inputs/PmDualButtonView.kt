package cl.datageneral.customforms.inputs

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputDualButtonView
import cl.datageneral.customforms.helpers.MainListener

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmDualButtonView(val readOnly:Boolean, context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputDualButtonView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            buttonA.text = value.buttonAtitle
            buttonB.text = value.buttonBtitle

            initReadonly()
            setColor(value.color)
        }

    private var buttonA: Button
    private var buttonB: Button
    private var btnsContainer:LinearLayout
    var listener: MainListener?=null

    private fun initReadonly(){
        if(inputLabel?.onlyShowOnEdit==true && readOnly){
            btnsContainer.visibility = View.GONE
        }else{
            btnsContainer.visibility = View.VISIBLE
        }
    }

    private fun setColor(color:String){
        val textColor =when(color){
            "RED"       -> ContextCompat.getColor(context, R.color.red)
            "BLUE"      -> ContextCompat.getColor(context, R.color.blue)
            "GREEN"     -> ContextCompat.getColor(context, R.color.green)
            "LIGHTBLUE" -> ContextCompat.getColor(context, R.color.light_blue)
            "PURPLE"    -> ContextCompat.getColor(context, R.color.purple)
            "ORANGE"    -> ContextCompat.getColor(context, R.color.orange)
            "WHITE"     -> ContextCompat.getColor(context, R.color.white)
            else        -> ContextCompat.getColor(context, R.color.blue)
        }

        buttonA.setTextColor(textColor)
        buttonB.setTextColor(textColor)
    }


    init {
        inflate(context, R.layout.pm_dualbutton_view, this)
        Log.e("switchView", "Init...")

        buttonA = findViewById(R.id.btnGuardar)
        buttonB = findViewById(R.id.btnCancelar)
        btnsContainer = findViewById(R.id.btns_container)

        buttonA.setOnClickListener {
            inputLabel?.let {
                listener?.onClick(it.viewId, "A")
            }
        }

        buttonB.setOnClickListener {
            inputLabel?.let {
                listener?.onClick(it.viewId, "B")
            }
        }
    }
}