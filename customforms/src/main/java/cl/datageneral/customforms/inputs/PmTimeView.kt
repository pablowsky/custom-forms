package cl.datageneral.customforms.inputs

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputTimeView
import cl.datageneral.customforms.helpers.DateTimeClickListener
import kotlinx.android.synthetic.main.pm_datetime_view.view.*
import org.json.JSONObject

import android.view.MotionEvent


/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
@SuppressLint("ClickableViewAccessibility")
class PmTimeView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputTimeView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            title       = value.title

            timeBox.hint = value.hint

            timeBox.setText(value.timeValue)

            initMandatory(value.mandatory)
            initReadonly(value.readOnly)
            displayWarning(value.warningMessage)
        }

    private var timeBox: EditText
    private var titleLabel: TextView
    private var warningLabel: TextView
    private var mandatoryLabel: TextView
    var datetimeListener: DateTimeClickListener?=null

    fun initReadonly(value:Boolean){
        timeBox.isEnabled = !value
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
        inflate(context, R.layout.pm_time_view, this)

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