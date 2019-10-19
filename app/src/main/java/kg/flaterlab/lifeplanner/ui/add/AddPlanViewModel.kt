package kg.flaterlab.lifeplanner.ui.add

import androidx.lifecycle.ViewModel
import kg.flaterlab.lifeplanner.data.PageRepository
import kg.flaterlab.lifeplanner.db.model.Plan

class AddPlanViewModel: ViewModel(){
    private lateinit var repository: PageRepository

    fun init(rep: PageRepository){
        repository = rep
    }

    fun setPlan(plan : Plan){
        repository.insertPlan(plan)
    }
}