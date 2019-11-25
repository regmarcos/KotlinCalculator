package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.kotlincalculator.mvp.model.CalculatorModel
import com.example.kotlincalculator.mvp.presenter.CalculatorPresenter
import com.example.kotlincalculator.mvp.view.CalculatorView
import kotlinx.android.synthetic.main.activity_main.*

class CalculatorActivity : AppCompatActivity(), View.OnClickListener {

    var presenter = CalculatorPresenter(CalculatorView(this), CalculatorModel())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setListeners()
    }

    private fun setListeners(){
        button_zero.setOnClickListener(this)
        button_one.setOnClickListener(this)
        button_two.setOnClickListener(this)
        button_three.setOnClickListener(this)
        button_four.setOnClickListener(this)
        button_five.setOnClickListener(this)
        button_six.setOnClickListener(this)
        button_seven.setOnClickListener(this)
        button_eight.setOnClickListener(this)
        button_nine.setOnClickListener(this)
        button_sum.setOnClickListener(this)
        button_subtract.setOnClickListener(this)
        button_multiply.setOnClickListener(this)
        button_share.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val textButton : String = (view as Button).text.toString()
        when(view.id){
            R.id.button_zero,R.id.button_one, R.id.button_two, R.id.button_three, R.id.button_four,
                R.id.button_five, R.id.button_six, R.id.button_seven, R.id.button_eight, R.id.button_nine -> {
                presenter.onNumberPresed(textButton)
            }
            R.id.button_sum, R.id.button_subtract, R.id.button_multiply, R.id.button_share -> {
                presenter.onOperatorPressed(textButton)
            }
        }
    }
}

