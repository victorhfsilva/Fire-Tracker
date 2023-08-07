package com.victor.firetracker_app.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.victor.firetracker_app.data.repository.SharedPreferencesManager
import com.victor.firetracker_app.databinding.ConfigFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConfigFragment: Fragment() {

    private var _binding: ConfigFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPreferencesManager : SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ConfigFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setView()
    }

    private fun setView() {
        sharedPreferencesManager.getIsNotificationsAllowed()
            ?.let { binding.swNotifications.isChecked = it }
        sharedPreferencesManager.getServer()?.let { binding.tvServer.text = it }
        sharedPreferencesManager.getIsContactAlertsAllowed()
            ?.let { binding.swContact.isChecked = it }
        sharedPreferencesManager.getContact()?.let { binding.tvContact.text = it }
    }

    fun setListeners(){

        binding.swNotifications.setOnCheckedChangeListener { compoundButton, isChecked ->
            sharedPreferencesManager.allowNotifications(isChecked)
        }

        binding.swContact.setOnCheckedChangeListener{ compoundButton, isChecked ->
            sharedPreferencesManager.allowContactAlerts(isChecked)
        }
        
        binding.btServerEdit.setOnClickListener {
            val alert = AlertDialog.Builder(this.context)

            alert.setMessage("Insira o endereço do servidor:")

            val input = EditText(this.context)
            alert.setView(input)

            alert.setPositiveButton("Ok") { dialog, whichButton ->
                val server: String = input.text.toString()

                sharedPreferencesManager.setServer(server)
                binding.tvServer.text = server
                Toast.makeText(this.context, "Servidor editado com sucesso.", Toast.LENGTH_LONG).show()
            }

            alert.setNegativeButton(
                "Cancelar"
            ) { dialog, whichButton ->

            }
            alert.show()
            true
        }

        binding.btContactEdit.setOnClickListener {
            val alert = AlertDialog.Builder(this.context)

            alert.setMessage("Insira o contato:")

            val input = EditText(this.context)
            input.inputType = InputType.TYPE_CLASS_PHONE

            alert.setView(input)

            alert.setPositiveButton("Ok") { dialog, whichButton ->
                val contact: String = input.text.toString()
                sharedPreferencesManager.setContact(contact)
                binding.tvContact.text = contact
                Toast.makeText(this.context, "Contato editado com sucesso.", Toast.LENGTH_LONG).show()
            }

            alert.setNegativeButton(
                "Cancelar"
            ) { dialog, whichButton ->

            }
            alert.show()
            true
        }
    }
}