package cl.datageneral.customforms.dialogs


import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class SelectTimeFragment(private val position:String, var mCallback: ISelectedData) :
    DialogFragment(), TimePickerDialog.OnTimeSetListener {
    //private var mCallback: ISelectedData? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hh = calendar[Calendar.HOUR]
        val nn = calendar[Calendar.MINUTE]
        return TimePickerDialog(activity, this, hh, nn, false)
    }

    override fun onTimeSet(view: TimePicker, hh: Int, nn: Int) {
        updateDisplay(hh, nn)
    }

    private fun updateDisplay(hh: Int, nn: Int) {
        val time = StringBuilder()
            .append(pad(hh)).append(":")
            .append(pad(nn))
        mCallback?.onTimeSelected(position, time.toString())
    }

    companion object {
        private fun pad(c: Int): String {
            return if (c >= 10) c.toString() else "0$c"
        }
    }

    /* fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            mCallback = activity as ISelectedData
        } catch (e: ClassCastException) {
            Log.d("MyDialog", "Activity doesn't implement the ISelectedData interface")
        }
    }*/
}