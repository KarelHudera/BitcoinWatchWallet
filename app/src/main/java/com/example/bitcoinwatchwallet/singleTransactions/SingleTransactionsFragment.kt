package com.example.bitcoinwatchwallet.singleTransactions

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcoinwatchwallet.ScreenState
import com.example.bitcoinwatchwallet.databinding.FragmentSingleTransactionsBinding
import com.example.bitcoinwatchwallet.databinding.LayoutErrorLoadingBinding
import com.example.bitcoinwatchwallet.wallets.updateVisibility
import java.math.RoundingMode

class SingleTransactionsFragment : Fragment() {
    private var _binding: FragmentSingleTransactionsBinding? = null
    private val binding get() = _binding!!
    private var _mergeBinding: LayoutErrorLoadingBinding? = null
    private val mergeBinding get() = _mergeBinding!!

    private val viewModel by viewModels<SingleTransactionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingleTransactionsBinding.inflate(layoutInflater)
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

        binding.floatingActionButton.setOnClickListener{
            viewModel.deletePublicKey(String())
            findNavController().navigateUp()
        }

        val singleTransactionAdapter = SingleTransactionsAdapter ()

        binding.recyclerSingleTransactions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = singleTransactionAdapter
        }

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            mergeBinding.updateVisibility(state)
            binding.recyclerSingleTransactions.isVisible = state is SingleTransactionsScreenState.Success

            when (state) {
                is SingleTransactionsScreenState.Error -> {
                    state.throwable.printStackTrace()
                    mergeBinding.errorMessageErrorLayout.text = "Error: ${state.throwable.localizedMessage}"
                }
                is SingleTransactionsScreenState.Loading -> {
                    //
                }
                is SingleTransactionsScreenState.Success -> {

                    binding.textViewSingleHeader.text = "Balance"
                    val balance = state.data.final_balance.abs().divide(100000000.toBigDecimal()).setScale(8, RoundingMode.DOWN)
                    val formattedBalance = balance.toPlainString()
                    binding.textViewSingleBalance.text = "$formattedBalance BTC"
                    singleTransactionAdapter.submitList(state.data.txs)
                }
            }
        }
    }
}

fun LayoutErrorLoadingBinding.updateVisibility(state: SingleTransactionsScreenState) {
    refreshButtonErrorLayout.isVisible = state is SingleTransactionsScreenState.Error
    errorMessageErrorLayout.isVisible = state is SingleTransactionsScreenState.Error
    progressBarErrorLayout.isVisible = state is SingleTransactionsScreenState.Loading
}