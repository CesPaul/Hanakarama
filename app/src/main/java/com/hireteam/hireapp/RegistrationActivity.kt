package com.hireteam.hireapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    fun registerUser(view: View){

        // Забираем данные
        val login = findViewById<EditText>(R.id.regLoginEditText) as EditText
        val password = findViewById<EditText>(R.id.regPasswordEditText) as EditText
        val name = findViewById<EditText>(R.id.regNameEditText) as EditText
        val num = findViewById<EditText>(R.id.regNumberPhone) as EditText

        // Поля должны быть непустыми
        if (login.text.isBlank() == false && password.text.isBlank() == false){
            // Устанавливаем поля
            val loginTextView = findViewById<TextView>(R.id.regLoginTextView) as TextView
            loginTextView.setText(login.text)

            val passwordTextView = findViewById<TextView>(R.id.regPasswordTextView) as TextView
            passwordTextView.setText(password.text)

            val nameTextView = findViewById<TextView>(R.id.nameTextView) as TextView
            nameTextView.setText(name.text)

            val numTextView = findViewById<TextView>(R.id.numTextView) as TextView
            numTextView.setText(num.text)
        }
        // Иначе предупреждаем юзера
        else{
            val toast = Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT)
            toast.show()
        }

        // Это событие будет инициировать вызов другого activity в случае успешной регистрации
    }
}
