package com.example.a19bsit087

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_addteacherinfo.*

class addteacherinfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addteacherinfo)
        var helper = dbhelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")

        var check = arrayOf(userid)
        var rs: Cursor = db.rawQuery("SELECT * FROM TEACHERINFO WHERE USERID=?", check)
        rs.requery()
        if (rs.moveToNext()) {
            name.setText(rs.getString(1).toString())
            designation.setText(rs.getString(2))
            course.setText(rs.getString(3))
            subject.setText(rs.getString(4))
            mobile.setText(rs.getString(5))
            email.setText(rs.getString(6))
        }
        else {
            name.setHint("Enter Name")
            designation.setHint("Enter Designation")
            course.setHint("Enter Course")
            subject.setHint("Enter Subject")
            mobile.setHint("Enter Mobile No")
            email.setHint("Enter Email")


        }
        btnupdate.setOnClickListener {
            if(name.text.toString() !="" && designation.text.toString() !="" && course.text.toString() !="" && subject.text.toString() !="" && mobile.text.toString() !="" && email.text.toString() !="")
            {
                var cv = ContentValues()
                cv.put("USERID",userid)
                cv.put("NAME", name.text.toString())
                cv.put("DESIGNATION", designation.text.toString())
                cv.put("COURSE", course.text.toString())
                cv.put("SUBJECT", subject.text.toString())
                cv.put("MOBILE", mobile.text.toString())
                cv.put("EMAIL", email.text.toString())
                db.insert("TEACHERINFO",null,cv)
                rs.requery()
                Toast.makeText(this, "Teacher Information Updated", Toast.LENGTH_LONG).show()
                var intent = Intent(this,viewteacherinfo::class.java)
                intent.putExtra("USER",userid)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "Enter All details", Toast.LENGTH_LONG).show()
            }

        }
        btnhome.setOnClickListener { var intent = Intent(this,viewteacherinfo::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent) }
        btnstudent.setOnClickListener { var intent = Intent(this,addstudent::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent) }
    }

}