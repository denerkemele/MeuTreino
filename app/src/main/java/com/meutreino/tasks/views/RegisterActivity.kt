package com.meutreino.tasks.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

import com.devmasterteam.tasks.R

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val mViewHolder = ViewHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializa elementos
        this.mViewHolder.editName = this.findViewById<View>(R.id.edit_name) as EditText
        this.mViewHolder.editEmail = this.findViewById<View>(R.id.edit_email) as EditText
        this.mViewHolder.editPassword = this.findViewById<View>(R.id.edit_password) as EditText
        this.mViewHolder.buttonSave = this.findViewById<View>(R.id.button_save) as Button

        // Inicializa eventos
        this.mViewHolder.buttonSave!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_save) {
            // TODO
        }
    }

    /**
     * ViewHolder
     */
    private class ViewHolder {
        var imageBack: ImageView? = null
        var editName: EditText? = null
        var editEmail: EditText? = null
        var editPassword: EditText? = null
        var buttonSave: Button? = null
    }
}
