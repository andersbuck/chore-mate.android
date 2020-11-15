package com.andersbuck.choremate.ui.login

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.andersbuck.choremate.R
import com.andersbuck.choremate.data.User
import com.andersbuck.choremate.data.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

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