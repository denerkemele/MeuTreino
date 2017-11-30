package com.meutreino.tasks.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.devmasterteam.tasks.R
import com.meutreino.tasks.entities.TaskEntity
import com.meutreino.tasks.listeners.OnGuestListInteractionListener
import com.meutreino.tasks.viewholder.TaskViewHolder

//

class TaskListAdapter
/**
 * //     * Construtor
 * //      */

//recebendo uma lista taskEntityList
(private val mTaskEntityList: List<TaskEntity>, private val mOnGuestListInteractionListener: OnGuestListInteractionListener)//faz uma atribuição para mTaskEntityList com parametro de taskEntityList
//taskEntityList listagem de convidados passadas pro adapter this.mTaskEntityList
    : RecyclerView.Adapter<TaskViewHolder>() {


    //onCreateViewHolder instancia  o layout para fazer as alterações necessarias
    //metodo de criação
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {


        //buscar o contexto
        val context = parent.context

        //inlfar o layout para manipulação e faz uso na listagem
        val layoutInflater = LayoutInflater.from(context)
        //instancair a view  e espera um retorno view
        val carView = layoutInflater.inflate(R.layout.row_task_list2, parent, false)

        return TaskViewHolder(carView, context)
    }

    //troca um valor do laoyout ja intanciado por um valor da lista
    //metodo de preenchimento
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        //this.mTaskEntityList traz a lista
        //quem passa a postion é RecylcerView
        val taskEntity = this.mTaskEntityList[position]
        //ViewHolder vai manipular os dados, fica reponsavel por preencher os dados
        holder.bindData(taskEntity, mOnGuestListInteractionListener)
    }

    //conta os elementos que a ViewHolder esta trabalhando
    override fun getItemCount(): Int {
        //return 0;

        return this.mTaskEntityList.size
    }


    //    private List<TaskEntity> mListTaskEntities;
    //
    //    /**
    //     * Construtor
    //     */
    //    public TaskListAdapter(List<TaskEntity> taskList) {
    //    }
    //
    //    @Override
    //    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //
    //        Context context = parent.getContext();
    //
    //        // Infla o layout da linha e faz uso na listagem
    //        LayoutInflater inflater = LayoutInflater.from(context);
    //        View view = inflater.inflate(R.layout.row_task_list, parent, false);
    //
    //        return new TaskViewHolder(view);
    //    }
    //
    //    @Override
    //    public void onBindViewHolder(TaskViewHolder holder, int position) {
    //    }
    //
    //    @Override
    //    public int getItemCount() {
    //        return 0;
    //    }

}
