package kg.flaterlab.lifeplanner.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import kg.flaterlab.lifeplanner.R
import kg.flaterlab.lifeplanner.db.model.Plan
import kg.flaterlab.lifeplanner.ui.plan.PlanActivity
import kg.flaterlab.lifeplanner.ui.main.PlaceholderFragment
import kotlinx.android.synthetic.main.main_adapter_list_item.view.*


class MainAdapter(var myDataset: ArrayList<Plan>, var context: PlaceholderFragment) :

    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    class MyViewHolder(
        val relativeLayout: RelativeLayout,
        val adapter: MainAdapter,
        upButton: View = relativeLayout.findViewById(R.id.rl_up),
        downButton: View = relativeLayout.findViewById(R.id.rl_down)
    ) : RecyclerView.ViewHolder(relativeLayout){
        init {
            upButton.setOnClickListener(moveUp())
            downButton.setOnClickListener(moveDown())
        }
        private fun moveUp(): (View) -> Unit = {
            layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
                adapter.context.appRepository.planChangePriority(
                    adapter.myDataset[currentPosition],
                    true)
                adapter.myDataset[currentPosition].priority--
                adapter.myDataset[currentPosition - 1].priority++
                adapter.myDataset.removeAt(currentPosition).also {
                    adapter.myDataset.add(currentPosition - 1, it)
            }
                adapter.notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }

        private fun moveDown(): (View) -> Unit = {
            layoutPosition.takeIf { it < adapter.myDataset.size - 1 }?.also { currentPosition ->
                adapter.context.appRepository.planChangePriority(
                    adapter.myDataset[currentPosition],
                false)
                adapter.myDataset[currentPosition].priority++
                adapter.myDataset[currentPosition + 1].priority--
                adapter.myDataset.removeAt(currentPosition).also {
                    adapter.myDataset.add(currentPosition + 1, it)
                }
                adapter.notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {

        val relativeLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_adapter_list_item, parent, false) as RelativeLayout

        return MyViewHolder(relativeLayout, this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val plan = myDataset[position]
        holder.relativeLayout.text.text = plan.name
        holder.relativeLayout.progressBar.progress = plan.persentage
        val percentage = plan.persentage.toString() + "%"
        holder.relativeLayout.progressPercent.text = percentage
        holder.relativeLayout.setOnClickListener{
            val intent = Intent(context.context, PlanActivity::class.java)
            intent.putExtra("id", plan.id)
            context.startActivity(intent)
        }
        holder.relativeLayout.setOnLongClickListener {
            context.appRepository.deletePlan(plan)
            true
        }
    }


    override fun getItemCount() = myDataset.size
    fun update(data: List<Plan>){
        Log.d("dblog", data.toString())
        Log.d("dblog", myDataset.toString())
        if(data.size == itemCount){
            return
        }
        myDataset = arrayListOf()
        myDataset.addAll(data)
        this.notifyDataSetChanged()
    }
}