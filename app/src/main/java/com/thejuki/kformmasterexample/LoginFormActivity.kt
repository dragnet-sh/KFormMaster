package com.thejuki.kformmasterexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormCheckBoxElement
import com.thejuki.kformmaster.model.FormEmailEditTextElement
import com.thejuki.kformmaster.model.FormPasswordEditTextElement
import com.thejuki.kformmasterexample.LoginFormActivity.Tag.Email
import com.thejuki.kformmasterexample.LoginFormActivity.Tag.Password
import kotlinx.android.synthetic.main.activity_login_form.*

/**
 * Login Form Activity
 *
 * Example Login screen using the Form
 *
 * @author **TheJuki** ([GitHub](https://github.com/TheJuki))
 * @version 1.0
 */
class LoginFormActivity : AppCompatActivity() {

    private lateinit var formBuilder: FormBuildHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)

        setupToolBar()

        setupForm()

        buttonLogin.setOnClickListener {
            val loginElementValue = formBuilder.getFormElement<FormEmailEditTextElement>(Email.ordinal).value
            val passwordElementValue = formBuilder.getFormElement<FormPasswordEditTextElement>(Password.ordinal).value
            val checkBoxElementValue = formBuilder.getElementAtIndex(2).value as Boolean?
            Toast.makeText(this@LoginFormActivity, "Do whatever you want with this data\n" +
                    "$loginElementValue\n" +
                    "$passwordElementValue\n" +
                    checkBoxElementValue
                    , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolBar() {

        val actionBar = supportActionBar

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
        }

    }

    private enum class Tag {
        Email,
        Password
    }

    private fun setupForm() {
        formBuilder = FormBuildHelper(this, cacheForm = true)
        formBuilder.attachRecyclerView(this, recyclerView)

        val elements: MutableList<BaseFormElement<*>> = mutableListOf()

        val emailElement = FormEmailEditTextElement(Email.ordinal)
                .setTitle(getString(R.string.email))
        elements.add(emailElement)

        val passwordElement = FormPasswordEditTextElement(Password.ordinal)
                .setTitle(getString(R.string.password))
        elements.add(passwordElement)

        val checkBoxElement = (FormCheckBoxElement<Boolean>()
                .setTitle(getString(R.string.remember))
                .setValue(false)
                as FormCheckBoxElement<Boolean>)
                .setCheckedValue(true)
                .setUnCheckedValue(false)
        elements.add(checkBoxElement)

        formBuilder.addFormElements(elements)

    }
}