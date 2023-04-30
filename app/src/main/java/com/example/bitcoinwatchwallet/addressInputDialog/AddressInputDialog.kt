package com.example.bitcoinwatchwallet.addressInputDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.bitcoinwatchwallet.databinding.DialogAddressInputBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddressInputDialog : BottomSheetDialogFragment() {

    private var _binding: DialogAddressInputBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddressInputViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddressInputBinding.inflate(layoutInflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener{
            viewModel.insertWallet(binding.editTextFilterAddr.text.toString())
            dismiss()
        }
    }

    companion object{
        fun newInstance(): AddressInputDialog{
            return AddressInputDialog()
        }
    }
}