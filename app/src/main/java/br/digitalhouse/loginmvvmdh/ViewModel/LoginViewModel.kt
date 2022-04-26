package br.digitalhouse.loginmvvmdh.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.digitalhouse.loginmvvmdh.model.User
import java.net.PasswordAuthentication

class LoginViewModel () : ViewModel(){
    private val user = User("","")

    //cria um mutable Live Data de String
    private val onUserRequestToLogin = MutableLiveData<String>()
    //joga o valor atualizado no liveData para a val
    val onUserRequestLoginLiveData : LiveData<String> = onUserRequestToLogin


    fun onUserRequestLogin(email : String, password : String){
        user.email = email
        user.password = password
        //atualiza o valor do livedata
        onUserRequestToLogin.value = generateLoginValidationMessage()
    }

    // valida se o usuario é valido e retorna uma mensagem de acordo
    fun generateLoginValidationMessage() : String{
       if (user.isUserValid()) return "Login Sucess"
       else return "Login Error"
    }


}