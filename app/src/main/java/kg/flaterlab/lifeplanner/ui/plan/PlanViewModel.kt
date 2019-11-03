package kg.flaterlab.lifeplanner.ui.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kg.flaterlab.lifeplanner.data.AppRepository
import kg.flaterlab.lifeplanner.db.model.Plan

class PlanViewModel : ViewModel(){
    private lateinit var repository: AppRepository

    fun init(rep: AppRepository){
        repository = rep
    }

    fun getPlan(id :Long): LiveData<Plan> {
        return repository.getPlan(id)
    }




}