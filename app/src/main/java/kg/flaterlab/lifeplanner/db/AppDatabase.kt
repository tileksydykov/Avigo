package kg.flaterlab.lifeplanner.db

import androidx.room.RoomDatabase
import androidx.room.Database
import kg.flaterlab.lifeplanner.db.dao.PlanDao
import kg.flaterlab.lifeplanner.db.model.Plan

@Database(entities = [Plan::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun planDao(): PlanDao
}