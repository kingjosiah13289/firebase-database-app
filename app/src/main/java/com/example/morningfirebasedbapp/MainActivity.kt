package com.example.morningfirebasedbapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var edtname : EditText
    lateinit var edtemail : EditText
    lateinit var edtnumb : EditText
    lateinit var btnsave : Button
    lateinit var btnview : Button
    lateinit var progressDialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtname = findViewById(R.id.medt_Name)
        edtemail = findViewById(R.id.medt_email)
        edtnumb = findViewById(R.id.medt_Idnumber)
        btnsave = findViewById(R.id.mbtn_Save)
        btnview = findViewById(R.id.mbtn_View)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Saving")
        progressDialog.setMessage("please wait....")
        btnsave.setOnClickListener {
            var name = edtname.text.toString().trim()
            var email = edtemail.text.toString().trim()
            var idnum = edtnumb.text.toString().trim()
            var id = System.currentTimeMillis().toString()
            if (name.isEmpty()){
                edtname.setError("Please fill this field")
                edtname.requestFocus()
            }else if (email.isEmpty()){
                edtemail.setError("Please fill this field")
                edtemail.requestFocus()
            }else if(idnum.isEmpty()){
                edtnumb.setError("Please fill this field")
                edtnumb.requestFocus()
            }else{
                // Proceed to save
                var user = User(name, email, idnum, id)
                // create a reference in the firebase database
                var ref = FirebaseDatabase.getInstance().
                        getReference().child("Users/"+id)
                // show the progress to start saving
                progressDialog.show()
                ref.setValue(user).addOnCompleteListener {
                    // Dismiss the progress and check if the task was successful
                 task->
                 progressDialog.dismiss()
                 if (task.isSuccessful){
                     Toast.makeText(this, "User saved successfully",
                     Toast.LENGTH_LONG).show()
                 }else{
                     Toast.makeText(this, "User saving failed",
                     Toast.LENGTH_LONG).show()
                 }
                }
            }
        }
        btnview.setOnClickListener {

        }

    }
}