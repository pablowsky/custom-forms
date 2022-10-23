package cl.datageneral.customforms

import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cl.datageneral.customforms.dialogs.ISelectedData
import cl.datageneral.customforms.dialogs.SelectDateFragment
import cl.datageneral.customforms.dialogs.SelectTimeFragment
import cl.datageneral.customforms.factory.custominputs.FileOptions
import cl.datageneral.customforms.factory.custominputs.TextOptions
import cl.datageneral.customforms.helpers.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity(), ISelectedData {
    val TAG = "MainActivity"

    val cform = CustomFormBuilder()

    /*val startFileManagerForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        val id      = result.data?.getStringExtra(FileManagerActivity.PARAM_ID).toString()
        val data    = result.data?.getCharSequenceArrayListExtra(FileManagerActivity.PARAM_ATTACHMENT)?: arrayListOf()

        if(result.resultCode == FileManagerActivity.ATTACHMENT_OK) {
            cform.setValue(id, data)
        }
    }

    val startSignatureForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        val id      = result.data?.getStringExtra(SignatureActivity.PARAM_ID).toString()
        val data    = result.data?.getStringExtra(SignatureActivity.PARAM_SIGNATURE).toString()


        if(result.resultCode == SignatureActivity.SIGNATURE_OK) {
            Log.e("saving, $id", data)
            cform.setValue(id, data)
        }
    }

    val startLargeTextForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        val id      = result.data?.getStringExtra(LargeTextActivity.PARAM_ID).toString()
        val data    = result.data?.getStringExtra(LargeTextActivity.PARAM_TEXT).toString()


        if(result.resultCode == LargeTextActivity.OK) {
            Log.e("saving, $id", data)
            cform.setValue(id, data)
        }
    }*/

    var mainListener = object : MainListener{
        override fun onRequestLargeText(itemId: String, value: String, options: TextOptions) {
            Log.e("requireLargetext", "id:${itemId} ${value}")
            val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            cform.setValue(itemId, text)
            /*val intent = Intent(this@MainActivity, LargeTextActivity::class.java)
            intent.putExtra(LargeTextActivity.PARAM_TITLE, "Texto largo")
            intent.putExtra(LargeTextActivity.PARAM_TEXT, value)
            intent.putExtra(LargeTextActivity.PARAM_ID, itemId)
            startLargeTextForResult.launch(intent)*/
        }

        override fun onDateInputClick(viewId:String, value: String) {
            SelectDateFragment(viewId, this@MainActivity as ISelectedData).show(supportFragmentManager, "DatePicker")
        }

        override fun onTimeInputClick(viewId:String, value: String) {
            Log.e("onTimeInputClick", "viewId:${viewId}, Values:${value}")
            SelectTimeFragment(viewId, this@MainActivity as ISelectedData).show(supportFragmentManager, "TimePikcer")
        }

        override fun onClick(itemId: String, data: ArrayList<String>, readOnly: Boolean, options: FileOptions?) {
            Log.e("DialogData", "Id:${itemId}, Values:${data.size}")
            if(itemId=="11") {
                cform.setValue(itemId, arrayListOf("/my/fake/signature1.jpg","/my/fake/signature2.jpg","/my/fake/signature3.jpg"))
            }else{
                cform.setValue(itemId, fileUri("IMG_20211007_005235.jpg"))
            }
            /*when(cform.getType(itemId)){
                ViewTypes.FILES -> {
                    val intent = Intent(this@MainActivity, FileManagerActivity::class.java)
                    intent.putExtra(FileManagerActivity.PARAM_OPTIONS, arrayListOf("GALLERY", "CAMERA", "DOCUMENTS"))
                    intent.putExtra(FileManagerActivity.PARAM_TITLE, cform.getTitle(itemId))
                    intent.putExtra(FileManagerActivity.PARAM_ATTACHMENT, data)
                    intent.putExtra(FileManagerActivity.PARAM_ID, itemId)
                    startFileManagerForResult.launch(intent)
                }
                ViewTypes.SIGNATURE -> {
                    val intent = Intent(this@MainActivity, SignatureActivity::class.java)
                    intent.putExtra(SignatureActivity.PARAM_TITLE, cform.getTitle(itemId))
                    intent.putExtra(SignatureActivity.PARAM_SIGNATURE, data.first().toString())
                    intent.putExtra(SignatureActivity.PARAM_ID, itemId)
                    startSignatureForResult.launch(intent)
                }
            }*/
        }

        override fun onClick(itemId: String, data: String) {
            Log.e("DialogData", "Id:${itemId}, Values:${data}")
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



    fun fileUri(fileName: String): String {
        return  cacheDir.absolutePath + "/attachment/" + fileName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                viewListContainer.background = ColorDrawable(resources.getColor(R.color.darkNightBackGround))
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                viewListContainer.background = ColorDrawable(resources.getColor(R.color.white))
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }

        //val sampleJson = getFormConfig("template.json")
        //val sampleJson = getFormConfig("template_viewer.json")
        val sampleJson = getFormConfig("template_acta.json")
        //val sampleJson = JSONObject("{\"questions\":[{\"view_id\":1,\"title\":\"Hora de inicio\",\"hint\":\"HH:MM\",\"vtype\":\"time\",\"mandatory\":true},{\"view_id\":2,\"title\":\"Hora de termino\",\"hint\":\"HH:MM\",\"vtype\":\"time\",\"mandatory\":true},{\"view_id\":3,\"title\":\"Colacion\",\"textOff\":\"Verdadero\",\"textOn\":\"Falso\",\"default\":true,\"vtype\":\"switch\"},{\"view_id\":4,\"title\":\"Numero de sellos o comprobante\",\"hint\":\"Escriba aquí\",\"vtype\":\"text\",\"mandatory\":true},{\"view_id\":5,\"title\":\"Descripcion de equipos\",\"hint\":\"Escriba aquí\",\"vtype\":\"text\",\"mandatory\":true,\"text_options\":{\"max_chars\":10}},{\"view_id\":6,\"title\":\"Detalle de los servicios\",\"hint\":\"Escriba aquí\",\"vtype\":\"text\",\"mandatory\":true,\"text_options\":{\"external_text\":true,\"max_chars\":1000,\"min_chars\":10,\"max_lines\":2}},{\"view_id\":7,\"title\":\"Desea agregar al resto del personal en su acta?\",\"hint\":\"Escriba aquí\",\"vtype\":\"checkbox\",\"mandatory\":true,\"items\":[{\"itemId\":\"10431387\",\"itemText\":\"Mónica Díaz Badillo\"},{\"itemId\":\"10504403\",\"itemText\":\"Claudio Orellana C.\"}]},{\"view_id\":8,\"title\":\"Firma del cliente\",\"vtype\":\"signature\",\"layout_options\":{\"button_text\":\"Ingresar Firma\"},\"mandatory\":true},{\"view_id\":10,\"title\":\"Envia copia de acta de inspeccion al cliente\",\"textOff\":\"Verdadero\",\"textOn\":\"Falso\",\"default\":false,\"vtype\":\"switch\"},{\"view_id\":11,\"title\":\"Archivos adjuntos\",\"vtype\":\"files\",\"min_files\":1,\"max_files\":3,\"layout_options\":{\"button_text\":\"Adjuntos\"},\"mandatory\":false},{\"view_id\":13,\"title\":\"\",\"vtype\":\"dual_button\",\"layout_options\":{\"titleA\":\"Guardar\",\"titleB\":\"Cancelar\",\"only_show_on_edit\":true,\"color\":\"BLUE\"}}]}")

        cform.mainListener = mainListener
        //cform.buildLayout(this, sampleJson, viewContainer, true)
        cform.buildRecycler(this, sampleJson, viewListContainer, false)

        val f = File(fileUri("IMG_20211007_005235.jpg"))
        Log.e("fileURI", f.path)
        Log.e("fileURIExists", f.exists().toString())

        /*val requiresoptions = cform.viewsRequireExternalData()
        ** /data/data/cl.datageneral.customforms/cache/attachment/IMG_20211007_005235.jpg

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
        val answerAll0 = "{\"answers\":[{\"view_id\":\"1\",\"value\":[\"11:50\"]},{\"view_id\":\"2\",\"value\":[\"12:25\"]},{\"view_id\":\"3\",\"value\":[false]},{\"view_id\":\"4\",\"value\":[\"cmVhbGx5IGdvb2QgcmlnaHQgbm93IGJ1dCBJIHdpbGwgYmUgdGhlcmUgYXQgdGhl\\n\"]},{\"view_id\":\"5\",\"value\":[\"dGhhbmtz\\n\"]},{\"view_id\":\"6\",\"value\":[\"TG9yZW0gaXBzdW0gZG9sb3Igc2l0IGFtZXQsIGNvbnNlY3RldHVyIGFkaXBpc2NpbmcgZWxpdCwg\\nc2VkIGRvIGVpdXNtb2QgdGVtcG9yIGluY2lkaWR1bnQgdXQgbGFib3JlIGV0IGRvbG9yZSBtYWdu\\nYSBhbGlxdWEuIFV0IGVuaW0gYWQgbWluaW0gdmVuaWFtLCBxdWlzIG5vc3RydWQgZXhlcmNpdGF0\\naW9uIHVsbGFtY28gbGFib3JpcyBuaXNpIHV0IGFsaXF1aXAgZXggZWEgY29tbW9kbyBjb25zZXF1\\nYXQuIER1aXMgYXV0ZSBpcnVyZSBkb2xvciBpbiByZXByZWhlbmRlcml0IGluIHZvbHVwdGF0ZSB2\\nZWxpdCBlc3NlIGNpbGx1bSBkb2xvcmUgZXUgZnVnaWF0IG51bGxhIHBhcmlhdHVyLiBFeGNlcHRl\\ndXIgc2ludCBvY2NhZWNhdCBjdXBpZGF0YXQgbm9uIHByb2lkZW50LCBzdW50IGluIGN1bHBhIHF1\\naSBvZmZpY2lhIGRlc2VydW50IG1vbGxpdCBhbmltIGlkIGVzdCBsYWJvcnVtLg==\\n\"]},{\"view_id\":\"7\",\"value\":[{\"itemId\":\"2\",\"itemText\":\"Text 2\"},{\"itemId\":\"4\",\"itemText\":\"Text 4\"}]},{\"view_id\":\"8\",\"value\":[\"/data/user/0/cl.datageneral.customforms/cache/attachment/IMG_20211007_005235.jpg\"]},{\"view_id\":\"10\",\"value\":[true]},{\"view_id\":\"11\",\"value\":[\"/my/fake/signature1.jpg\",\"/my/fake/signature2.jpg\",\"/my/fake/signature3.jpg\"]}]}"
        val answerAll = "{\"answers\":[{\"view_id\":\"1\",\"value\":[\"05:38\"]},{\"view_id\":\"2\",\"value\":[\"03:38\"]},{\"view_id\":\"14\",\"value\":[\"5.5\"]},{\"view_id\":\"3\",\"value\":[true]},{\"view_id\":\"4\",\"value\":[\"TG9yZW0gaXBzdW0gZG9sb3Igc2l0IGFtZXQsIGNvbnNlY3RldHVyIGFkaXBpc2NpbmcgZWxpdCwg\\nc2VkIGRvIGVpdXNtb2QgdGVtcG9yIGluY2lkaWR1bnQgdXQgbGFib3JlIGV0IGRvbG9yZSBtYWdu\\nYSBhbGlxdWEuIFV0IGVuaW0gYWQgbWluaW0gdmVuaWFtLCBxdWlzIG5vc3RydWQgZXhlcmNpdGF0\\naW9uIHVsbGFtY28gbGFib3JpcyBuaXNpIHV0IGFsaXF1aXAgZXggZWEgY29tbW9kbyBjb25zZXF1\\nYXQuIER1aXMgYXV0ZSBpcnVyZSBkb2xvciBpbiByZXByZWhlbmRlcml0IGluIHZvbHVwdGF0ZSB2\\nZWxpdCBlc3NlIGNpbGx1bSBkb2xvcmUgZXUgZnVnaWF0IG51bGxhIHBhcmlhdHVyLiBFeGNlcHRl\\ndXIgc2ludCBvY2NhZWNhdCBjdXBpZGF0YXQgbm9uIHByb2lkZW50LCBzdW50IGluIGN1bHBhIHF1\\naSBvZmZpY2lhIGRlc2VydW50IG1vbGxpdCBhbmltIGlkIGVzdCBsYWJvcnVtLg==\\n\"]},{\"view_id\":\"5\",\"value\":[\"\"]},{\"view_id\":\"6\",\"value\":[\"\"]},{\"view_id\":\"7\",\"value\":[]},{\"view_id\":\"8\",\"value\":[\"\"]},{\"view_id\":\"10\",\"value\":[false]},{\"view_id\":\"11\",\"value\":[]}]}"

        saveBtn.setOnClickListener {
            val answers = cform.formAnswers
            Log.e("Answers", answers.toString())
        }

        setBtn.setOnClickListener {
            //cform.formAnswer =
            cform.formAnswers = Pair(getFormConfig("template_answers3.json"), arrayListOf())
            //val actas = "{\"answers\":[{\"view_id\":\"1\",\"value\":[\"11:07\"]},{\"view_id\":\"2\",\"value\":[\"03:07\"]},{\"view_id\":\"3\",\"value\":[true]},{\"view_id\":\"4\",\"value\":[\"dGVzdCBva2lv\\n\"]},{\"view_id\":\"5\",\"value\":[\"\"]},{\"view_id\":\"6\",\"value\":[\"\"]},{\"view_id\":\"7\",\"value\":[{\"itemId\":\"10431387\",\"itemText\":\"Mónica Díaz Badillo\"}]},{\"view_id\":\"8\",\"value\":[\"\"]},{\"view_id\":\"10\",\"value\":[false]},{\"view_id\":\"11\",\"value\":[]},{}]}"
            //cform.formAnswers = Pair(JSONObject(answerFiles), arrayListOf())
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
            cform.setValue("11", listOf("/sign/picture1", "/sign/picture12", "/sign/picture13"))
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