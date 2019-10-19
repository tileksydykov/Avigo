package kg.flaterlab.lifeplanner

import android.util.Log

interface Logger {
    fun log(msg: String){
        Log.d("Mylog ${javaClass.canonicalName}", msg)
    }
}