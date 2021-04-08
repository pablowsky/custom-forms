package cl.datageneral.customforms

import android.app.Activity
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.datageneral.customforms.factory.ViewFactory
import cl.datageneral.customforms.factory.custominputs.*
import cl.datageneral.customforms.helpers.*
import cl.datageneral.customforms.inputs.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class CustomFormBuilder {
    val viewList:ArrayList<InputBase>     = ArrayList()
    var adapter:CustomFormAdapter?=null
    var layoutContainer:LinearLayout? =null
    var readOnly:Boolean = false

    private var selectListener = object : ItemSelectedListener{
        override fun onSelectInputClick(viewId: String, value: String) {

            //recursiveSet(viewId, value)
        }

        override fun onLoadChildrensClick(  parentViewId: String, selectedValue: String ) {
            recursiveSet(parentViewId, selectedValue)
        }
    }

    var datetimeListener = object : DateTimeClickListener{
        override fun onDateInputClick(viewId:String, value: String) {
            TODO("Not yet implemented")
        }

        override fun onTimeInputClick(viewId:String, value: String) {
            TODO("Not yet implemented")
        }
    }

    var externalListener = object :ExternalChangeListenerListener{
        override fun onExternalChange(viewId: String, searchKey: String, parent: String) {
            TODO("Not yet implemented")
        }
    }

    fun recursiveSet(parentViewId:String, selectedValue:String){
        val layout  = (layoutContainer as LinearLayout)
        for (i in 0 until layout.childCount) {
            val view = layout.getChildAt(i) as PmView
            if(view is PmSelectView && view.hasParent==parentViewId){
                Log.e("recSet PmSelectView", "${view.viewId} ${view.hasParent} $parentViewId")
                view.parentSelected = selectedValue
            }
            if(view is PmExternalView && view.hasParent==parentViewId){
                Log.e("recSet PmExternalView", "${view.viewId} ${view.hasParent} $parentViewId")
                view.parentSelected = selectedValue
            }
        }

        /*for ((key, itm) in items.withIndex()) {
            if (itm is InputSelectView) {
                if (itm.hasParent == parentViewId) {
                    itm.parentSelected = selectedValue
                    notifyItemChanged(key)
                }
            }
            if (itm is InputExternalView) {
                if (itm.hasParent == parentViewId) {
                    itm.parentSelected = selectedValue
                    notifyItemChanged(key)
                }
            }
        }*/
    }

    fun buildLayout(activity: Activity, jsonForm:JSONObject, container:LinearLayout, pReadOnly:Boolean=false){
        readOnly        = pReadOnly
        layoutContainer = container
        // Parse JSON
        val inputList    = Json.getArray(jsonForm, "questions")
        val size         = inputList?.length()?:0
        for (input in 0 until size){
            val qObject  = inputList?.getJSONObject(input)

            val iFactory    = ViewFactory(qObject!!)
            val input       = iFactory.build(pReadOnly)

            //input?.let { viewList.add(it) }
            val dView = when(input){
                is InputTextView -> {
                    input.draw(PmTextView(activity))
                }
                is InputSelectView -> {
                    input.draw(PmSelectView(activity), selectListener)
                }
                is InputExternalView -> {
                    input.draw(PmExternalView(activity), externalListener)
                }
                is InputDatetimeView -> {
                    input.draw(PmDatetimeView(activity), datetimeListener)
                }
                else -> PmView(activity)
            }
            layoutContainer?.addView(dView as View)

        }

    }

    fun buildRecycler(activity: Activity, jsonForm:JSONObject, container:RecyclerView, pReadOnly:Boolean=false){
        // Parse JSON
        val inputList    = Json.getArray(jsonForm, "questions")
        val size         = inputList?.length()?:0
        for (input in 0 until size){
            val qObject  = inputList?.getJSONObject(input)

            val iFactory    = ViewFactory(qObject!!)
            val input       = iFactory.build(pReadOnly)

            input?.let { viewList.add(it) }
        }

        // Set the recycler view
        adapter = CustomFormAdapter(activity, selectListener, externalListener, datetimeListener)
        container.layoutManager  = LinearLayoutManager(activity)
        container.adapter        = adapter
        setData(viewList)

        /*container.post {
            for ((position, view) in viewList.withIndex()) {
                // Call every external listener after the adapter has end with the load
                if (view is InputExternalView) {
                    externalListener.onExternalChange(position, view.searchKey, view.parentSelected)
                }
            }
        }*/
    }

    private fun setData(items:ArrayList<InputBase>){
        adapter?.let {
            it.setDataSet(items)
        }
    }

    fun setOptions(viewId:String, options:ArrayList<SpinnerItem>){
        /*val view = viewList[itemPosition]
        if(view is InputSelectView){
            view.options = options
            adapter?.notifyItemChanged(itemPosition)
        }*/
        val layout  = (layoutContainer as LinearLayout)
        for (i in 0 until layout.childCount) {
            val view = layout.getChildAt(i)
            if(view is PmSelectView && view.viewId==viewId){
                view.options = options
            }
        }
    }

    fun viewsRequireExternalData():ArrayList<OptionsForView>{
        val list:ArrayList<OptionsForView> = ArrayList()
        /*for((position, view) in viewList.withIndex()){
            if(view is InputSelectView){
                if(view.dataOrigin.isNotEmpty()){
                    list.add(
                        OptionsForView(position, view.dataOrigin)
                    )
                }
            }
        }*/
        val layout  = (layoutContainer as LinearLayout)
        for (i in 0 until layout.childCount) {
            val view = layout.getChildAt(i)
            if(view is PmSelectView && view.dataOrigin.isNotEmpty()) {
                list.add(
                    OptionsForView(viewId = view.viewId, dataCode = view.dataOrigin)
                )
            }
        }
        return list
    }

    fun validateFields():ValidationResults{
        val vr = ValidationResults(0,0)
        for((position, view) in viewList.withIndex()){
            if(view.isValid){
                vr.validFields++
            }else{
                vr.invalidFields++
            }
            adapter?.notifyItemChanged(position)
        }
        return vr
    }

    fun validateLayoutFields():ValidationResults{
        val vr      = ValidationResults(0,0)
        val layout  = (layoutContainer as LinearLayout)
        for (i in 0 until layout.childCount) {
            val view = layout.getChildAt(i)
            if(view is PmView) {
                if(view.isValid){
                    vr.validFields++
                }else{
                    vr.invalidFields++
                }
            }
        }
        return vr
    }

    var pairedData:ArrayMap<String, String>
        get(){
            val map:ArrayMap<String, String> = ArrayMap()
            for( view in adapter!!.items ){
                map[view.viewId] = view.value
            }
            return map
        }
        set(value) {
            for(map in value){
                for((position, view) in viewList.withIndex()){
                    if(view.viewId==map.key){
                        view.value = map.value
                        adapter?.notifyItemChanged(position)
                    }
                }
            }
        }

    fun setValue(position:Int, value:String, subValue:String?=null){
        if(subValue!=null){
            viewList[position].setValue(value, subValue!!)
        }
        adapter?.notifyItemChanged(position)
    }

    fun setValue(viewId: String, value:String, subValue:String?=null){
        val layout  = (layoutContainer as LinearLayout)
        for (i in 0 until layout.childCount) {
            val view = layout.getChildAt(i)
            if(view is PmDatetimeView && view.viewId==viewId) {
                view.setValue(value, subValue!!)
            }else if(view is PmView && view.viewId==viewId) {
                view.mainValue = value
            }
        }
        /*if(subValue!=null){
            viewList[position].setValue(value, subValue!!)
        }
        adapter?.notifyItemChanged(position)*/
    }

    fun setValue(position:Int, value:String){
        viewList[position].value = value
        adapter?.notifyItemChanged(position)
    }

    fun getValue(viewId:String):String{
        var value = ""
        for(v in viewList){
            if(v.viewId==viewId){
                value = v.value
            }
        }
        return value
    }

    var formAnswer:JSONObject
        get(){
            val answers = JSONArray()
            val layout  = (layoutContainer as LinearLayout)
            for (i in 0 until layout.childCount) {
                val view = layout.getChildAt(i)
                if(view is PmView) {
                    answers.put(view.answer)
                }
            }
            return JSONObject().apply {
                put("answers", answers)
            }
        }
        set(value) {
            val jAnswers = value.getJSONArray("answers")
            for(key in 0 until jAnswers.length()){
                val janswer = jAnswers.getJSONObject(key)
                val jviewId = janswer.getString("view_id")
                val layout  = (layoutContainer as LinearLayout)
                for (i in 0 until layout.childCount) {
                    val view = layout.getChildAt(i)
                    if(view is PmView){
                        if(jviewId==view.viewId){
                            Log.e("jAnswer", janswer.toString())
                            view.answer = janswer
                        }
                    }
                }
            }
        }
    /*
    var formAnswer:JSONObject
        get(){
            val answers = JSONArray()
            for( view in viewList ){
                answers.put(view.answer)
            }
            return JSONObject().apply {
                put("answers", answers)
            }
        }
        set(value) {
            val jAnswers = value.getJSONArray("answers")
            for(key in 0 until jAnswers.length()){
                val janswer = jAnswers.getJSONObject(key)
                val jviewId = janswer.getString("view_id")
                for((position, view) in viewList.withIndex()){
                    if(jviewId==view.viewId){
                        view.answer = janswer
                        adapter?.notifyItemChanged(position)
                    }
                }
            }
        }*/
    data class OptionsForView(
        var position:Int=0,
        var dataCode:String,
        var viewId:String="",
        var values:ArrayList<SpinnerItem> = ArrayList()
    )

    data class ValidationResults(
        var invalidFields:Int,
        var validFields:Int
    ){
        val isValidForm:Boolean
            get() {
                return invalidFields==0
            }
    }
}