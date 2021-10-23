package cl.datageneral.customforms.inputs

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
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
            setColor(value.color)
        }

    private var singleButton: Button
    var listener: MainListener?=null

    private fun initReadonly(){
        if(inputLabel?.onlyShowOnEdit==true && readOnly){
            singleButton.visibility = View.GONE
        }else{
            singleButton.visibility = View.VISIBLE
        }
    }

    private fun setColor(color:String){
        //singleButton.setBackgroundColor(context.resources.getColor(R.color.blue))
        var backgroundColor = ContextCompat.getColor(context, R.color.blue)
        var textColor = ContextCompat.getColor(context, R.color.white)
        when(color){
            "RED" -> {
                backgroundColor = ContextCompat.getColor(context, R.color.red)
                textColor = ContextCompat.getColor(context, R.color.white)
            }
            "BLUE" -> {
                backgroundColor = ContextCompat.getColor(context, R.color.blue)
                textColor = ContextCompat.getColor(context, R.color.white)
            }
            "GREEN" -> {
                backgroundColor = ContextCompat.getColor(context, R.color.green)
                textColor = ContextCompat.getColor(context, R.color.white)
            }
            "LIGHTBLUE" -> {
                backgroundColor = ContextCompat.getColor(context, R.color.light_blue)
                textColor = ContextCompat.getColor(context, R.color.white)
            }
            "PURPLE" -> {
                backgroundColor = ContextCompat.getColor(context, R.color.purple)
                textColor = ContextCompat.getColor(context, R.color.white)
            }
            "ORANGE" -> {
                backgroundColor = ContextCompat.getColor(context, R.color.orange)
                textColor = ContextCompat.getColor(context, R.color.white)
            }
            "WHITE" -> {
                backgroundColor = ContextCompat.getColor(context, R.color.white)
                textColor = ContextCompat.getColor(context, R.color.blue)
            }
        }
        singleButton.background.setColorFilter(backgroundColor, PorterDuff.Mode.MULTIPLY)
        singleButton.setTextColor(textColor)

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