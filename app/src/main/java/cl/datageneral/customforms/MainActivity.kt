package cl.datageneral.customforms

import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cl.datageneral.customforms.dialogs.ISelectedData
import cl.datageneral.customforms.dialogs.SelectDateFragment
import cl.datageneral.customforms.dialogs.SelectTimeFragment
import cl.datageneral.customforms.factory.custominputs.TextOptions
import cl.datageneral.customforms.helpers.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), ISelectedData {
    val TAG = "MainActivity"

    val cform = CustomFormBuilder()

    var mainListener = object : MainListener{
        override fun onRequestLargeText(itemId: String, value: String, options: TextOptions) {
            Log.e("requireLargetext", "id:${itemId} ${value}")
            cform.setValue(itemId, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        }

        override fun onDateInputClick(viewId:String, value: String) {
            SelectDateFragment(viewId, this@MainActivity as ISelectedData).show(supportFragmentManager, "DatePicker")
        }

        override fun onTimeInputClick(viewId:String, value: String) {
            Log.e("onTimeInputClick", "viewId:${viewId}, Values:${value}")
            SelectTimeFragment(viewId, this@MainActivity as ISelectedData).show(supportFragmentManager, "TimePikcer")
        }

        override fun onClick(itemId:String, data:ArrayList<String>) {
            Log.e("DialogData", "Id:${itemId}, Values:${data.size}")
            if(itemId=="11") {
                cform.setValue(itemId, arrayListOf("/my/fake/signature1.jpg","/my/fake/signature2.jpg","/my/fake/signature3.jpg"))
            }else{
                cform.setValue(itemId, "/my/fake/signature.jpg")
            }
        }

        override fun onExternalChange(viewId:String, searchKey: String, parent: String) {
            //Thread.sleep(3000)
            Log.e("ExternalChangeDetected", "$viewId $searchKey $parent")
            cform.setValue(viewId, "Este es un texto externo")
        }

        override fun onDataListClick(data: HashMap<String, ArrayList<String>>) {
            for(d in data){
                Log.e("DialogData", "Title:${d.key}, Values:${d.value.size}")
            }
        }

        override fun onSelectInputClick(viewId: String, value: String) {
            Log.e(TAG, "onSelectInputClick $viewId $value")
        }

        override fun onLoadChildrensClick(
            parentViewId: String,
            selectedValue: String
        ) {
            Log.e(TAG, "onLoadChildrensClick $parentViewId $selectedValue")
        }
    }

    override fun onDateSelected(position: String, date: String) {
        Log.e("data", "$date")
        //cform.setValue(position, date, "DATE")
    }

    override fun onTimeSelected(position: String, time: String) {
        Log.e("data", "$time")
        //cform.setValue(position, time, "TIME")
        cform.setValue(position, time)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val sampleJson = getFormConfig("template.json")
        val sampleJson = getFormConfig("template_acta.json")

        cform.mainListener = mainListener
        //cform.buildLayout(this, sampleJson, viewContainer, true)
        cform.buildRecycler(this, sampleJson, viewListContainer, false)


        /*val requiresoptions = cform.viewsRequireExternalData()

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
        }*/
        val answer = "{\"answers\":[{\"view_id\":\"7\",\"value\":[{\"itemId\":\"1\",\"itemText\":\"Text 1\"},{\"itemId\":\"3\",\"itemText\":\"Text 3\"},{\"itemId\":\"5\",\"itemText\":\"Text 5\"}]}]}"
        val answerFiles = "{\"answers\":[{\"view_id\":\"11\",\"value\":[\"/my/fake/signature1.jpg\",\"/my/fake/signature2.jpg\",\"/my/fake/signature3.jpg\"]}]}"
        val answerSignature = "{\"answers\":[{\"view_id\":\"8\",\"value\":[\"/my/fake/signature.jpg\"]}]}"
        val answerTextSwitchTIme = "{\"answers\":[{\"view_id\":\"1\",\"value\":[\"10:44\"]},{\"view_id\":\"2\",\"value\":[\"02:44\"]},{\"view_id\":\"3\",\"value\":[false]},{\"view_id\":\"4\",\"value\":[\"cmVhbGx5IGdvb2Qg8J+RjfCfkY3wn5GN\\n\"]},{\"view_id\":\"5\",\"value\":[\"ZW1haWwgYWRkcg==\\n\"]},{\"view_id\":\"6\",\"value\":[\"TG9yZW0gaXBzdW0gZG9sb3Igc2l0IGFtZXQsIGNvbnNlY3RldHVyIGFkaXBpc2NpbmcgZWxpdCwg\\nc2VkIGRvIGVpdXNtb2QgdGVtcG9yIGluY2lkaWR1bnQgdXQgbGFib3JlIGV0IGRvbG9yZSBtYWdu\\nYSBhbGlxdWEuIFV0IGVuaW0gYWQgbWluaW0gdmVuaWFtLCBxdWlzIG5vc3RydWQgZXhlcmNpdGF0\\naW9uIHVsbGFtY28gbGFib3JpcyBuaXNpIHV0IGFsaXF1aXAgZXggZWEgY29tbW9kbyBjb25zZXF1\\nYXQuIER1aXMgYXV0ZSBpcnVyZSBkb2xvciBpbiByZXByZWhlbmRlcml0IGluIHZvbHVwdGF0ZSB2\\nZWxpdCBlc3NlIGNpbGx1bSBkb2xvcmUgZXUgZnVnaWF0IG51bGxhIHBhcmlhdHVyLiBFeGNlcHRl\\ndXIgc2ludCBvY2NhZWNhdCBjdXBpZGF0YXQgbm9uIHByb2lkZW50LCBzdW50IGluIGN1bHBhIHF1\\naSBvZmZpY2lhIGRlc2VydW50IG1vbGxpdCBhbmltIGlkIGVzdCBsYWJvcnVtLg==\\n\"]}]}"

        saveBtn.setOnClickListener {
            val answers = cform.formAnswers
            Log.e("Answers", answers.toString())
        }

        setBtn.setOnClickListener {
            //cform.formAnswer = getFormConfig("template_answers3.json")
            cform.formAnswers = Pair(JSONObject(answerTextSwitchTIme), arrayListOf())
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
            val vr = cform.validateFields()
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