package kg.flaterlab.lifeplanner.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.flaterlab.lifeplanner.App
import kg.flaterlab.lifeplanner.Logger
import kg.flaterlab.lifeplanner.adapters.MainAdapter
import kg.flaterlab.lifeplanner.R
import kg.flaterlab.lifeplanner.data.PageRepository
import kg.flaterlab.lifeplanner.db.dao.PlanDao
import kg.flaterlab.lifeplanner.db.model.Plan

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() , Logger{

    private lateinit var pageViewModel: PageViewModel

    lateinit var pageRepository: PageRepository

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MainAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(requireActivity())
            .get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 2)
        }
        pageRepository = PageRepository(App.getInstance().database!!.planDao())
        pageViewModel.init(pageRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_main, container, false)


        viewManager = LinearLayoutManager(context)

        viewAdapter = MainAdapter(arrayListOf(), this)

        recyclerView = root.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        val list = pageViewModel.get(arguments?.getInt(ARG_SECTION_NUMBER))
        list.observe(this, Observer<List<Plan>> {
            viewAdapter.update(it)
        })

        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}