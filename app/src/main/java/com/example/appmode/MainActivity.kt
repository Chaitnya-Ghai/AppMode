package com.example.appmode

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appmode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPrefrence:SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        sharedPrefrence=getSharedPreferences(resources.getString(R.string.app_name), MODE_PRIVATE)
        editor=sharedPrefrence.edit()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.dialogBtn.setOnClickListener{
            AlertDialog.Builder(this).apply {
                setTitle("Select App-Mode")
                setPositiveButton("Dark"){
                    _,_->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    editor.putBoolean("darkMode",true)
                    editor.commit()
                    editor.apply()
                }
                setNegativeButton("light"){_,_->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    editor.putBoolean("darkMode",false)
                    editor.commit()
                    editor.apply()}
                setCancelable(false)
                show()
            }
        }
    }
}