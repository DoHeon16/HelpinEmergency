package com.example.cws_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_b_khos.*

/**
 * A simple [Fragment] subclass.
 */
class BkphaFragment(val phaarray:ArrayList<String>) : Fragment() {

    lateinit var layoutManager: LinearLayoutManager
    val mDB= FirebaseDatabase.getInstance()
    lateinit var bookmarkAdapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_b_khos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        var info=arrayListOf<String>()
        val database=mDB.getReference("Pharmacys")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children ){
                    info.add(i.key.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        Handler().postDelayed({},2000)


        layoutManager= LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL,false)
        recyler.layoutManager=layoutManager
        //bookmarkAdapter= BookmarkAdapter(info)
        bookmarkAdapter= BookmarkAdapter(phaarray,activity!!)
        bookmarkAdapter.itemClickListener=object :BookmarkAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int, foodPosition: Int) {
                //화면 넘기기
                database.child(info[position]).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val i=snapshot.getValue(BookmarkDB::class.java)
                        if(i!=null){
                            val a=RequestBookmark1(i).execute().get()
                            val e= Intent(activity!!,InfoActivity::class.java)
                            e.putExtra("medical","pharmacy")
                            val bundle=Bundle()
                            bundle.putSerializable("pharmacyinfo",a)
                            e.putExtras(bundle)
                            startActivity(e)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })

                Handler().postDelayed({},2000)


            }

        }
        Handler().postDelayed({},2000)

        recyler.adapter=bookmarkAdapter

    }

}
