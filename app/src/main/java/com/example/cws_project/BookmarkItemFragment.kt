package com.example.cws_project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [BookmarkItemFragment.OnListFragmentInteractionListener] interface.
 */
class BookmarkItemFragment : Fragment() {

    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null
    var medicalNum=1   //1-병의원, 2-약국, 3-응급기관
    private val item= arrayListOf<String>()//데이터베이스에 추가되어 있는 즐겨찾기된 각 기관 이름 읽어와서 저장

    companion object{
        fun newItemFragment(num:Int):BookmarkItemFragment{
            val itemFragment=BookmarkItemFragment()
            itemFragment.medicalNum=num
            return itemFragment
        }
    }

    private fun readBookmark(num:Int){//num에 따른 각 기관의 북마크되어있는 목록 가져오기
        item.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        readBookmark(medicalNum)
        //medicalNum에 따른 항목 가져오기
        val view = inflater.inflate(R.layout.fragment_bookmark_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyBookmarkItemRecyclerViewAdapter(item, listener)//데이터베이스에 저장한 각 정보 넣기
            }
        }
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: String?) //데이터베이스에 저장한 각 정보 넣기
    }

}
