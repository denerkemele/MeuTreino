package com.meutreino.tasks.views

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast

import com.devmasterteam.tasks.R
import com.meutreino.tasks.business.TaskBusiness
import com.meutreino.tasks.constants.TaskConstants
import com.meutreino.tasks.entities.TaskEntity

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.SimpleTimeZone

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    // mapeai os elementos do XML
     val mViewHolder = ViewHolder()
     var mTaskBusiness: TaskBusiness? = null

    //armazena o ID do usuario
    private var mTaskId = 0


    internal lateinit var info: TextView
    internal lateinit var textAlarmPrompt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        // Inicializa variáveis
        this.mViewHolder.editAtividade = this.findViewById<View>(R.id.edit_description) as EditText
        this.mViewHolder.editDuracao = this.findViewById<View>(R.id.edit_tempo) as EditText
        //this.mViewHolder.checkComplete = (CheckBox) this.findViewById(R.id.check_complete);
        //this.mViewHolder.spinnerPriority = (Spinner) this.findViewById(R.id.spinner_priority);
        this.mViewHolder.buttonDate = this.findViewById<View>(R.id.button_date) as Button
        this.mViewHolder.buttonHora = this.findViewById<View>(R.id.button_hora) as Button
        //this.mViewHolder.buttonSetAlarm = (Button) this.findViewById(R.id.button_set_alarm);
        //this.mViewHolder.buttonCancelar = (Button) this.findViewById(R.id.button_cancel);
        this.mViewHolder.buttonSave = this.findViewById<View>(R.id.button_save) as Button
        this.mViewHolder.progressDialog = ProgressDialog(this)

        this.mTaskBusiness = TaskBusiness(this)

        info = findViewById<View>(R.id.info) as TextView
        textAlarmPrompt = findViewById<View>(R.id.alarmprompt) as TextView


        // Atribui eventos
        this.mViewHolder.buttonSave!!.setOnClickListener(this)
        this.mViewHolder.buttonDate!!.setOnClickListener(this)
        // this.mViewHolder.buttonSetAlarm.setOnClickListener(this);
        //this.mViewHolder.buttonCancelar.setOnClickListener(this);
        this.mViewHolder.buttonHora!!.setOnClickListener(this)


        //this.setListeners();


        //vai carregar o metodo para edição
        this.loadDataFromActivity()

        // Spinner spinner  = (Spinner) findViewById(R.id.spinner);

        this.mViewHolder.spinner = this.findViewById<View>(R.id.spinner) as Spinner

        val list = this.mTaskBusiness!!.allCircuitos

        val adapter = ArrayAdapter(this, R.layout.spinner_layout, R.id.txt, list)
        // spinner.setAdapter(adapter);

        this.mViewHolder.spinner!!.adapter = adapter


    }

    private fun loadDataFromActivity() {

        //recuperar pelo BUNDLE pq os dados foram passados pel INTENT
        val bundle = intent.extras

        //faz a vrificação para saber se foram passados dados por paremeto
        if (bundle != null) {
            //valor pego por parametro atrves da chave
            this.mTaskId = bundle.getInt(TaskConstants.BundleConstants.TASK_ID)

            val taskEntity = this.mTaskBusiness!!.load(this.mTaskId)

            this.mViewHolder.editAtividade!!.setText(taskEntity.atividade)

        }
    }


    override fun onClick(v: View) {
        val id = v.id

        if (id == R.id.button_save) {
            this.SalvarDados()
        } else if (id == R.id.button_date) {
            this.showDatePicker()
        } else if (id == R.id.button_hora) {
            // this.showHoraPicker();
        }
        // else if (id == R.id.button_set_alarm) {
        //    this.showAlarm();
        //  }
        // else if (id == R.id.button_cancel) {
        //     this.cancelAlarm();
        // }

    }

    private fun SalvarDados() {
        val taskEntity = TaskEntity()
        taskEntity.atividade = this.mViewHolder.editAtividade!!.text.toString()
        taskEntity.data = this.mViewHolder.buttonDate!!.text.toString()
        taskEntity.duracao = this.mViewHolder.editDuracao!!.text.toString()
        taskEntity.nomecircuito = this.mViewHolder.spinner!!.selectedItem.toString()
        taskEntity.hora = this.mViewHolder.buttonHora!!.text.toString()



        if (mTaskId == 0) {
            //salva entidade convidado
            if (this.mTaskBusiness!!.insert(taskEntity)!!) {

                Toast.makeText(this, getString(R.string.salvo_com_suceso), Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this, getString(R.string.erro_ao_suceso), Toast.LENGTH_LONG).show()
            }
        } else {

            //salva entidade convidado
            if (this.mTaskBusiness!!.update(taskEntity)!!) {

                Toast.makeText(this, getString(R.string.salvo_com_suceso), Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this, getString(R.string.erro_ao_suceso), Toast.LENGTH_LONG).show()
            }
        }


        finish()
    }


    //O usuario escolhe a data
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val hora = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)


        TimePickerDialog(this, this, hora, minute, true).show()// show() para aparecer na tela

        DatePickerDialog(this, this, year, month, dayOfMonth).show()// show() para aparecer na tela
    }


    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        //preenche o valor clicando no botão
        this.mViewHolder.buttonDate!!.text = SIMPLE_DATE_FORMAT.format(calendar.time)//quando o usuario seleciona e a data vai ser formatada


        // necessita da classe Calender
    }


    //    private void showHoraPicker() {
    //        Calendar calendar = Calendar.getInstance();
    //        int hora = calendar.get(Calendar.HOUR_OF_DAY);
    //        int minute = calendar.get(Calendar.MINUTE);
    //
    //
    //        new TimePickerDialog(this, this, hora, minute, true).show();// show() para aparecer na tela
    //    }


    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Calendar calendar = Calendar.getInstance();
        // calendar.set(hourOfDay, minute);
        val cal = Calendar.getInstance()

        cal.set(2017, 10, 30, hourOfDay, minute, 0)
        this.setAlarm(cal)


        if (hourOfDay >= 0 && hourOfDay < 12) {
            this.mViewHolder.buttonHora!!.text = hourOfDay.toString() + ":" + minute + " AM"
        } else {
            this.mViewHolder.buttonHora!!.text = hourOfDay.toString() + ":" + minute + " PM"


        }
    }


    fun showAlarm() {
        val cal = Calendar.getInstance()

        //        cal.set( hourOfDay ,minute);
        //        setAlarm(cal);


    }

    //////////////////////////////////////
    private fun setAlarm(targetCal: Calendar) {

        /*info.setText("\n\n***\n"
                + "Alarm is set@ " + targetCal.getTime() + "\n"
                + "***\n");*/

        val intent = Intent(baseContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(baseContext, RQS_1, intent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.timeInMillis, pendingIntent)
    }

    //////////////////////////////////////
    private fun cancelAlarm() {

        textAlarmPrompt.text = ("\n\n***\n"
                + "Alarm Cancelled! \n"
                + "***\n")

        val intent = Intent(baseContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(baseContext, RQS_1, intent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)

    }

    /**
     * ViewHolder
     */
    class ViewHolder {
         var editAtividade: EditText? = null
         var spinner: Spinner? = null
         var editDuracao: EditText? = null
         var checkComplete: CheckBox? = null
         var spinnerPriority: Spinner? = null
         var buttonDate: Button? = null
         var buttonHora: Button? = null
         var buttonSave: Button? = null
         var progressDialog: ProgressDialog? = null
         var buttonSetAlarm: Button? = null
         var buttonCancelar: Button? = null
    }

    companion object {

        private val SIMPLE_DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy")
        // SIMPLE_DATE_FORMAT  variavel privada

        //private final static SimpleDateFormat SIMPLE_HORA_FORMAT = new SimpleDateFormat("HH:mm");
        internal val RQS_1 = 1
    }
}
