package com.example.staffan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.staffan.databinding.FragmentLoginBinding
import com.example.staffan.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    //navArgs
    private val args : WelcomeFragmentArgs by navArgs()
    private lateinit var binding : FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewUsernameFragmentWelcome.text = args.username
        binding.textViewPasswordFragmentWelcome.text = args.password

        binding.buttonOkFragmentWelcome.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
            findNavController().navigate(action)
        }

    }
}