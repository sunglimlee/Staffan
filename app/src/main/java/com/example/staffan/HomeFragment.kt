package com.example.staffan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.staffan.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLoginFragmentHome.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            //이 액션에 따라 네비게이트를 해라. 만들어지 NavDirections 객체를 넘겨준다.
            findNavController().navigate(action) // backStack 같은걸 모두 Nav Control이 다 관리한다.
        }
    }
}