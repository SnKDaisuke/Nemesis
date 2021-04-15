package com.example.nemesis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import com.firebase.ui.auth.data.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private var googleSignIn : GoogleSignInClient? = null
    private val RC_SIGN_IN = 1000
    private val REQUEST_APP : Int = 50


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignIn = GoogleSignIn.getClient(this,gso)

        //Execute un code si le joueur se connecte

        google_button.setOnClickListener{
            Log.wtf("Login", "Appuie sur bouton se connecter")
            var signIntent = googleSignIn?.signInIntent
            startActivityForResult(signIntent, RC_SIGN_IN)
        }
    }

    override fun onStart() { super.onStart() }
    override fun onResume() { super.onResume() }
    override fun onPause() { super.onPause() }
    override fun onRestart() { super.onRestart() }
    override fun onStop() { super.onStop() }
    override fun onDestroy() { super.onDestroy() }

    fun moveNextPage() {
        //Execute l'application normalement
        Log.wtf("Login", "Dans moveNextPage2")
        var currentuser = FirebaseAuth.getInstance().currentUser
        if (currentuser != null ){
            val intent =  Intent(this@Login, MainActivity::class.java)
            startActivityForResult(intent, REQUEST_APP)
        }

    }


    fun FirebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        //Vérifie la connexion Google
        Log.wtf("Login", "Dans FirebaseAuthWithGoogle2")
        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                    moveNextPage()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.wtf("Login", "Avant IF RC_SIGN_IN ")
        if (requestCode == RC_SIGN_IN ){
            Log.wtf("Login", "Dans IF RC_SIGN_IN ")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            FirebaseAuthWithGoogle(account)
        }
    }
}