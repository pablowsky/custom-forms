package cl.datageneral.customforms.helpers

import java.nio.charset.StandardCharsets

/**
 * Created by Pablo Molina on 04-10-2021. s.pablo.molina@gmail.com
 */
class B64 {
    companion object{
        fun decode(str:String):String {
            return String(android.util.Base64.decode(str, android.util.Base64.DEFAULT), StandardCharsets.UTF_8)
        }

        fun encode(str:String):String {
            return String(android.util.Base64.encode(str.toByteArray(), android.util.Base64.DEFAULT), StandardCharsets.UTF_8)
        }
    }
}