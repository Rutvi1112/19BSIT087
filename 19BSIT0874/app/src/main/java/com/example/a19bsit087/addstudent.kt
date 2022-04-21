package com.example.a19bsit087

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_addstudent.*

class addstudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addstudent)
        var helper = dbhelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")

        btnsave.setOnClickListener {
            var check = arrayOf(userid, rno.text.toString())
            var rs: Cursor = db.rawQuery("SELECT * FROM STUDENTINFO WHERE USERID=? AND RNO=?", check)
            rs.moveToFirst()
            if(rs.moveToNext()) {
                Toast.makeText(this, "Already Entered", Toast.LENGTH_LONG).show()
                rno.setText("")
                sname.setText("")
                rno.requestFocus()
            } else {
                var beforeinsert = rs.count
                var cv = ContentValues()
                cv.put("USERID", userid)
                cv.put("RNO", rno.text.toString())
                cv.put("NAME", sname.text.toString())
                db.insert("STUDENTINFO", null, cv)
                rs.requery()
                var afterinsert = rs.count
                if (afterinsert > beforeinsert) {
                    Toast.makeText(this, "Record Inserted Sucessfully", Toast.LENGTH_LONG)
                        .show()
                    rno.setText("")
                    sname.setText("")
                    rno.requestFocus()

                } else {
                    Toast.makeText(this, "Record Not Inserted", Toast.LENGTH_LONG).show()

                }


            }

        }
        btnview.setOnClickListener {
            var intent = Intent(this, viewstudent::class.java)
            intent.putExtra("USER",userid)

            startActivity(intent)
        }
        btnlogout.setOnClickListener { startActivity((Intent(this,login::class.java))) }
    }
}
