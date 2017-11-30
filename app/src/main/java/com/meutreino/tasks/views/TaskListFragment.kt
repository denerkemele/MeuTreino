package com.meutreino.tasks.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.devmasterteam.tasks.R
import com.meutreino.tasks.adapter.TaskListAdapter
import com.meutreino.tasks.business.TaskBusiness
import com.meutreino.tasks.constants.TaskConstants
import com.meutreino.tasks.entities.TaskEntity
import com.meutreino.tasks.listeners.OnGuestListInteractionListener

class TaskListFragment : Fragment(), View.OnClickListener {

    private var mContext: Context? = null
    private val mTaskEntityList: List<TaskEntity>? = null
    private var mTaskListAdapter: TaskListAdapter? = null
    private var mListTask: List<TaskEntity>? = null
    private var mOnGuestListInteractionListener: OnGuestListInteractionListener? = null
    //private TaskManager

    private val mViewHolder = ViewHolder()

    //fazer a definição da camada Business
    private var mTaskBusiness: TaskBusiness? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        // Infla o layout
        // View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);
        val view = inflater!!.inflate(R.layout.fragment_task_list, container, false)
        val context = view.context

        // Incializa as variáveis
        //this.mContext = rootView.getContext();
        this.mContext = view.context

        // Inicializa elementos
        // this.mViewHolder.floatAddTask = (FloatingActionButton) rootView.findViewById(R.id.float_add_task);
        this.mViewHolder.floatAddTask = view.findViewById<View>(R.id.float_add_task) as FloatingActionButton

        // Inicializa eventos
        this.mViewHolder.floatAddTask!!.setOnClickListener(this)

        // 1 - Obter a recyclerview
        // this.mViewHolder.recylerTaskList = (RecyclerView) rootView.findViewById(R.id.recycler_task_list);

        this.mViewHolder.recylerTaskList = view.findViewById<View>(R.id.recycler_task_list) as RecyclerView

        this.mOnGuestListInteractionListener = object : OnGuestListInteractionListener {

            override fun onListClick(id: Int) {
                val bundle = Bundle()
                //chave = TaskConstants.TASK_ID
                //valor = id qe veio por parametro
                bundle.putInt(TaskConstants.BundleConstants.TASK_ID, id)

                //intente e a intenção de fazer algo
                //navegar de uma activity para outra
                val intent = Intent(getContext(), TaskFormTarefaActivity::class.java)

                //juntar o intent com o bundlle
                intent.putExtras(bundle)
                startActivity(intent)

            }

            override fun onDeleteClick(id: Int) {
                mTaskBusiness!!.remove(id)

                Toast.makeText(getContext(), R.string.removido_sucesso, Toast.LENGTH_LONG)

                loadGuests()

            }
        }


        //instanciar a camada Business
        this.mTaskBusiness = TaskBusiness(context)


        //his.mTaskBusiness = new TaskBusiness(this);
        /* TaskEntity taskEntity = new TaskEntity();
        taskEntity.setNomecircuito("CORRER".toString());

        this.mTaskBusiness.insertSpinner(taskEntity);
        // this.mTaskBusiness.insertSpinner("CORRER");
        */

        // 3 - Definir um layout
        //this.mViewHolder.recylerTaskList.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mViewHolder.recylerTaskList!!.setLayoutManager(LinearLayoutManager(context))
        return view
    }

    override fun onResume() {
        super.onResume()

        // Atualiza listagem
        this.loadGuests()


        // Carrega quantidade de treinos

        //this.loadDashboard();
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.float_add_task) {
            startActivity(Intent(this.mContext, TaskFormActivity::class.java))

        }
    }

    /**
     * Atualiza listagem
     */
    private fun loadGuests() {


        //List<TaskEntity> taskEntityList = this.mTaskBusiness.getTreinos();

        // 2 - Definir adapter passando listagem de itens
        //o Adapter faz a junção dos dados com a RecylerView
        //Definir um adapter
        //  TaskListAdapter taskListAdapter = new TaskListAdapter(taskEntityList, this.mOnGuestListInteractionListener);
        //setar o o adapter para recycler View

        this.mListTask = this.mTaskBusiness!!.treinos
        this.mTaskListAdapter = TaskListAdapter(this.mListTask!!, this!!.mOnGuestListInteractionListener!!)
        this.mViewHolder.recylerTaskList!!.setAdapter(this.mTaskListAdapter)


        /// this.mViewHolder.recylerTaskList.setAdapter(taskListAdapter);

        //notifica que a listagem foi alterada
        this.mTaskListAdapter!!.notifyDataSetChanged()


    }


/**
 * Carrega quantidade de convidados
 * z
 * private void loadDashboard() {
 * GuestCount guestCount = this.mGuestBusiness.loadGuestsCount();
 *
 *
 * // Atribui dados aos elementos
 * this.mViewHolder.textAllGuests.setText(String.valueOf(guestCount.getTotalGuests()));
 * this.mViewHolder.textAllPresent.setText(String.valueOf(guestCount.getTotalPresent()));
 * this.mViewHolder.textAllAbsent.setText(String.valueOf(guestCount.getTotalAbsent()));
 * }
 *
 *
 */ /**
 * ViewHolder
*/
private class ViewHolder {
var floatAddTask:FloatingActionButton? = null
var recylerTaskList:RecyclerView? = null
}

companion object {

fun newInstance(): TaskListFragment {
val fragment = TaskListFragment()
return fragment
}
}
}
