package com.example.jetpackdatastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.jetpackdatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        applicationContext.readString(DatastoreConstants.USER_NAME_KEY).asLiveData().observe(this) {
            binding.textView.text = it
        }

        binding.saveButton.setOnClickListener {
            val data = binding.editText.text.toString()
            lifecycleScope.launch {
                applicationContext.writeString(DatastoreConstants.USER_NAME_KEY, data)
            }

        }

    }
}