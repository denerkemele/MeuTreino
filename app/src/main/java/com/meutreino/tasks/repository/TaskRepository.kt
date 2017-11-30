package com.meutreino.tasks.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import com.meutreino.tasks.constants.DataBaseConstants
import com.meutreino.tasks.entities.TaskEntity

import java.util.ArrayList

class TaskRepository private constructor(context: Context) {
    //Singleton é um Designer Pattern, garante que não tenha mais de uma instancia na classe

    private val mTaskDataBaseHelper: TaskDataBaseHelper

    init {
        //Intanciar a data base e garante que este seja criado
        this.mTaskDataBaseHelper = TaskDataBaseHelper(context)
    }

    fun insert(taskEntity: TaskEntity): Boolean? {
        try {

            //obtenção da instancia
            //criar os contentValues concatenados
            val sQLiteDatabase = this.mTaskDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.TREINOS.COLUMNS.CIRCUITO, taskEntity.nomecircuito)
            contentValues.put(DataBaseConstants.TREINOS.COLUMNS.ATIVIDADE, taskEntity.atividade)
            contentValues.put(DataBaseConstants.TREINOS.COLUMNS.DATA, taskEntity.data)
            contentValues.put(DataBaseConstants.TREINOS.COLUMNS.DURACAO, taskEntity.duracao)
            contentValues.put(DataBaseConstants.TREINOS.COLUMNS.HORA, taskEntity.hora)

            //faz a inserção
            sQLiteDatabase.insert(DataBaseConstants.TREINOS.TABLE_NAME, null, contentValues)

            return true

        } catch (e: Exception) {

            return false

        }

    }

    /*  public Boolean insertSpinner(TaskEntity taskEntity) {
          try {

              //obtenção da instancia
              //criar os contentValues concatenados
              SQLiteDatabase sQLiteDatabase = this.mTaskDataBaseHelper.getWritableDatabase();

             // sQLiteDatabase.beginTransaction();
              ContentValues contentValues = new ContentValues();
              contentValues.put(DataBaseConstants.CIRCUITO.COLUMNS.NOMECIRCUITO, taskEntity.getNomecircuito());
              //faz a inserção
              sQLiteDatabase.insert(DataBaseConstants.CIRCUITO.TABLE_NAME, null, contentValues);
              //sQLiteDatabase.setTransactionSuccessful();
              return true;

          } catch (Exception e) {


              return false;

          }

      }
  */
    fun update(taskEntity: TaskEntity): Boolean? {

        try {

            val sqLiteDatabase = this.mTaskDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.TREINOS.COLUMNS.ATIVIDADE, taskEntity.atividade)
            contentValues.put(DataBaseConstants.TREINOS.COLUMNS.DATA, taskEntity.data)

            //id = ? pega o valor do ID
            val selection = DataBaseConstants.TREINOS.COLUMNS.ID + " = ?"
            val selectioArgs = arrayOf(taskEntity.id.toString())

            sqLiteDatabase.update(DataBaseConstants.TREINOS.TABLE_NAME, contentValues, selection, selectioArgs)
            return true
        } catch (e: Exception) {
            return false
        }

    }


    fun load(id: Int): TaskEntity {
        val taskEntity = TaskEntity()

        try {

            val sQLiteDatabase = this.mTaskDataBaseHelper.readableDatabase

            val cursor = sQLiteDatabase.rawQuery("select * from " + DataBaseConstants.TREINOS.TABLE_NAME + " where id =" + id.toString(), null)

            if (cursor != null && cursor.count > 0) {

                //mover para primeira posição que encontrar
                cursor.moveToFirst()
                taskEntity.id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TREINOS.COLUMNS.ID))
                taskEntity.atividade = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TREINOS.COLUMNS.ATIVIDADE))
                taskEntity.duracao = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TREINOS.COLUMNS.DURACAO))
                taskEntity.data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TREINOS.COLUMNS.DATA))
                taskEntity.nomecircuito = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TREINOS.COLUMNS.CIRCUITO))
            }
            cursor?.close()

            return taskEntity

        } catch (e: Exception) {
            return taskEntity
        }


    }

    fun getListaTreinosQuery(query: String): List<TaskEntity> {

        //cria um Array vazio
        val list = ArrayList<TaskEntity>()

        //apresentar a lista preenchida dentro do try
        try {

            //pegar a instancia do banco
            val sQLiteDatabase = this.mTaskDataBaseHelper.readableDatabase
            //rawQuery percorre linha a linha do banco de dados e com o retorno vai montar a lista

            val cursor = sQLiteDatabase.rawQuery(query, null)

            if (cursor != null && cursor.count > 0) {

                //preencher a lista
                while (cursor.moveToNext()) {

                    val taskEntity = TaskEntity()

                    //cursor.getString() precisa o codeindex
                    //cursor.getString(cursor.getColumnIndex()) vai definir o index, o cursor vai retornar o index da coluna
                    taskEntity.id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TREINOS.COLUMNS.ID))
                    taskEntity.atividade = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TREINOS.COLUMNS.ATIVIDADE))
                    taskEntity.duracao = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TREINOS.COLUMNS.DURACAO))
                    taskEntity.data = cursor.getString(cursor.getColumnIndex(DataBaseConstants.TREINOS.COLUMNS.DATA))


                    //add na lista o treino
                    list.add(taskEntity)
                }

            }

            //fecha a conexão
            cursor?.close()


        } catch (e: Exception) {

            return list
        }

        return list
    }

    fun getListaCisrcuitosQuery(s: String): ArrayList<String> {

        val list = ArrayList<String>()

        val db = this.mTaskDataBaseHelper.readableDatabase

        try {
            val cursor = db.rawQuery(s, null)

            if (cursor.count > 0) {

                while (cursor.moveToNext()) {

                    val pname = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CIRCUITO.COLUMNS.NOMECIRCUITO))
                    list.add(pname)

                }

            }
        } catch (e: Exception) {

            //return list;
        }

        return list
    }


    fun remove(id: Int): Boolean? {
        try {

            val db = this.mTaskDataBaseHelper.writableDatabase

            val whereClause = DataBaseConstants.CIRCUITO.COLUMNS.ID + " = ?"
            val whereArgs = arrayOf(id.toString())

            db.delete(DataBaseConstants.TREINOS.TABLE_NAME, whereClause, whereArgs)


            return true
        } catch (e: Exception) {
            return false
        }

    }

    companion object {

        private var INSTANCE: TaskRepository? = null

        // para acessar o repositorio tem que passar o contexto da aplicação
        //mesmo tendo multi thereds somente uma por vez utilizando o synchronized
        @Synchronized
        fun getInstance(context: Context): TaskRepository {

            if (INSTANCE == null) {
                INSTANCE = TaskRepository(context)
            }
            return INSTANCE as TaskRepository
        }
    }
}
