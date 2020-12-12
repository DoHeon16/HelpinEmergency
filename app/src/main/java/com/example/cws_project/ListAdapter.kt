package com.example.cws_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(val items:ArrayList<CommonInfo>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun OnItemClick(holder:ViewHolder, view:View, data: CommonInfo, position:Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var name:TextView=itemView.findViewById(R.id.name)
        var tel:TextView=itemView.findViewById(R.id.tel)
        var addr:TextView=itemView.findViewById(R.id.address)
        val bookmark:ImageView=itemView.findViewById(R.id.bookmark)
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
        holder.name.text=items[position].name
        holder.tel.text=items[position].tel
        holder.addr.text=items[position].addr
        if(items[position].bookmark){
            holder.bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp)
        }else{
            holder.bookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}