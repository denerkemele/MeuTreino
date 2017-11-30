package com.meutreino.tasks.views

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.devmasterteam.tasks.R
import com.meutreino.tasks.business.TaskBusiness
import com.meutreino.tasks.constants.TaskConstants
import com.meutreino.tasks.entities.TaskEntity

class TaskFormTarefaActivity : AppCompatActivity(), View.OnClickListener {

    // mapeai os elementos do XML
    private val mViewHolder = ViewHolder()
    private var mTaskBusiness: TaskBusiness? = null

    //armazena o ID do usuario
    private var mTaskId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form_tarefa)

        // Inicializa variáveis
        this.mViewHolder.editTarefa = this.findViewById<View>(R.id.edit_tarefa) as TextView
        this.mViewHolder.editCircuito = this.findViewById<View>(R.id.edit_circuito) as TextView
        this.mViewHolder.editTempo = this.findViewById<View>(R.id.edit_tempo) as TextView
        //this.mViewHolder.buttonSetTempo = (Button) this.findViewById(R.id.button_set_tempo);
        //this.mViewHolder.textoTeste = (EditText) this.findViewById(R.id.texto_teste);

        //Button agendar = (Button) findViewById(R.id.button_set_tempo);

        // final EditText segundos = (EditText) findViewById(R.id.texto_teste);

        this.mTaskBusiness = TaskBusiness(this)

        //        info = (TextView) findViewById(R.id.info);
        //        textAlarmPrompt = (TextView) findViewById(R.id.alarmprompt);


        //vai carregar o metodo para edição
        this.loadDataFromActivity()


        // Atribui eventos
        //this.mViewHolder.buttonSetTempo.setOnClickListener(this);

        //Button agendar = (Button) findViewById(R.id.button_set_tempo);

        this.mViewHolder.buttonAgendar = this.findViewById<View>(R.id.button_set_tempo) as Button
        //final EditText segundos = (EditText) findViewById(R.id.texto_teste);

        // this.mViewHolder.textoTeste = (EditText) this.findViewById(R.id.texto_teste);


        this.mViewHolder.buttonAgendar!!.setOnClickListener(this)


    }


    private fun loadDataFromActivity() {

        //recuperar pelo BUNDLE pq os dados foram passados pel INTENT
        val bundle = intent.extras

        //faz a vrificação para saber se foram passados dados por paremeto
        if (bundle != null) {
            //valor pego por parametro atrves da chave
            this.mTaskId = bundle.getInt(TaskConstants.BundleConstants.TASK_ID)

            val taskEntity = this.mTaskBusiness!!.load(this.mTaskId)

            this.mViewHolder.editTarefa!!.setText(taskEntity.atividade!!.toUpperCase())
            this.mViewHolder.editTempo!!.text = taskEntity.duracao
            this.mViewHolder.editCircuito!!.text = taskEntity.nomecircuito

        }
    }

    override fun onClick(v: View) {
        val id = v.id

        if (id == R.id.button_set_tempo) {
            this.AgendarTempo()
        }
    }

    private fun AgendarTempo() {

        val time = Integer.parseInt(mViewHolder.editTempo!!.text.toString())

        val intent = Intent(baseContext, AlarmReceiver::class.java)
        val p1 = PendingIntent.getBroadcast(applicationContext, 0, intent, 0)
        val a = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        a.set(AlarmManager.RTC, System.currentTimeMillis() + time * 1000, p1)
        Toast.makeText(applicationContext, "Treino Iniciado para $time segundos", Toast.LENGTH_LONG).show()
    }


    /**
     * ViewHolder
     */
    class ViewHolder {

        var textoTeste: EditText? = null

        var editTarefa: TextView? = null
        var editCircuito: TextView? = null
        var editTempo: TextView? = null

        //private Button buttonSetAlarm;
        var buttonSetTempo: Button? = null
        var buttonAgendar: Button? = null

    }
}
