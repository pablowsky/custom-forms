package cl.datageneral.customforms.inputs

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputTimeView
import org.json.JSONObject

import cl.datageneral.customforms.helpers.MainListener


/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
@SuppressLint("ClickableViewAccessibility")
class PmTimeView(val readOnly:Boolean, context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputTimeView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            title       = value.title

            timeBox.hint = value.hint

            timeBox.setText(value.timeValue)
            displayWarning(value.showWarning)
            initReadonly()
        }

    private var timeBox: EditText
    private var titleLabel: TextView
    private var warningLabel: TextView
    private var mandatoryLabel: TextView
    var datetimeListener: MainListener?=null

    private fun initReadonly(){
        if(readOnly){
            initMandatory(false)
        }else{
            initMandatory(inputLabel!!.mandatory)
        }
        timeBox.isEnabled = !readOnly
    }

    override val isValid: Boolean
        get(){
            val value2 = timeBox.text.toString()

            val b2 = if(mandatory && value2.isEmpty()){
                displayWarning(context.getString(R.string.is_required))
                false
            }else{
                displayWarning("")
                true
            }
            return b2
        }

    fun initMandatory(value:Boolean) {
        if(value){
            mandatoryLabel.visibility = View.VISIBLE
        }else{
            mandatoryLabel.visibility = View.GONE
        }
    }

    fun setValue(value:String, subtype:String){
        if(subtype=="TIME"){
            timeBox.setText(value)
        }
    }

    override var mainValue:String
        get() {
            val jObj = JSONObject().apply {
                put("time", timeBox.text.toString())
            }
            return jObj.toString()
        }
        set(value) {
            val jObj = JSONObject(value)

            if(jObj.has("time")){
                setValue(jObj.getString("time"), "TIME")
            }
        }

    /**
     * Returns true when the field has a right answer
     * Returns false when the field doesnt have a right answer
     */
    override fun checkRequired(): Boolean {
        return if(mandatory){
            return timeBox.text.toString().isNotEmpty()
        }else{
            true
        }
    }

    fun displayWarning(value: Boolean) {
        if(value) {
            val format = if(inputLabel?.hint?.isNotEmpty() == true){
                "(${inputLabel?.hint})"
            }else{
                ""
            }
            warningLabel.text = "${context.getString(R.string.is_required)}. $format"
            warningLabel.visibility = View.VISIBLE
        }else{
            warningLabel.text = ""
            warningLabel.visibility = View.GONE
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
        if (readOnly) {
            inflate(context, R.layout.ro_time_view, this)
        } else {
            inflate(context, R.layout.pm_time_view, this)
        }

        timeBox         = findViewById(R.id.timeBox2)
        titleLabel      = findViewById(R.id.titleLabel)
        warningLabel    = findViewById(R.id.warningLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)
        val icon        = findViewById<ImageView>(R.id.imageTime)

        timeBox.setOnClickListener {
            datetimeListener?.onTimeInputClick(inputLabel!!.viewId, timeBox.text.toString())
        }

        icon.setOnClickListener {
            datetimeListener?.onTimeInputClick(inputLabel!!.viewId, timeBox.text.toString())
        }
    }



}