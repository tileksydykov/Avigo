package kg.flaterlab.lifeplanner

import android.app.Application

import androidx.room.Room
import kg.flaterlab.lifeplanner.db.AppDatabase

class App : Application() {

    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
    }


    companion object {
        private var instance: App? = null
        fun getInstance(): App = instance!!
    }
}