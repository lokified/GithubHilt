package com.loki.githubhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.loki.githubhilt.databinding.ActivityMainBinding
import com.loki.githubhilt.ui.MainViewModel
import com.loki.githubhilt.ui.UserAdapter
import com.loki.githubhilt.util.EventObserver
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


        loadUsers(getRecentSearch())

        binding.searchUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {

                if (query.isNullOrEmpty()) {

                    loadUsers(getRecentSearch())
                }
                else {
                    SharedPreferenceManager.savePreviousSearch(this@MainActivity, query)

                    loadUsers(query)
                }

                return true
            }
        })


        viewModel.errorText.observe(this, EventObserver {

            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel
    }

    private fun loadUsers(query: String?) {

        val users = viewModel.loadUsers(query!!)

        users.observe(this@MainActivity) { list ->
            adapter.setRepoList(list!!)
        }
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