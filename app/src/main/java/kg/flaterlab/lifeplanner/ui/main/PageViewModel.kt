package kg.flaterlab.lifeplanner.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kg.flaterlab.lifeplanner.data.PageRepository
import kg.flaterlab.lifeplanner.db.model.Plan
import java.lang.IllegalArgumentException
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kg.flaterlab.lifeplanner.Logger
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class PageViewModel : ViewModel(){

    private lateinit var repository: PageRepository
    fun init(rep: PageRepository) {
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