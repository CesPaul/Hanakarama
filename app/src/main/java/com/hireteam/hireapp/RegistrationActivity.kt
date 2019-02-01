package com.hireteam.hireapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val wikiApiServe by lazy {
        WikiApiService.create("http://192.168.1.58:8080/profile/")
    }

    /*
    private fun beginRegistration() {
        disposable = wikiApiServe.addUser("login", "password", "name", "phone")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> resultTextView.text = "${result.message} result found" },
                { error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show() }
            )
    }
    */
    private fun beginRegistration(login: String, password: String, name: String, phone: String) {
        disposable = wikiApiServe.addUser(login,password, name, phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // Пустой ответ - успех
                { result -> if(result.message.isNullOrBlank()){
                    Toast.makeText(this, "Добро пожаловать!", Toast.LENGTH_SHORT).show()

                    val sidePanelIntent = Intent(this, SidepanelActivity::class.java)

                    sidePanelIntent.putExtra("currentLogin", regLoginEditText.text.toString())
                    sidePanelIntent.putExtra("currentName", regNameEditText.text.toString())
                    sidePanelIntent.putExtra("currentPhone", regNumberPhone.text.toString())

                    startActivity(sidePanelIntent)
                }},
                { error -> if(error.message.toString() == "User already exist!"){
                    Toast.makeText(this, error.message.toString(), Toast.LENGTH_SHORT).show()
                }}
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registrationButton.setOnClickListener {

            // Поля должны быть непустыми
            if (regLoginEditText.text.isBlank() == false
                && regPasswordEditText.text.isBlank() == false
                && regNameEditText.text.isBlank() == false
                && regNumberPhone.text.isBlank() == false
            ) {

                beginRegistration(
                    regLoginEditText.text.toString(),
                    regPasswordEditText.text.toString(),
                    regNameEditText.text.toString(),
                    regNumberPhone.text.toString()
                )
            } else {
                Toast.makeText(this, "Пожалуйста, введите все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*
    fun launch(view: View) {
        val intent = Intent(this, SidepanelActivity::class.java)

        startActivity(intent);
    }
    */
}
