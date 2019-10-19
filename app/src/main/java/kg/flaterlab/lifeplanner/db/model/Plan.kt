package kg.flaterlab.lifeplanner.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan")
data class Plan(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var type: Int,
    var name: String? = null,
    var visibility: Boolean = false,
    var priority: Long,
    var persentage: Int = 0,
    var planTrace: String = ""
)