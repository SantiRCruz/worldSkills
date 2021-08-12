package com.santiago.worldskillscomida.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.databinding.DialogRegistrarBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.ui.registrar.RegistroViewModel

class DialogRegistro:DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.dialog_registrar,container,false)

        val binding = DialogRegistrarBinding.bind(rootView)

        binding.txtPoliticas.text = Constants.POLITICAS_PRIVACIDAD
        binding.ButtonAceptar.setOnClickListener {
            Constants.POLITICAS_ELECCION = 1
            dismiss()
        }
        binding.ButtonDenegar.setOnClickListener {
            Constants.POLITICAS_ELECCION = 2
            dismiss()
        }

        return binding.root
    }

}