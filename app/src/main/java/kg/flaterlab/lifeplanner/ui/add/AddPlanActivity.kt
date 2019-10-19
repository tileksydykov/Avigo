package kg.flaterlab.lifeplanner.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kg.flaterlab.lifeplanner.App
import kg.flaterlab.lifeplanner.R
import kg.flaterlab.lifeplanner.data.PageRepository
import kg.flaterlab.lifeplanner.db.model.Plan
import kg.flaterlab.lifeplanner.ui.main.PageViewModel
import kg.flaterlab.lifeplanner.ui.main.PlaceholderFragment
import kotlinx.android.synthetic.main.activity_add_plan.*

class AddPlanActivity : AppCompatActivity() {
    private lateinit var viewModel: AddPlanViewModel
    private lateinit var repository: PageRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plan)

        var type = 0
        repository = PageRepository(App.getInstance().database!!.planDao())
        viewModel = ViewModelProviders.of(this)
            .get(AddPlanViewModel::class.java).apply {
                init(repository)
            }


        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                type = position
            }
        }
        add_button.setOnClickListener{
            val name: String = name_of_goal.text.toString()
            val plan :Plan = Plan(0, type, name, true, 1)
            viewModel.setPlan(plan)
            onBackPressed()
        }
    }
}
