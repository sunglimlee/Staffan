package com.example.staffan

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_login_fragment_home.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            //이 액션에 따라 네비게이트를 해라. 만들어지 NavDirections 객체를 넘겨준다.
            findNavController().navigate(action) // backStack 같은걸 모두 Nav Control이 다 관리한다.
        }
    }
}