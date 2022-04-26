package br.digitalhouse.loginmvvmdh.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.digitalhouse.loginmvvmdh.Interfaces.LoginResultInterface
import br.digitalhouse.loginmvvmdh.R
import br.digitalhouse.loginmvvmdh.ViewModel.LoginViewModel

class MainActivity : AppCompatActivity(), LoginResultInterface {

    //cria o objeto viewModel
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //configura o view model
        loginViewModel = ViewModelProvider(this).get(LoginViewModel ::class.java)
        //configura o ouvidor de eventos
        setupListeners()
        //configura o observador de atualização de dados
        setupObservers()
    }

    private fun setupListeners() {
        var btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            var email = findViewById<EditText>(R.id.txtEmail).text.toString()
            var password = findViewById<EditText>(R.id.txtPassword).text.toString()
            //chama a função para atualizar o valor do view model
            loginViewModel.onUserRequestLogin(email,password)
        }
    }

    private fun setupObservers(){
        //observa a alteração no valor da variavel e caso haja alteração chama a função de mostrar mensagem
        loginViewModel.onUserRequestLoginLiveData.observe(this,{
            showMessage(it)
        }
        )
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}