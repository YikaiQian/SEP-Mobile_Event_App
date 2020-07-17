package com.example.sepapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.example.sepapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener{
            loginHandler()
        }
    }

    private fun loginHandler(){
        if(isLoginCheckPassed(username_input_text.text)){
            username_input_layout.error = null
            navToMainActivity()
        } else {
            username_input_layout.error = getString(R.string.error_login_username_text)
        }
    }

    private fun navToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun isLoginCheckPassed(username: Editable?): Boolean {
        return username!=null && username.isNotEmpty()
    }


}