package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.helpers.DateTimeClickListener
import kotlinx.android.synthetic.main.pm_datetime_view.view.*
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmDatetimeView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    private var dateBox: EditText
    private var timeBox: EditText
    private var titleLabel: TextView
    private var warningLabel: TextView
    private var mandatoryLabel: TextView
    var datetimeListener: DateTimeClickListener?=null
    var dateValue = String()
        set(value) {
            dateBox.setText(value)
        }
    var timeValue = String()
        set(value) {
            timeBox.setText(value)
        }
    var readOnly = false
        set(value) {
            dateBox.isEnabled = !value
            timeBox.isEnabled = !value
            field = value
        }

    override val isValid: Boolean
        get(){
            val value1 = dateBox.text.toString()
            val value2 = timeBox.text.toString()
            val b1 = if(mandatory && value1.isEmpty()){
                displayWarning(context.getString(R.string.is_required))
                false
            }else{
                displayWarning("")
                true
            }
            val b2 = if(mandatory && value2.isEmpty()){
                displayWarning(context.getString(R.string.is_required))
                false
            }else{
                displayWarning("")
                true
            }
            return b1 && b2
        }

    override var mandatory: Boolean = false
        set(value) {
            if(value){
                mandatoryLabel.visibility = View.VISIBLE
            }else{
                mandatoryLabel.visibility = View.GONE
            }
            field = value
        }

    fun setValue(value:String, subtype:String){
        if(subtype=="DATE"){
            dateBox.setText(value)
        }
        if(subtype=="TIME"){
            timeBox.setText(value)
        }
    }

    override var mainValue:String
        get() {
            val jObj = JSONObject().apply {
                put("date", dateBox.text.toString())
                put("time", timeBox.text.toString())
            }
            return jObj.toString()
        }
        set(value) {
            val jObj = JSONObject(value)
            if(jObj.has("date")){
                setValue(jObj.getString("date"), "DATE")
            }
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
            val c1 = dateBox.text.toString().isNotEmpty()
            val c2 = timeBox.text.toString().isNotEmpty()
            return c1 && c2
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
        inflate(context, R.layout.pm_datetime_view, this)

        dateBox         = findViewById(R.id.dateBox)
        timeBox         = findViewById(R.id.timeBox)
        titleLabel      = findViewById(R.id.titleLabel)
        warningLabel    = findViewById(R.id.warningLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)

        dateBox.setOnClickListener {
            datetimeListener?.onDateInputClick(viewId, dateBox.text.toString())
        }

        timeBox.setOnClickListener {
            datetimeListener?.onTimeInputClick(viewId, timeBox.text.toString())
        }
    }



}