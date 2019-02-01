package com.hireteam.hireapp

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import android.os.Parcelable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.reflect.KClass


class AuthorizationActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val wikiApiServe by lazy {
        WikiApiService.create("http://192.168.1.58:8080/login/")
    }

    private fun beginAuthorization(login: String, password: String) {
        disposable = wikiApiServe.login(login,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // Пустой ответ - успех
                { result -> if (result.login.isNotBlank()){
                    Toast.makeText(this, "Добро пожаловать!", Toast.LENGTH_SHORT).show()

                    val sidePanelIntent = Intent(this, SidepanelActivity::class.java)

                    sidePanelIntent.putExtra("currentLogin", result.login.toString())
                    sidePanelIntent.putExtra("currentName", result.name.toString())
                    sidePanelIntent.putExtra("currentPhone", result.phone.toString())

                    startActivity(sidePanelIntent)
                }
                },
                { error -> Toast.makeText(this, "Что-то пошло не так...", Toast.LENGTH_SHORT).show() }
            )
    }

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


            beginAuthorization(login.text.toString(), password.text.toString())

        }
        // Иначе предупреждаем юзера
        else{
            val toast = Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT)
            toast.show()
        }

        // Это событие будет инициировать вызов другого activity в случае успешной авторизации
    }
}
