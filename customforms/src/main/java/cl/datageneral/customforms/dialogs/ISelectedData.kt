package cl.datageneral.customforms.dialogs

/**
 * Created by Pablo Molina on 04-11-2020. s.pablo.molina@gmail.com
 */


interface ISelectedData {
    fun onDateSelected(position: String, date: String)
    fun onTimeSelected(position: String, time: String)
}
open class SelectedData: ISelectedData{
    override fun onDateSelected(position: String, date: String) {
        TODO("Not yet implemented")
    }

    override fun onTimeSelected(position: String, time: String) {
        TODO("Not yet implemented")
    }
}