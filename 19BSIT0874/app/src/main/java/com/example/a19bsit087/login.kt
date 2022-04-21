package com.example.a19bsit087

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.example.a19bsit087.login
import com.example.a19bsit087.signup
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_login`)
        var helper = dbhelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase

        Login.setOnClickListener {

            if(userid.text.toString() !="" && password.text.toString() != "")
            {
                var etuid = userid.text.toString()
                var etupw = password.text.toString()
                var check = arrayOf(etuid,etupw)
                var rs: Cursor = db.rawQuery("SELECT * FROM LOGIN WHERE USERID=? AND PASSWORD=?",check)
                rs.requery()
                if(rs.moveToNext())
                {
                    var intent = Intent(this, addteacherinfo::class.java)
                    intent.putExtra("USER",etuid)
                    startActivity(intent)
                }
                else
                {
                    var toast = Toast.makeText(this,"Enter Correct Credentials to Login",Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.TOP,0,300)
                    toast.show()
                    userid.setText("")
                    password.setText("")
                    userid.requestFocus()
                }
            }

            else if(userid.text.toString() == "" || password.text.toString() == "")
            {
                var toast = Toast.makeText(this,"Enter UserId and Password Both",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP,0,300)
                toast.show()
                userid.requestFocus()
            }

            else
            {
                var toast = Toast.makeText(this,"Enter Correct Credentials to Login",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP,0,300)
                toast.show()
                userid.setText("")
                password.setText("")
                userid.requestFocus()
            }


        }
        signup1.setOnClickListener {
            startActivity(Intent(this, signup::class.java))

        }

    }
}