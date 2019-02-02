package com.hireteam.hireapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_create_item.*

class CreateItemActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val wikiApiServe by lazy {
        WikiApiService.create("http://192.168.1.58:8080/login/")
    }

    private fun beginAuthorization(title: String, description: String, price: Int, dateToEnd: String) {
        disposable = wikiApiServe.createItem(title,description, price, dateToEnd)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // Пустой ответ - успех
                { result -> Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
                },
                { error -> Toast.makeText(this, "Что-то пошло не так...", Toast.LENGTH_SHORT).show() }
            )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_item)
    }

    fun CreateItemAA(view: View){
        beginAuthorization(titleEditText.text.toString(), descriptionEditText.text.toString(), priceEditText.text.toString().toInt(), dateToEndEditText.toString())
    }
}
