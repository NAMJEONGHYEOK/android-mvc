package com.test.mvctest.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.mvctest.Model.MyModel

import com.test.mvctest.databinding.SeconderecyclerviewItemBinding

class secondRecyclerAdapter(private val data:List<MyModel>) :
RecyclerView.Adapter<secondRecyclerAdapter.secondViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): secondViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.seconderecyclerview_item,parent,false)
        val binding = SeconderecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return secondViewHolder(binding)
    }

    override fun onBindViewHolder(holder: secondViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() : Int = data.size

    class secondViewHolder(private val binding: SeconderecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(model: MyModel) {
                //바인딩
             binding.textItem.text = model.text
            }
        }
}