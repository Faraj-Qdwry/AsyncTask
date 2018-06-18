package com.example.all_the_way_up.asynctaskex

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintSet
import android.support.constraint.ConstraintLayout
import android.transition.TransitionManager
import android.view.View
import kotlinx.android.synthetic.main.nnewlyout.*


class newlyout : AppCompatActivity() {


    private val applyConstraintSet = ConstraintSet()
    private val resetConstraintSet = ConstraintSet()

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nnewlyout)

        resetConstraintSet.clone(constraintLyout1)
        applyConstraintSet.clone(constraintLyout1)

        applyButton.setOnClickListener({ onApplyClick(it) })
        resetButton.setOnClickListener({onResetClick(it)})
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun onApplyClick(view: View) {
        TransitionManager.beginDelayedTransition(constraintLyout1);
        applyConstraintSet.centerHorizontally(R.id.button1,R.id.constraintLyout1)
        applyConstraintSet.centerHorizontally(R.id.button2,R.id.constraintLyout1)
        applyConstraintSet.centerHorizontally(R.id.button3,R.id.constraintLyout1)
        applyConstraintSet.applyTo(constraintLyout1)
    }

    fun onResetClick(view: View) {
        resetConstraintSet.applyTo(constraintLyout1)
    }
}
