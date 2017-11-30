package com.meutreino.tasks.business


import android.content.Context

import com.meutreino.tasks.constants.DataBaseConstants
import com.meutreino.tasks.entities.TaskEntity
import com.meutreino.tasks.repository.TaskRepository

import java.util.ArrayList

class TaskBusiness(context: Context) {

    private val mTaskRepositorio: TaskRepository

    //chamar o repositorio que vai fazer a consulta do banco de dados e retornar a listagem
    val treinos: List<TaskEntity>
        get() = this.mTaskRepositorio.getListaTreinosQuery("select * from " + DataBaseConstants.TREINOS.TABLE_NAME)

    //chamar o repositorio que vai fazer a consulta do banco de dados e retornar a listagem
    val allCircuitos: ArrayList<String>
        get() = this.mTaskRepositorio.getListaCisrcuitosQuery("select * from " + DataBaseConstants.CIRCUITO.TABLE_NAME)

    init {
        this.mTaskRepositorio = TaskRepository.getInstance(context)
    }

    fun insert(taskEntity: TaskEntity): Boolean? {

        return this.mTaskRepositorio.insert(taskEntity)
    }

    /* public Boolean insertSpinner(TaskEntity taskEntity){

        return  this.mTaskRepositorio.insertSpinner(taskEntity);
    }*/

    fun update(taskEntity: TaskEntity): Boolean? {

        return this.mTaskRepositorio.update(taskEntity)
    }

    fun remove(id: Int): Boolean? {

        return this.mTaskRepositorio.remove(id)
    }

    //recebe um id
    fun load(id: Int): TaskEntity {
        // String query = ("select * from " + DataBaseConstants.TREINOS.TABLE_NAME +"where id = " + String.valueOf(id));
        return this.mTaskRepositorio.load(id)
    }

}
