package com.sanmiaderibigbe.budget.ui.transactions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.EditText
import com.sanmiaderibigbe.budget.R
import com.sanmiaderibigbe.budget.adapter.TransactionAdapter

import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {

    private lateinit var viewModel: TransactionViewModel
    private var  userId : Long = Long.MIN_VALUE
    private lateinit var  adapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this).get(TransactionViewModel::class.java)
        adapter = initRecyclerView()
        userId = intent.getLongExtra(USER_ID_EXTRA, 0)
        viewModel.getTransaction(userId).observe(this, Observer { adapter.setTransactionList(it) })

        Log.d("transaction", userId.toString())

        fab.setOnClickListener { view ->
            initAddUserDialog()
        }

    }

    private fun initAddUserDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val createUserView = this.layoutInflater.inflate(R.layout.dialog_add_new_transaction, null)


        alertDialogBuilder.setPositiveButton(getString(R.string.create_user)) { dialogInterface, i ->
            val amount = createUserView.findViewById<EditText>(R.id.amount_edit_txt).text.toString()
            val transactionState = createUserView.findViewById<EditText>(R.id.transaction_state_edit_txt).text.toString()
            viewModel.createTransaction(amount, transactionState, userId)
            dialogInterface?.cancel()


        }.setNegativeButton(getString(R.string.cancel)) { dialogInterface: DialogInterface?, i: Int ->
            dialogInterface?.cancel()
        }.setView(createUserView)

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun initRecyclerView(): TransactionAdapter {
        val adapter = TransactionAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        return adapter
    }
    companion object {
        const val USER_ID_EXTRA = "com.sanmiaderibigbe.budget.user_extra_id"
    }

}
