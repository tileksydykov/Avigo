package kg.flaterlab.lifeplanner.ui.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kg.flaterlab.lifeplanner.App
import kg.flaterlab.lifeplanner.R
import kg.flaterlab.lifeplanner.data.AppRepository
import kg.flaterlab.lifeplanner.db.model.Plan
import kotlinx.android.synthetic.main.activity_plan.*

class PlanActivity : AppCompatActivity() {
    lateinit var viewModel :PlanViewModel
    private lateinit var repository: AppRepository
    lateinit var plan : LiveData<Plan>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan)

        val planId = intent.getLongExtra("id", 0)

        repository = AppRepository(App.getInstance().database!!.planDao())
        viewModel = ViewModelProviders.of(this)
            .get(PlanViewModel::class.java).apply {
                init(repository)
            }

        plan = viewModel.getPlan(planId)

        plan.observe(this, Observer<Plan> {
            planName.text = it.name
        })
    }
}
