package com.sanmiaderibigbe.budget.ui.users

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.Toast
import com.sanmiaderibigbe.budget.R
import com.sanmiaderibigbe.budget.adapter.UserAdapter
import com.sanmiaderibigbe.budget.data.model.User
import com.sanmiaderibigbe.budget.ui.transactions.TransactionActivity

import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity(), UserAdapter.OnUserItemClickHandler {

    override fun onClick(user: User) {
       Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, TransactionActivity::class.java)
        intent.putExtra(TransactionActivity.USER_ID_EXTRA, user.id)
        startActivity(intent)
    }

    private lateinit var viewModel: UsersViewModel
    private lateinit var  adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        adapter = initRecyclerView()
        //viewModel.getUser().observe(this, Observer { adapter.setUserList(it) })
        viewModel.getUserWithTransactionInformation().observe(this, Observer { adapter.setUserList(it) })
        fab_add_new_user.setOnClickListener { view ->
          initAddUserDialog()
        }
    }

    private fun initAddUserDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val createUserView = this.layoutInflater.inflate(R.layout.dialog_add_new_user, null)


        alertDialogBuilder.setPositiveButton(getString(R.string.create_user)) { dialogInterface, i ->
            val name = createUserView.findViewById<EditText>(R.id.name_edit_txt).text.toString()
            val amount = createUserView.findViewById<EditText>(R.id.amount_edit_txt).text.toString()
            viewModel.createUser(name, amount)
            dialogInterface?.cancel()


        }.setNegativeButton(getString(R.string.cancel)) { dialogInterface: DialogInterface?, i: Int ->
            dialogInterface?.cancel()
        }.setView(createUserView)

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    private fun initRecyclerView(): UserAdapter {
        val adapter = UserAdapter(this, this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        return adapter
    }
}
