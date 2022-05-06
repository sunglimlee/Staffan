package com.example.staffan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //NavController 변수만들고
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // supportFragmentManager에서 FragmentContainer찾아서 그걸 NavHostFragment로 캐스팅하고
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //그 캐스팅한 navHostFragment에 Nav Graph 정보가 있으니깐 findNavController하고
        navController = navHostFragment.findNavController()

        //Regular Actionbar 등록하고
        setSupportActionBar(toolbar_activity_main)
        //그 regular Actionbar를 navController를 넣어주어서 setup을 한다.
        setupActionBarWithNavController(navController) // 여기까지만 하면 backbutton만 동작한다.

    }

    // navController에서 up을 해도 작동하도록 오버라이딩을 해준다.
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}