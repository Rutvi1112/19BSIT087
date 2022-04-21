package com.example.a19bsit087

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        signup2.setOnClickListener {
            if (useridsignup.text.toString() == "" || passwordsignup.text.toString() == "" || passwordsignup2.text.toString() == "") {
                var toast = Toast.makeText(
                    this,
                    "Enter UserId, Password and Re-Enter Password",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP, 0, 300)
                toast.show()
                useridsignup.setText("")
                passwordsignup.setText("")
                passwordsignup2.setText("")
                useridsignup.requestFocus()
            } else if (passwordsignup.text.toString() != passwordsignup2.text.toString()) {

                var toast = Toast.makeText(this, "Passwords Must be Same...", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0, 300)
                toast.show()
                passwordsignup.setText("")
                passwordsignup2.setText("")
                passwordsignup.requestFocus()

            } else {
                var helper = dbhelper(applicationContext)
                var db: SQLiteDatabase = helper.writableDatabase
                var check = arrayOf(useridsignup.text.toString())
                var rs: Cursor = db.rawQuery("SELECT * FROM LOGIN WHERE USERID=?", check)
                rs.requery()
                if (rs.moveToNext()) {
                    Toast.makeText(this, "User Exist, Try with new UserID", Toast.LENGTH_LONG).show()
                    useridsignup.setText("")
                    passwordsignup.setText("")
                    passwordsignup2.setText("")
                    useridsignup.requestFocus()
                } else {
                    var beforeinsert = rs.count
                    var cv = ContentValues()
                    cv.put("USERID", useridsignup.text.toString())
                    cv.put("PASSWORD", passwordsignup.text.toString())
                    db.insert("LOGIN", null, cv)
                    rs.requery()
                    var afterinsert = rs.count
                    if (afterinsert > beforeinsert) {
                        Toast.makeText(this, "Record Inserted Sucessfully", Toast.LENGTH_LONG)
                            .show()
                        startActivity(Intent(this, login::class.java))

                    } else {
                        Toast.makeText(this, "Record Not Inserted", Toast.LENGTH_LONG).show()

                    }
                    /*var splogin = getSharedPreferences("splogin", Context.MODE_PRIVATE)
                    var edt = splogin.edit()
                    edt.apply{
                        putString("uid",useridsignup.text.toString())
                        putString("pwd",passwordsignup.text.toString())
                    }.apply()
                    var toast = Toast.makeText(this,"User Created Successfully.....",Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.TOP,0,300)

                    toast.show()
                    startActivity(Intent(this, Login::class.java))*/

                }
            }


        }

    }

}