package com.example.staffan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.staffan.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding : FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonConfirmFragmentLogin.setOnClickListener {
            val username = binding.editTextUsernameFragmentLogin.text.toString()
            val password = binding.editTextPasswordFragmentLogin.text.toString()
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username, password)
            findNavController().navigate(action)
        }
    }
}