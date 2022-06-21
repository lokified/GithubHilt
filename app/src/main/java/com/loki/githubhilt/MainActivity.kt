package com.loki.githubhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.loki.githubhilt.databinding.ActivityMainBinding
import com.loki.githubhilt.ui.MainViewModel
import com.loki.githubhilt.ui.UserAdapter
import com.loki.githubhilt.util.SharedPreferenceManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter =  UserAdapter()

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()

        viewModel.loadUsers(getRecentSearch())

        binding.searchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {

                viewModel.loadUsers(query!!)
                SharedPreferenceManager.savePreviousSearch(this@MainActivity, query)

                return true
            }
        })


        loadUsersList()
        viewModel
    }

    private fun loadUsersList() {

        viewModel.getLiveDataObserver().observe(this, Observer {

            if (it == null) {
                viewModel.loadUsers(getRecentSearch())
            }
            else {
                adapter.setRepoList(it)
            }

        })
    }


    private fun initRecycler() {

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter
    }


    private fun getRecentSearch() : String {

        val shared = applicationContext?.getSharedPreferences("recentSearch", MODE_PRIVATE)
        val pin = shared?.getString("user", "")

        return pin!!
    }
}