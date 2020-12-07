package com.example.cws_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(val items:ArrayList<String>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder:ViewHolder,view :View, data:String,position:Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var textView:TextView=itemView.findViewById(R.id.kind)
        init {
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this,it,items[adapterPosition],adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.textView.text=items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }
}