package cl.datageneral.customforms.dialogs


import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class SelectDateFragment(private val position:String, var mCallback: ISelectedData) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    //private var mCallback: ISelectedData? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val yy = calendar[Calendar.YEAR]
        val mm = calendar[Calendar.MONTH]
        val dd = calendar[Calendar.DAY_OF_MONTH]
        return DatePickerDialog(context!!, this, yy, mm, dd)
    }

    override fun onDateSet(view: DatePicker, yy: Int, mm: Int, dd: Int) {
        populateSetDate(yy, mm + 1, dd)
    }

    fun populateSetDate(year: Int, month: Int, day: Int) {
        //mEdit = (EditText)findViewById(R.id.fecha);
        //mEdit.setText(day+"/"+month+"/"+year);
        mCallback!!.onDateSelected(position, "$day/$month/$year")
    }

    /*override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            mCallback = activity as ISelectedData
        } catch (e: ClassCastException) {
            Log.d("MyDialog", "Activity doesn't implement the ISelectedData interface")
        }
    }*/
}