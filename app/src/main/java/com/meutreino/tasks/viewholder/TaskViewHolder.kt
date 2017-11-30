package com.meutreino.tasks.viewholder

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.devmasterteam.tasks.R
import com.meutreino.tasks.entities.TaskEntity
import com.meutreino.tasks.listeners.OnGuestListInteractionListener
import com.meutreino.tasks.views.AlarmReceiver

import org.w3c.dom.Text

class TaskViewHolder(itemView: View, var mContex: Context) : RecyclerView.ViewHolder(itemView) {

    //objeto mapeado
    private val mTextName: TextView
    private val mTextName2: TextView
    private val mImageRemove: ImageView

    init {

        this.mTextName = itemView.findViewById<View>(R.id.text_name) as TextView
        this.mTextName2 = itemView.findViewById<View>(R.id.text_data) as TextView
        this.mImageRemove = itemView.findViewById<View>(R.id.image_remove_treino) as ImageView
    }

    fun bindData(taskEntity: TaskEntity, listener: OnGuestListInteractionListener) {

        //atribuição do evento
        this.mTextName.setText(taskEntity.atividade!!.toUpperCase())
        this.mTextName2.text = taskEntity.data

        //manipulação do evento
        this.mTextName.setOnClickListener { listener.onListClick(taskEntity.id) }

        this.mTextName2.setOnClickListener { listener.onListClick(taskEntity.id) }


        this.mImageRemove.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                AlertDialog.Builder(mContex)
                        .setTitle("Remoção do Treino")
                        .setMessage("Deseja Remover o Treino ?")
                        .setIcon(R.drawable.remove)
                        .setPositiveButton("Sim") { dialogInterface, i -> listener.onDeleteClick(taskEntity.id) }

                        .setNegativeButton("Não", null)
                        .show()

                this.cancelAlarm()

            }

            private fun cancelAlarm() {



                val intent = Intent(mContex, AlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(mContex, RQS_1, intent, 0)
                val alarmManager =  mContex.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmManager.cancel(pendingIntent)

            }
        })


        //        this.mTextName.setOnLongClickListener(new View.OnLongClickListener(){
        //            @Override
        //            public boolean onLongClick(View view) {
        //
        //                new AlertDialog.Builder(mContex)
        //                        .setTitle("Remoção do Treino")
        //                        .setMessage("Deseja Remover o Treino ?")
        //                        .setIcon(R.drawable.remove)
        //                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
        //
        //                            @Override
        //                            public void onClick(DialogInterface dialogInterface, int i) {
        //                                listener.onDeleteClick(taskEntity.getId());
        //                            }
        //                        })
        //
        //                        .setNegativeButton("Não", null)
        //                        .show();
        //
        //
        //                return true;
        //            }
        //
        //
        //        });


    }

    companion object {
        internal val RQS_1 = 1
    }

}


