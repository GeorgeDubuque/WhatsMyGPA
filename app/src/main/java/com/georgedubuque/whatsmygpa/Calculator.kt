package com.georgedubuque.whatsmygpa

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.android.synthetic.main.content_calculator.*
import java.text.DecimalFormat

class Calculator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setSupportActionBar(toolbar)

//        GpaButton.setOnClickListener { Toast.makeText (
//                this@Calculator, "GPA Button Pressed", Toast.LENGTH_SHORT).show()}
        GpaButton.setOnClickListener {generateGPA()}
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_calculator, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun generateGPA(){
        var gp1 = toGradePoint(perc1.text.toString().toDouble())
        var gp2 = toGradePoint(perc2.text.toString().toDouble())
        var gp3 = toGradePoint(perc3.text.toString().toDouble())
        var gp4 = toGradePoint(perc4.text.toString().toDouble())

        var cw1 = cw1.text.toString().toInt()
        var cw2 = cw2.text.toString().toInt()
        var cw3 = cw3.text.toString().toInt()
        var cw4 = cw4.text.toString().toInt()

        var totalGradePoints = gp1*cw1 + gp2*cw2 + gp3*cw3 + gp4*cw4
        var totalCreditHours = cw1 + cw2 + cw3 + cw4

        Toast.makeText(this@Calculator, (totalGradePoints/totalCreditHours).toString(), Toast.LENGTH_SHORT).show()
    }

    fun toGradePoint(decimalGrade: Double):Double{
        var gradePoint: Double = 0.00;
        if(decimalGrade >= 93){
            gradePoint = 4.0
        }else if(decimalGrade >= 90 && decimalGrade < 93){
            gradePoint = 3.70
        }else if(decimalGrade >= 87 && decimalGrade < 90){
            gradePoint = 3.33
        }else if(decimalGrade >= 83 && decimalGrade < 87) {
            gradePoint = 3.0
        }else if(decimalGrade >= 80 && decimalGrade < 83) {
            gradePoint = 2.70
        }else if(decimalGrade >= 77 && decimalGrade < 80) {
            gradePoint = 2.30
        }else if(decimalGrade >= 73 && decimalGrade < 77) {
            gradePoint = 2.00
        }else if(decimalGrade >= 70 && decimalGrade < 73) {
            gradePoint = 1.70
        }else if(decimalGrade >= 67 && decimalGrade < 70) {
            gradePoint = 1.30
        }else if(decimalGrade >= 63 && decimalGrade < 67) {
            gradePoint = 1.00
        }else if(decimalGrade >= 60 && decimalGrade < 63) {
            gradePoint = 0.70
        }else if(decimalGrade >= 0 && decimalGrade < 60) {
            gradePoint = 0.00
        }

        return gradePoint
    }

    fun addGrade(){
        Toast.makeText(this@Calculator, "Need to add grade.", Toast.LENGTH_LONG).show()
    }

}
