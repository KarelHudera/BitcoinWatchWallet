package com.example.bitcoinwatchwallet.singleTransactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcoinwatchwallet.apiSingleAddr.Tx
import com.example.bitcoinwatchwallet.databinding.ItemTransactionBinding
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class SingleTransactionsAdapter() :
    ListAdapter<Tx, SingleTransactionsAdapter.ItemViewHolder>(AddressesDiffCallBack()) {

    inner class ItemViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Tx) {
            with(binding) {
                val balance = item.result.abs().divide(100000000.toBigDecimal()).setScale(8, RoundingMode.DOWN)
                val formattedBalance = balance.toPlainString()
                textViewTx.text = formattedBalance

                if (item.result< BigDecimal.ZERO) {
                    imageViewIcon.setImageResource(android.R.drawable.stat_sys_upload)
                    textViewTx.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_red_dark))
                } else {
                    imageViewIcon.setImageResource(android.R.drawable.stat_sys_download)
                    textViewTx.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.holo_green_dark))
                }

                val timestamp = item.time.toLong()
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
                sdf.timeZone = TimeZone.getTimeZone("UTC")
                val formattedDateTime = sdf.format(Date(timestamp * 1000))
                textViewDate.text = formattedDateTime
            }
        }
    }

    private class AddressesDiffCallBack : DiffUtil.ItemCallback<Tx>() {
        override fun areItemsTheSame(
            oldAddressesData: Tx,
            newAddressesData: Tx
        ): Boolean =
            oldAddressesData == newAddressesData

        override fun areContentsTheSame(
            oldAddressesData: Tx,
            newAddressesData: Tx
        ): Boolean =
            oldAddressesData == newAddressesData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}