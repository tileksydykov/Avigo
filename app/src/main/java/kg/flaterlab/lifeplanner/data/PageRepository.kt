package kg.flaterlab.lifeplanner.data

import android.util.Log
import androidx.lifecycle.LiveData
import kg.flaterlab.lifeplanner.db.dao.PlanDao
import kg.flaterlab.lifeplanner.db.model.Plan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class PageRepository (
    private val planDao: PlanDao
){
    fun insertPlan(plan: Plan) = runBlocking{
        this.launch(Dispatchers.IO){
            plan.priority = 1
            planDao.changePriorityInsert(plan.type)
            planDao.insert(plan)
        }
    }

    fun planChangePriority(plan: Plan, isUp: Boolean) = runBlocking{
        this.launch(Dispatchers.IO){
            if(isUp){
                planDao.planDownByPriority(plan.priority - 1, plan.type)
                planDao.planUp(plan.id)
            }else{
                planDao.planUpByPriority(plan.priority + 1, plan.type)
                planDao.planDown(plan.id)
            }
        }
    }

    fun deletePlan(plan: Plan) = runBlocking{
        this.launch(Dispatchers.IO) {
            plan.visibility = false
            plan.priority = 0
            planDao.changePriorityAfterDelete(plan.priority, plan.type)
            planDao.update(plan)
        }
    }

    fun getPlans(i: Int?):LiveData<List<Plan>>{
        if(i == 1){
            return getShortPlans()
        }else if(i == 2){
            return getLongPlans()
        }
        return planDao.all
    }

    private fun getLongPlans()= planDao.allLong
    private fun getShortPlans()= planDao.allShort
}