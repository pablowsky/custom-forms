package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputSignatureView
import cl.datageneral.customforms.helpers.MainListener
import android.graphics.BitmapFactory
import android.widget.ImageView
import java.io.File

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmSignatureView(val readOnly:Boolean, context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var inputLabel: InputSignatureView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            titleLabel.text = value.title
            button.text     = value.buttonText

            displayWarning(value.showWarning)
            initIndicator()
            initReadonly()
        }

    private fun initReadonly(){
        if(readOnly){
            initMandatory(false)
            signatureIndicator.visibility   = View.GONE
            button.visibility               = View.GONE
        }else{
            initMandatory(inputLabel!!.mandatory)
        }

    }

    private var titleLabel: TextView
    private var signatureIndicator: TextView
    private var mandatoryLabel: TextView
    private var button: Button
    private var warningLabel: TextView
    private var imageHolder:ImageView
    override var mainValue:String = String()
    var listener: MainListener?=null

    private fun initMandatory(value:Boolean) {
        if(value){
            mandatoryLabel.visibility = View.VISIBLE
        }else{
            mandatoryLabel.visibility = View.GONE
        }
    }

    private fun initIndicator(){
        if(readOnly && !inputLabel?.mainValue.isNullOrEmpty()) {
            val imgFile = File(inputLabel?.mainValue!!)

            if (imgFile.exists()) {
                val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                imageHolder.setImageBitmap(myBitmap)
            }

            imageHolder.visibility          = View.VISIBLE
            initMandatory(false)
        } else {
            imageHolder.visibility = View.GONE
            if (inputLabel?.mainValue.isNullOrEmpty()) {
                signatureIndicator.visibility = View.GONE
            } else {
                signatureIndicator.visibility = View.VISIBLE
            }
        }
    }

    fun displayWarning(value: Boolean) {
        if(value) {
            warningLabel.text = context.getString(R.string.is_required)
            warningLabel.visibility = View.VISIBLE
        }else{
            warningLabel.text = ""
            warningLabel.visibility = View.GONE
        }
    }

    init {
        inflate(context, R.layout.pm_signature_view, this)

        titleLabel      = findViewById(R.id.titleLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)
        button          = findViewById(R.id.button)
        warningLabel    = findViewById(R.id.warningLabel)
        signatureIndicator    = findViewById(R.id.signatureIndicator)
        imageHolder    = findViewById(R.id.imageView)
        mandatoryLabel.visibility = View.GONE

        button.setOnClickListener {
            inputLabel?.let {
                listener?.onClick(inputLabel!!.viewId, arrayListOf(inputLabel!!.mainValue), readOnly, null)
            }
        }
        displayWarning("")
    }
}