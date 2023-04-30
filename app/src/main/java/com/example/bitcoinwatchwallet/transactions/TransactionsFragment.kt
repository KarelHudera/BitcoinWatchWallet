package com.example.bitcoinwatchwallet.transactions

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcoinwatchwallet.ScreenState
import com.example.bitcoinwatchwallet.databinding.FragmentTransactionsBinding
import com.example.bitcoinwatchwallet.databinding.LayoutErrorLoadingBinding
import com.example.bitcoinwatchwallet.extensions.safeNavigate
import com.example.bitcoinwatchwallet.wallets.WalletsFragmentDirections
import com.example.bitcoinwatchwallet.wallets.WalletsFragmentViewModel
import com.example.bitcoinwatchwallet.wallets.updateVisibility
import java.math.RoundingMode

class Transactions : Fragment() {
    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!
    private var _mergeBinding: LayoutErrorLoadingBinding? = null
    private val mergeBinding get() = _mergeBinding!!

    private val viewModel by viewModels<WalletsFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionsBinding.inflate(layoutInflater)
        _mergeBinding = LayoutErrorLoadingBinding.bind(binding.root)
        return _binding?.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mergeBinding.refreshButtonErrorLayout.setOnClickListener {
            viewModel.refreshFragment()
        }

        val transactionAdapter = TransactionAdapter ()

        binding.recyclerTransactions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            mergeBinding.updateVisibility(state)
            binding.recyclerTransactions.isVisible = state is ScreenState.Success

            when (state) {
                is ScreenState.Error -> {
                    mergeBinding.errorMessageErrorLayout.text = "Error: timeout"
                }
                is ScreenState.Loading -> {
                    //
                }
                is ScreenState.Success -> {

                    binding.textViewHeader.text = "Balance"
                    val balance = state.data.wallet.finalBalance.abs().divide(100000000.toBigDecimal()).setScale(8, RoundingMode.DOWN)
                    val formattedBalance = balance.toPlainString()
                    binding.textViewBalance.text = "$formattedBalance BTC"
                    transactionAdapter.submitList(state.data.txs)

                }
            }
        }
    }
}

fun LayoutErrorLoadingBinding.updateVisibility(state: ScreenState) {
    refreshButtonErrorLayout.isVisible = state is ScreenState.Error
    errorMessageErrorLayout.isVisible = state is ScreenState.Error
    progressBarErrorLayout.isVisible = state is ScreenState.Loading

}