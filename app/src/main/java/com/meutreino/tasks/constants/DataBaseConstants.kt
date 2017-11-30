package com.meutreino.tasks.constants

/**
 * Todas as constantes utilizadas no banco de dados
 * Tabelas, Colunas
 */
object DataBaseConstants {

    /**
     * Tabelas disponíveis no banco de dados com suas colunas
     */
    object TREINOS {
        val TABLE_NAME = "Treinos"

        object COLUMNS {
            val ID = "id"
            val DATA = "data"
            val ATIVIDADE = "atividade"
            val CIRCUITO = "circuito"
            val DURACAO = "duracao"
            val HORA = "hora"

        }
    }

    object CIRCUITO {
        val TABLE_NAME = "Circuito"

        object COLUMNS {
            val ID = "id"
            val NOMECIRCUITO = "NomeCircuito"


        }
    }
}
/**
 * Não deve ser instanciada
 */
