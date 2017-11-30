package com.meutreino.tasks.views


import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.devmasterteam.tasks.R
import com.meutreino.tasks.adapter.TaskListAdapter
import com.meutreino.tasks.business.TaskBusiness
import com.meutreino.tasks.entities.TaskEntity

class AllTreinosFragment : Fragment() {

    //intanciar a viewHolder
    private val mViewHolder = ViewHolder()

    //fazer a definição da camada Business
    private var mTaskBusiness: TaskBusiness? = null

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saveInstanceState: Bundle): View? {

        val view = inflater.inflate(R.layout.fragment_all_treinos, container, false)

        val context = view.context

        //passos para instanciar a viewHolder

        //obter a Ricicler
        this.mViewHolder.mRecyclerAllTreinos = view.findViewById<View>(R.id.recycler_alL_treinos) as RecyclerView

        //instanciar a camada Business
        this.mTaskBusiness = TaskBusiness(context)
        val taskEntityList = this.mTaskBusiness!!.treinos

        //o Adapter faz a junção dos dados com a RecylerView
        //Definir um adapter
        //TaskListAdapter taskListAdapter  = new TaskListAdapter(taskEntityList);
        //setar o o adapter para recycler View
        //this.mViewHolder.mRecyclerAllTreinos.setAdapter(taskListAdapter);

        //Definir o layout
        this.mViewHolder.mRecyclerAllTreinos!!.layoutManager = LinearLayoutManager(context)

        return view
    }

    // mapear os elementos
    private class ViewHolder {
        internal var mRecyclerAllTreinos: RecyclerView? = null

    }
}
