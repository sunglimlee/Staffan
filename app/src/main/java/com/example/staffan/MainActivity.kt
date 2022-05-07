package com.example.staffan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.staffan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    //NavController 변수만들고
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // supportFragmentManager에서 FragmentContainer찾아서 그걸 NavHostFragment로 캐스팅하고
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //그 캐스팅한 navHostFragment에 Nav Graph 정보가 있으니깐 findNavController하고
        navController = navHostFragment.findNavController()



        //Regular Actionbar 등록하고
        setSupportActionBar(binding.toolbarActivityMain)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.searchFragment), drawerLayout
              //setOf(R.id.homeFragment, R.id.searchFragment)
        )
        //그 regular Actionbar를 navController를 넣어주어서 setup을 한다.
        //색깔때문에 마치 없는것 처럼 보여서 안되었구나.. ㅋ.... 많이 배웠다.
        setupActionBarWithNavController(navController, appBarConfiguration) // 여기까지만 하면 backbutton만 동작한다.
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view).setupWithNavController(
            navController
        )
        //NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        //binding.bottomNavigationView.setupWithNavController(navController)
        binding.navView.setupWithNavController(navController)
    }
/*
        binding.bottomNavigationView.setOnItemSelectedListener {
            // Gradle 2.3.5 로 바꾸고 이상없이 작동한다. Error
            // https://stackoverflow.com/questions/70703505/navigationui-not-working-correctly-with-bottom-navigation-view-implementation
            //https://medium.com/androiddevelopers/multiple-back-stacks-b714d974f134
            //logic like navController.popBackStack(R.id.homeFragment, false) and navController.navigate(item.getItemId(), null, options)
            true
        }
    }
*/

    // navController에서 up을 해도 작동하도록 오버라이딩을 해준다.
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return super.onSupportNavigateUp() || navController.navigateUp(appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    //여기가 햄버거 버턴 문제가 생기는 부분이네..
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Global Action을 통해서 TermsAndCondition Fragment를 보여주기 위한 방법
        return if (item.itemId == R.id.termsAndConditions) {
            val action = NavGraphDirections.actionGlobalTermsFragment()
            navController.navigate(action)
            true
        } else {

            // 메뉴의 아이템이 선택되면 해당 메뉴에 대한 extension function이 이미 있는데 그렇게 확장이 되어 있는 상태라는거지.
            // 위에 말을 적어놓고 이해를 못했네.. MenuItem을 확장한 Extension function onNaveDestinationSelected가 이미
            // 만들어져 있고 (androidx.naviation.ui) MenuItem이 들어오면 그 확장함수를 쓸 수 있다는 거다.
            // 그렇게 MenuItem을 확장해 나가는거다. 단
            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
            // 위에 문장이 true false를 리턴하는 건데.. 바로 밑에다가 무조건 true를 넣어놓으니깐 문제가 생기는거지...
            // 이거때메 햄버거 아이콘 문제 무지하게 생겼잖아... 또 2시간 그냥갔다. 지금 저녁 7시.. 오마이갓...
        }
    }
}

