package kg.flaterlab.lifeplanner.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import androidx.recyclerview.widget.RecyclerView
import kg.flaterlab.lifeplanner.R
import android.text.SpannableStringBuilder
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import kotlinx.android.synthetic.main.edit_step_item.view.*


class EditStepAdaptor(var myDataset: ArrayList<String>) :
    RecyclerView.Adapter<EditStepAdaptor.MyViewHolder>() {

    class MyViewHolder(val linearLayout: LinearLayout, val adapter: EditStepAdaptor)
        : RecyclerView.ViewHolder(linearLayout){

        fun setText(text: String){
            linearLayout.edite.text = SpannableStringBuilder(text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {

        val linearLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.edit_step_item, parent, false) as LinearLayout

        return MyViewHolder(linearLayout, this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val text = myDataset[position]
        holder.setText(text)
        holder.linearLayout.edite.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                myDataset[position] = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun getItemCount() = myDataset.size

    fun addItem(){
        if(myDataset.contains("")){
            val indexOfEmptyEditText = myDataset.indexOf("")
            errorEditText(indexOfEmptyEditText)
            myDataset.removeAt(indexOfEmptyEditText)
            return
        }
        myDataset.add("")
        notifyDataSetChanged()
    }

    private fun errorEditText(index: Int){
        notifyItemRemoved(index)
    }
}