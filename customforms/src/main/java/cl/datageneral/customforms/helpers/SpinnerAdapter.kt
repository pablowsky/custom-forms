package cl.datageneral.customforms.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import cl.datageneral.customforms.R

class SpinnerAdapter(
    private val mContext: Context,
    textViewResourceId: Int,
    var myObjs: List<SelectableItem>
) : ArrayAdapter<SelectableItem>(
    mContext, textViewResourceId, myObjs
) {
    override fun getCount(): Int {
        return myObjs.size
    }

    override fun getItem(position: Int): SelectableItem {
        return myObjs[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = TextView(context)
        label.text = myObjs[position].text
        //label.setText(myObjs[position].getText());
        //return label;
        // COMPATIBILIDAD CON LOLIPOP
        val rtlContainer = RelativeLayout(context)
        rtlContainer.addView(label)
        return rtlContainer
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        //LayoutInflater inflater = context.getLayoutInflater();
        val rowView = inflater.inflate(R.layout.row_spinner, parent, false)
        val label = rowView.findViewById<TextView>(R.id.c_row)
        //TextView label = new TextView(context);
        label.text = myObjs[position].text
        label.post { label.isSingleLine = false }
        return label
    }
}