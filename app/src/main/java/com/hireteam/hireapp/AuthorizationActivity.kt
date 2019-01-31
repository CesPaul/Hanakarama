package com.hireteam.hireapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.Intent



class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
    }

    fun signup(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)

        startActivity(intent);
    }

    // Авторизирует пользователя в системе
    fun authorizeUser(view: View){

        // Забираем данные
        val login = findViewById<EditText>(R.id.loginEditText) as EditText
        val password = findViewById<EditText>(R.id.passwordEditText) as EditText

        // Поля должны быть непустыми
        if (login.text.isBlank() == false && password.text.isBlank() == false){
            // Устанавливаем поля
            val loginTextView = findViewById<TextView>(R.id.loginTextView) as TextView
            loginTextView.setText(login.text)

            val passwordTextView = findViewById<TextView>(R.id.passwordTextView) as TextView
            passwordTextView.setText(password.text)
        }
        // Иначе предупреждаем юзера
        else{
            val toast = Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT)
            toast.show()
        }

        // Это событие будет инициировать вызов другого activity в случае успешной авторизации
    }
}
