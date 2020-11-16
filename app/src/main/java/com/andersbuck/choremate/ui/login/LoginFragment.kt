package com.andersbuck.choremate.ui.login

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.andersbuck.choremate.R
import com.andersbuck.choremate.data.User
import com.andersbuck.choremate.data.UserRepository
import com.andersbuck.choremate.shared.Session
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"

    @Inject
    lateinit var userRepository: UserRepository

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val spinner: Spinner = root.findViewById(R.id.spinner_users)
        userRepository.fetchUsers { users -> context?.let { adaptSpinnerUsers(it, spinner, users) } }

        Session.getUsername()?.let {
            Log.i(TAG, "User in session: " + it)
        } ?: run {
            Log.i(TAG, "Username not set!")
        }

        val buttonLogin: Button = root.findViewById(R.id.button_login)
        buttonLogin.setOnClickListener {
            val user = spinner.selectedItem
            if (user is User) {
                Session.setUsername(user.username)
                Log.i(TAG, "User set in session: " + user.username)
            }
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun adaptSpinnerUsers(context: Context, spinner: Spinner, users: List<User>) {
        val userAdapter = ArrayAdapter<User>(context, android.R.layout.simple_spinner_dropdown_item, users)
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = userAdapter
    }

}