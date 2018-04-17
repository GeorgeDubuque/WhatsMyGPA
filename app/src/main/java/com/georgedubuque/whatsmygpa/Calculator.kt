package com.georgedubuque.whatsmygpa

import android.os.Bundle
import android.os.Debug
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.android.synthetic.main.content_calculator.*
import java.text.DecimalFormat

class Calculator : AppCompatActivity() {

    var gpArray = ArrayList<EditText>()
    var chArray = ArrayList<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("debug","creating")
        setContentView(R.layout.activity_calculator)
        setSupportActionBar(toolbar)
        GpaButton.setOnClickListener {generateGPA()}
        add_grade_button.setOnClickListener { addGrade() }
        remove_grade_button.setOnClickListener { removeGrade() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.d("debug","savingInstanceState")
        super.onSaveInstanceState(outState)
        if(outState != null) {
            outState.putSerializable("gpArray", gpArray)
            outState.putSerializable("chArray", chArray)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("debug","restoring instance state")
        if(savedInstanceState != null) {
            gpArray = (savedInstanceState.getSerializable("gpArray") as ArrayList<EditText>)
            chArray = (savedInstanceState.getSerializable("chArray") as ArrayList<EditText>)
            //repopulateText()
        }
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

    fun repopulateText(){
            Log.d("debug","gpArray.size")

            for (x in 1 until gpArray.size) {
                addGrade()
            }

    }

    fun addGrade(){
        val gradeLayout = gp_layout
        val chLayout = cr_layout

        val gText = EditText(this)
        val cText = EditText(this)

        makeChText(cText)
        makeGpText(gText)

        gpArray.add(gText)
        chArray.add(cText)

        gradeLayout.addView(gText)
        chLayout.addView(cText)

    }

    fun removeGrade(){
        if(gpArray.size > 0) {
            gp_layout.removeView(gpArray[gpArray.size - 1])
            gpArray.removeAt(gpArray.size - 1)
            cr_layout.removeView(chArray[chArray.size - 1])
            chArray.removeAt(chArray.size - 1)
        }
    }

    fun makeGpText(input: EditText){
        input.inputType = 8194
        input.setEms(10)
        input.textAlignment = View.TEXT_ALIGNMENT_CENTER
        input.setTextSize(25f)
    }

    fun makeChText(input: EditText) {
        input.inputType = InputType.TYPE_CLASS_NUMBER
        input.setEms(10)
        input.textAlignment = View.TEXT_ALIGNMENT_CENTER
        input.setTextSize(25f)
    }

    fun generateGPA(){
        var gp : String
        var ch : String
        var totalGradePoints = 0.0
        var totalCreditHours  = 0
        var gpa : Double

        for(x in 0 until gpArray.size){
            gp = gpArray[x].text.toString()
            ch = chArray[x].text.toString()
            if(gp.isNotEmpty() && ch.isNotEmpty()) {
                Log.d("DEBUG", ch)
                Log.d("DEBUG", gp)

                totalGradePoints += toGradePoint(gp.toDouble()) * ch.toInt()
                totalCreditHours += ch.toInt()
            }

        }

        gpa = totalGradePoints/totalCreditHours

        Toast.makeText(this@Calculator, gpa.toString(), Toast.LENGTH_SHORT).show()
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



}
