package kg.flaterlab.lifeplanner.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kg.flaterlab.lifeplanner.db.model.Plan


@Dao
interface PlanDao {

    @get:Query("SELECT * FROM `plan` WHERE visibility = 1 ")
    val all: LiveData<List<Plan>>

    @get:Query("SELECT * FROM `plan` WHERE visibility = 0 ")
    val allDeleted: LiveData<List<Plan>>

    @get:Query("SELECT * FROM `plan` WHERE type = 0 AND visibility = 1 ORDER BY priority")
    val allShort: LiveData<List<Plan>>

    @get:Query("SELECT * FROM `plan` WHERE type = 1 AND visibility = 1 ORDER BY priority ")
    val allLong: LiveData<List<Plan>>

    @Query("SELECT * FROM `plan` WHERE id = :id")
    fun getById(id: Long): LiveData<Plan>

    @Insert
    suspend fun insert(plan: Plan)

    @Query("UPDATE `plan` SET priority = priority - 1 WHERE id = :id")
    fun planUp(id: Long)

    @Query("UPDATE `plan` SET priority = priority + 1 WHERE id = :id ")
    fun planDown(id: Long)

    @Query("UPDATE `plan` SET priority = priority - 1 WHERE priority = :priority AND type = :type")
    fun planUpByPriority(priority: Long, type: Int)

    @Query("UPDATE `plan` SET priority = priority + 1 WHERE priority = :priority AND type = :type")
    fun planDownByPriority(priority: Long, type: Int)

    @Query("UPDATE `plan` SET priority = priority - 1 WHERE priority > :priority AND type = :type")
    fun changePriorityAfterDelete(priority: Long, type: Int)

    @Query("UPDATE `plan` SET priority = priority + 1 WHERE type = :type")
    fun changePriorityInsert(type: Int)

    @Update
    fun update(plan: Plan)

    @Delete
    fun delete(plan: Plan)

}