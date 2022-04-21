package com.example.a19bsit087

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_viewstudent.*

class viewstudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewstudent)
        var helper = dbhelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")
        var check = arrayOf(userid)
        var rs: Cursor = db.rawQuery("SELECT * FROM STUDENTINFO WHERE USERID=?",check)
        rs.moveToFirst()
        if(rs.moveToNext()) {
            val rno = ArrayList<String>()
            val name = ArrayList<String>()
            rs.moveToFirst()
            while (rs.moveToNext()) {
                rno.add(rs.getString(1))
                name.add(rs.getString(2))
            }
            var myadapter = custadapter(this, rno, name)
            lview.adapter = myadapter
        }
        else
        {
            Toast.makeText(this, "No Records Found", Toast.LENGTH_LONG).show()
            var intent = Intent(this,addstudent::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)
        }



    }
    }
