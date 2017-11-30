package com.meutreino.tasks.listeners

interface OnGuestListInteractionListener {

    /**
     * Click para edição
     */
    fun onListClick(id: Int)

    /**
     * Click para remoção
     */
    fun onDeleteClick(id: Int)

}