package com.andersbuck.choremate.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andersbuck.choremate.R
import com.andersbuck.choremate.data.User
import com.andersbuck.choremate.data.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val button: Button = root.findViewById(R.id.btn_insert)
        button.setOnClickListener {
            insertUser()
        }

        val fetchbutton: Button = root.findViewById(R.id.btn_fetch)
        fetchbutton.setOnClickListener {
            fetchUsers()
        }

        return root
    }

    fun insertUser() {
        val user = User("User1")
        userRepository.insert(user)
        Log.i(TAG, "Inserted user $user")
    }

    fun fetchUsers() {
        userRepository.fetchUsers { users ->
            Log.i(TAG, "First user: " + users[0])
            Log.i(TAG, "Users inserted: " + users.size)
        }
    }
}