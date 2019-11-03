package kg.flaterlab.lifeplanner.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.flaterlab.lifeplanner.data.AppRepository
import kg.flaterlab.lifeplanner.db.model.Plan


class PageViewModel : ViewModel(){

    private lateinit var repository: AppRepository
    fun init(rep: AppRepository) {
        repository = rep
    }

    private val _index = MutableLiveData<Int>()

    fun get(int: Int?): LiveData<List<Plan>>{
        return repository.getPlans(int)
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}