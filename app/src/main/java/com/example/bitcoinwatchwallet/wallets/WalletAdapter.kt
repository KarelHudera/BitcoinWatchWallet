package com.example.bitcoinwatchwallet.wallets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcoinwatchwallet.R
import com.example.bitcoinwatchwallet.apiMultiAddr.Addresse
import com.example.bitcoinwatchwallet.databinding.ItemWalletBinding
import java.math.RoundingMode

class WalletAdapter(private val clickListener: OnWalletClicked) :
    ListAdapter<Addresse, WalletAdapter.ItemViewHolder>(AddressesDiffCallBack()) {

    inner class ItemViewHolder(private val binding: ItemWalletBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Addresse) {
            with(binding) {
                textViewAddress.text = item.address
                val balance = item.finalBalance.abs().divide(100000000.toBigDecimal()).setScale(8, RoundingMode.DOWN)
                val formattedBalance = balance.toPlainString()
                textViewBalance.text = formattedBalance
                root.setOnClickListener { clickListener.onWalletClicked(item) }

                imageViewFavorite.setImageResource(
                    if (item.isFavorite) android.R.drawable.btn_star_big_on  else android.R.drawable.btn_star_big_off
                )

                imageViewFavorite.setOnClickListener {
                    // Invert the isFavorite flag
                    item.isFavorite = !item.isFavorite

                    // Update the favorite image
                    imageViewFavorite.setImageResource(
                        if (item.isFavorite) android.R.drawable.btn_star_big_on  else android.R.drawable.btn_star_big_off
                    )
                }
            }
        }
    }

    private class AddressesDiffCallBack : DiffUtil.ItemCallback<Addresse>() {
        override fun areItemsTheSame(
            oldAddressesData: Addresse,
            newAddressesData: Addresse
        ): Boolean =
            oldAddressesData == newAddressesData

        override fun areContentsTheSame(
            oldAddressesData: Addresse,
            newAddressesData: Addresse
        ): Boolean =
            oldAddressesData == newAddressesData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemWalletBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun interface OnWalletClicked {
        fun onWalletClicked(addressees: Addresse)
    }
}