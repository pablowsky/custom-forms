package cl.datageneral.customforms

import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cl.datageneral.customforms.dialogs.ISelectedData
import cl.datageneral.customforms.dialogs.SelectDateFragment
import cl.datageneral.customforms.dialogs.SelectTimeFragment
import cl.datageneral.customforms.helpers.*
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), ISelectedData {
    val TAG = "MainActivity"

    val cform = CustomFormBuilder()

    override fun onDateSelected(position: String, date: String) {
        Log.e("data", "$date")
        cform.setValue(position, date, "DATE")
    }

    override fun onTimeSelected(position: String, time: String) {
        Log.e("data", "$time")
        cform.setValue(position, time, "TIME")
    }

    /*private val cListener = object : ItemSelectedListener {
        override fun onSelectInputClick(viewId: String, value: String) {
            Log.e(TAG, "onSelectInputClick $viewId $value")
        }

        override fun onLoadChildrensClick(
            parentViewId: String,
            selectedValue: String
        ) {
            Log.e(TAG, "onLoadChildrensClick $parentViewId $selectedValue")
        }
    }*/

    var datetimeListener = object : DateTimeClickListener {
        override fun onDateInputClick(viewId:String, value: String) {
            SelectDateFragment(viewId, this@MainActivity as ISelectedData).show(supportFragmentManager, "DatePicker")
        }

        override fun onTimeInputClick(viewId:String, value: String) {
            Log.e("onTimeInputClick", "viewId:${viewId}, Values:${value}")
            SelectTimeFragment(viewId, this@MainActivity as ISelectedData).show(supportFragmentManager, "TimePikcer")
        }
    }

    var externalListener = object : ExternalChangeListenerListener {
        override fun onExternalChange(viewId:String, searchKey: String, parent: String) {
            //Thread.sleep(3000)
            Log.e("ExternalChangeDetected", "$viewId $searchKey $parent")
            cform.setValue(viewId, "Este es un texto externo")
        }
    }

    var labelListener = object : LabelListener {
        override fun onDataListClick(data: HashMap<String, ArrayList<String>>) {
            for(d in data){
                Log.e("DialogData", "Title:${d.key}, Values:${d.value.size}")
            }
        }
    }

    var inputClickListener = object : InputClickListener{
        override fun onClick(itemId:String, data:ArrayList<String>) {
            Log.e("DialogData", "Id:${itemId}, Values:${data.size}")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //val sampleJson = getFormConfig("template.json")
        val sampleJson = getFormConfig("template_acta.json")


        //cform.selectListener = cListener
        cform.datetimeListener  = datetimeListener
        cform.externalListener  = externalListener
        cform.labelListener     = labelListener
        cform.inputClickListener = inputClickListener
        cform.buildLayout(this, sampleJson, viewContainer, true)


        val requiresoptions = cform.viewsRequireExternalData()

        for(vr in requiresoptions){
            val items = ArrayList<SelectableItem>()
            if(vr.viewId=="1"){
                items.add(SelectableItem("tipo sitio 1", "1"))
                items.add(SelectableItem("tipo sitio 2", "2"))
                items.add(SelectableItem("tipo sitio 3", "3"))
            }else if (vr.viewId=="2"){
                items.add(SelectableItem("nombre sitio 1", "11", "1"))
                items.add(SelectableItem("nombre sitio 2", "12", "1"))
                items.add(SelectableItem("nombre sitio 3", "13", "1"))
                items.add(SelectableItem("nombre sitio 4", "14", "2"))
                items.add(SelectableItem("nombre sitio 5", "15", "2"))
                items.add(SelectableItem("nombre sitio 6", "16", "2"))
                items.add(SelectableItem("nombre sitio 7", "17", "3"))
                items.add(SelectableItem("nombre sitio 8", "18", "3"))
                items.add(SelectableItem("nombre sitio 9", "19", "3"))
                items.add(SelectableItem("nombre sitio 10", "20", "3"))
            }else if (vr.viewId=="3"){
                items.add(SelectableItem("entidad 1", "21", "11"))
                items.add(SelectableItem("entidad 2", "22", "11"))
                items.add(SelectableItem("entidad 3", "23", "12"))
                items.add(SelectableItem("entidad 4", "24", "12"))
                items.add(SelectableItem("entidad 5", "25", "13"))
                items.add(SelectableItem("entidad 6", "26", "13"))
                items.add(SelectableItem("entidad 7", "27", "14"))
                items.add(SelectableItem("entidad 8", "28", "14"))
                items.add(SelectableItem("entidad 9", "29", "15"))
                items.add(SelectableItem("entidad 10", "30", "15"))
            }else if (vr.viewId=="4") {
                items.add(SelectableItem("linea de negocio del incidente 1", "31", "21"))
                items.add(SelectableItem("linea de negocio del incidente 2", "32", "21"))
                items.add(SelectableItem("linea de negocio del incidente 3", "33", "22"))
                items.add(SelectableItem("linea de negocio del incidente 4", "34", "22"))
                items.add(SelectableItem("linea de negocio del incidente 5", "35", "23"))
                items.add(SelectableItem("linea de negocio del incidente 6", "36", "23"))
                items.add(SelectableItem("linea de negocio del incidente 7", "37", "24"))
                items.add(SelectableItem("linea de negocio del incidente 8", "38", "24"))
                items.add(SelectableItem("linea de negocio del incidente 9", "39", "25"))
                items.add(SelectableItem("linea de negocio del incidente 10", "40", "25"))
            }


            cform.setOptions(vr.viewId, items)
        }

        saveBtn.setOnClickListener {
            val answers = cform.formAnswer
            Log.e("Answers", answers.toString())
        }

        setBtn.setOnClickListener {
            cform.formAnswer = getFormConfig("template_answers3.json")
        }

        saveTemp.setOnClickListener {
            Log.e("Rapid Save:", "-----------------------------")
            for(data in cform.pairedData){
                Log.e("Saved:${data.key}", "${data.value}")
            }
        }

        loadTemp.setOnClickListener {
            Log.e("Rapid Save:", "-----------------------------")
            val map:ArrayMap<String, String> = ArrayMap()
            map["1"] = "1"
            map["2"] = "12"
            map["3"] = "23"
            map["4"] = "36"
            map["11"] = "texto valido desde temporal"
            map["12"] = "09/09/2020"
            cform.pairedData = map

        }

        validateBtn.setOnClickListener {
            Log.e("Validation:", "-----------------------------")
            val vr = cform.validateLayoutFields()
            Log.e("Validation1:", "${vr.isValidForm}")
            Log.e("Validation2:", "${vr.invalidFields}")
            Log.e("Validation3:", "${vr.validFields}")
        }

        clearBtn.setOnClickListener {

        }
    }




    fun getFormConfig(name: String): JSONObject {
        var json: String? = null
        try {
            val myform =  assets.open(name)
            val mbuffer = ByteArray(myform.available())
            myform.read(mbuffer)
            myform.close()

            //json = mbuffer.toString()

            json = String(mbuffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return JSONObject(json)
    }
}