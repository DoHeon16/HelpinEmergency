package com.example.cws_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BookmarkAdapter(var info:ArrayList<String>,val context: Context):RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    var itemClickListener : OnItemClickListener ?= null

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var name : TextView
        init {
            name=itemView.findViewById(R.id.textView)

        }

    }
    interface OnItemClickListener{
        fun onItemClick(view : View, position : Int, foodPosition : Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookmark, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=info[position]
        holder.name.setOnClickListener {
            val database=FirebaseDatabase.getInstance().getReference("Hospitals")
                //화면 넘기기
                database.child(info[position]).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val i=snapshot.getValue(BookmarkDB::class.java)
                        if(i!=null){
                            val a=RequestBookmark1(i).execute().get()
                            val e= Intent(context!!,InfoActivity::class.java)
                            e.putExtra("medical","hospital")
                            val bundle= Bundle()
                            bundle.putSerializable("hospitalinfo",a)
                            e.putExtras(bundle)
                            context.startActivity(e)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
        }
    }

    override fun getItemCount(): Int {
        return info.size
    }
}