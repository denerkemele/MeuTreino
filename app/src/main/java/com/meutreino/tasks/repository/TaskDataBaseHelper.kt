package com.meutreino.tasks.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.meutreino.tasks.constants.DataBaseConstants

// leitura e escrita banco de dados
internal class TaskDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override//metodo chamado apenas uma vez na instalação do app
    fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TREINOS)
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_CIRCUITO)
        sqLiteDatabase.execSQL(SQL_INSERT_TABLE_CIRCUITO)

    }

    override// quando muda a versão do banco de dados
    fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        // sqLiteDatabase.execSQL(DROP_TABLE_GUEST);
        // sqLiteDatabase.execSQL(SQL_CREATE_TABLE_GUEST);
    }

    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "MeuTreino.db"

        // Criação da tabela de categoria
        private val SQL_CREATE_TABLE_TREINOS = (
                "create table " + DataBaseConstants.TREINOS.TABLE_NAME + " ("
                        + DataBaseConstants.TREINOS.COLUMNS.ID + " integer primary key autoincrement, "
                        + DataBaseConstants.TREINOS.COLUMNS.ATIVIDADE + " text null,"
                        + DataBaseConstants.TREINOS.COLUMNS.DURACAO + " text null,"
                        + DataBaseConstants.TREINOS.COLUMNS.CIRCUITO + " text null,"
                        + DataBaseConstants.TREINOS.COLUMNS.HORA + " text null,"
                        + DataBaseConstants.TREINOS.COLUMNS.DATA + " text null);")

        // Criação da tabela de categoria
        private val SQL_CREATE_TABLE_CIRCUITO = (
                "create table " + DataBaseConstants.CIRCUITO.TABLE_NAME + " ("
                        + DataBaseConstants.CIRCUITO.COLUMNS.ID + " integer primary key autoincrement, "
                        + DataBaseConstants.CIRCUITO.COLUMNS.NOMECIRCUITO + " text null);")


        private val SQL_INSERT_TABLE_CIRCUITO = (
                "insert into " + DataBaseConstants.CIRCUITO.TABLE_NAME
                        + "(" + DataBaseConstants.CIRCUITO.COLUMNS.NOMECIRCUITO + ") " +
                        "values ('CORRER'),('NADAR'),('LUTAR'),('ALONGAMENTO'),('VOLEI'),('CROSSFIT'),('YOGA')")

        // Remoção de tabelas
        private val DROP_TABLE_PRIORITY = "DROP TABLE IF EXISTS " + DataBaseConstants.TREINOS.TABLE_NAME
    }

}