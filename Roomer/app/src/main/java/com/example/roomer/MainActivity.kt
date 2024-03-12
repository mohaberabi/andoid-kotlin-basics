package com.example.roomer

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomer.databinding.ActivityMainBinding
import com.example.roomer.databinding.DialogUpdateBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val dao = (application as UsersApp).db.usersDao()
        binding?.btnAdd?.setOnClickListener {
            addUser(dao)
        }

        lifecycleScope.launch {
            dao.getAllUsers().collect {
                setupRecyclerView(it, userDao = dao)
            }

        }


    }


    private fun setupRecyclerView(users: List<User>, userDao: UsersDAO) {
        val usersAdapter = UsersAdapter(
            users = users,
            updater = {
                updateDialog(it, userDao)
            },
            deleter = {

            },
        )
        binding?.rvItemsList?.visibility = View.VISIBLE
        binding?.rvItemsList?.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        binding?.rvItemsList?.adapter = usersAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun addUser(userDao: UsersDAO) {

        val name = binding?.etName?.text.toString()
        val email = binding?.etEmailId?.text.toString()

        val user = User(name = name, email = email)
        lifecycleScope.launch {
            userDao.addUser(user = user)

            Toast.makeText(this@MainActivity, "User Added", Toast.LENGTH_SHORT).show()
        }

    }


    private fun updateDialog(
        id: Int,
        userDao: UsersDAO,
    ) {
        val updateDialog =
            Dialog(this, R.style.Theme_Dialog)

        updateDialog.setCancelable(false)
        val binding = DialogUpdateBinding.inflate(layoutInflater)
        updateDialog.setContentView(binding.root)
        lifecycleScope.launch {

            val user = userDao.getUserById(id)

            binding.etUpdateEmailId.setText(user.email)

            binding.etUpdateName.setText(user.name)


        }
        binding.tvUpdate.setOnClickListener {


            lifecycleScope.launch {
                userDao.updateUser(
                    user = User(
                        id = id,
                        name = binding.etUpdateName.text.toString(),
                        email = binding.etUpdateEmailId.text.toString()
                    )
                )
            }

            updateDialog.dismiss()
        }


        binding.tvCancel.setOnClickListener {
            updateDialog.dismiss()
        }
        updateDialog.show()


    }
}