package com.example.bitcoinwatchwallet.wallets

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
import com.example.bitcoinwatchwallet.R
import com.example.bitcoinwatchwallet.databinding.LayoutErrorLoadingBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.bitcoinwatchwallet.ScreenState
import com.example.bitcoinwatchwallet.databinding.FragmentWalletsBinding
import com.example.bitcoinwatchwallet.extensions.safeNavigate
import java.math.RoundingMode

class WalletsFragment : Fragment() {
    private var _binding: FragmentWalletsBinding? = null
    private val binding get() = _binding!!
    private var _mergeBinding: LayoutErrorLoadingBinding? = null
    private val mergeBinding get() = _mergeBinding!!

    private val viewModel by viewModels<WalletsFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWalletsBinding.inflate(layoutInflater)
        _mergeBinding = LayoutErrorLoadingBinding.bind(mergeBinding.root)
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

        val walletAdapter = WalletAdapter {
            findNavController().safeNavigate(
                WalletsFragmentDirections.actionToSingleTransactionsFragment(it.address)
            )
        }

        binding.recyclerAddresses.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = walletAdapter
        }

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            mergeBinding.updateVisibility(state)
            binding.recyclerAddresses.isVisible = state is ScreenState.Success
            val fab: FloatingActionButton = binding.floatingActionButton

            fab.setOnClickListener {
                // Navigate to the bottom dialog using the navigation graph
                findNavController().navigate(R.id.action_to_addressImputBottomSheetDialog)
            }

            when (state) {
                is ScreenState.Error -> {
                    state.throwable.printStackTrace()
                    mergeBinding.errorMessageErrorLayout.text = "Error: timeout"
                }
                is ScreenState.Loading -> {
                    //
                }
                is ScreenState.Success -> {
         //           binding.addresId.text = state.data.first().address
//                    binding.filterExhangesFragment.setOnClickListener {
//                        findNavController().safeNavigate(
//                            ExchangesListDirections.actionToFilter()
//                        )
//                    }
                    walletAdapter.submitList(state.data.addresses)
                    binding.textViewLilHeader.text = "Balance:"

                    val balance = state.data.wallet.finalBalance.abs().divide(100000000.toBigDecimal()).setScale(8, RoundingMode.DOWN)
                    val formattedBalance = balance.toPlainString()
                    binding.textViewBalance.text = "$formattedBalance BTC"

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