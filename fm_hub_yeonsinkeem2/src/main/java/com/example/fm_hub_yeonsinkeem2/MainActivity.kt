package com.example.fm_hub_yeonsinkeem2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.fm_hub_yeonsinkeem2.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener  {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened,
            R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        //override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            //menuInflater.inflate(R.menu.navigation_menu, menu)
            //return super.onCreateOptionsMenu(menu)
        // }

        // 네비게이션 메뉴의 아이템들에게 클릭 속성을 부여.
        binding.nav.setNavigationItemSelectedListener(this)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){ //토글 버튼을 눌렀을 때 drawer 열림
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_title-> {
                val intent = Intent(this, DailyMListActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }
}