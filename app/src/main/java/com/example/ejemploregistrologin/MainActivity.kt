package com.example.ejemploregistrologin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    //Firebase
    private lateinit var auth: FirebaseAuth;
    //private val
    private lateinit var authStateListener: AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Firebase
        auth = Firebase.auth

        val currentUser = auth.currentUser
        //Boton iniciar sesion
        val btnIniciarSesion = findViewById<Button>(R.id.loginButton)
        //Usuario textEdit
        val txtUsuario = findViewById<TextInputEditText>(R.id.usernameEditText)
        //Contraseña textEdit
        val txtContrasena = findViewById<TextInputEditText>(R.id.passwordEditText)

        btnIniciarSesion.setOnClickListener {
            signIn(txtUsuario.text.toString(), txtContrasena.text.toString())
        }
    }

    private fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    Toast.makeText(this, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, postLogin::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Error en la autenticacion", Toast.LENGTH_SHORT).show()
                }
            }
    }
}