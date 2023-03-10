package com.test.mvctest.Controller


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.test.mvctest.Model.User
import com.test.mvctest.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

//
//        username.afterTextChanged {
//            loginViewModel.loginDataChanged(
//                username.text.toString(),
//                password.text.toString()
//            )
//        }
//
//        password.apply {
//            afterTextChanged {
//                loginViewModel.loginDatadsChanged(
//                    username.text.toString(),
//                    password.text.toString()
//                )
//            }
//
//            setOnEditorActionListener { _, actionId, _ ->
//                when (actionId) {
//                    EditorInfo.IME_ACTION_DONE ->
//                        loginViewModel.login(
//                            username.text.toString(),
//                            password.text.toString()
//                        )
//                }
//                false
//            }



            login.setOnClickListener {
//                loading.visibility = View.VISIBLE
                OnLogin(
                    username?.text.toString(),
                    password?.text.toString().trim() // 공백 제거
                )

            }
        }

    fun OnLogin(email: String?, password: String?) {
        val user = User(email,password)
        val loginCode = user.isValid()

        when (loginCode) {
            0 -> {
                onFail("id를 입력해 주세요")
            }
            1 -> {
                onFail("유효하지 않는 이메일입니다.")
            }
            2 -> {
                onFail("비밀번호를 입력해 주세요")
            }
            3 -> {
                onFail("6자리 이상 비밀번호를 입력해 주세요")
            }
            else -> {
                onSuccess("로그인 성공",email)
            }
        }
    }
    // email 넘기는 부분이 비효율적인가?
    fun onSuccess(message: String?,email: String?) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("username",email) //데이터 넣어서
        startActivity(intent)
    }

    fun onFail(message: String?) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }

}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}