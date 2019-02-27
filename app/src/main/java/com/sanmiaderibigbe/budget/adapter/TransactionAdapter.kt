package com.sanmiaderibigbe.budget.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sanmiaderibigbe.budget.data.model.Transaction
import com.sanmiaderibigbe.budget.databinding.TransactionListItemBinding

class TransactionAdapter(context: Context) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>()  {
    private var transactionList: List<Transaction>? = null



    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val binding = TransactionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    fun setTransactionList(songs: List<Transaction>?) {
        transactionList = songs
        notifyDataSetChanged()
    }

    fun clear() {
        transactionList = null
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int =  transactionList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val transaction: Transaction? = transactionList?.get(p1)
        holder.transactionListBinding.transaction = transaction
    }

    inner class ViewHolder(val transactionListBinding: TransactionListItemBinding) :
        RecyclerView.ViewHolder(transactionListBinding.root)
}