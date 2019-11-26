package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlincalculator.mvp.model.CalculatorModel
import com.example.kotlincalculator.mvp.presenter.CalculatorPresenter
import com.example.kotlincalculator.mvp.view.CalculatorView
import com.example.kotlincalculator.utils.EIGHT
import com.example.kotlincalculator.utils.FIVE
import com.example.kotlincalculator.utils.FOUR
import com.example.kotlincalculator.utils.MINUS
import com.example.kotlincalculator.utils.MULTIPLY
import com.example.kotlincalculator.utils.NINE
import com.example.kotlincalculator.utils.ONE
import com.example.kotlincalculator.utils.SEVEN
import com.example.kotlincalculator.utils.SHARE
import com.example.kotlincalculator.utils.SIX
import com.example.kotlincalculator.utils.SUM
import com.example.kotlincalculator.utils.THREE
import com.example.kotlincalculator.utils.TWO
import com.example.kotlincalculator.utils.ZERO
import kotlinx.android.synthetic.main.activity_main.button_clear
import kotlinx.android.synthetic.main.activity_main.button_clear_all
import kotlinx.android.synthetic.main.activity_main.button_zero
import kotlinx.android.synthetic.main.activity_main.button_one
import kotlinx.android.synthetic.main.activity_main.button_two
import kotlinx.android.synthetic.main.activity_main.button_three
import kotlinx.android.synthetic.main.activity_main.button_four
import kotlinx.android.synthetic.main.activity_main.button_five
import kotlinx.android.synthetic.main.activity_main.button_six
import kotlinx.android.synthetic.main.activity_main.button_seven
import kotlinx.android.synthetic.main.activity_main.button_eight
import kotlinx.android.synthetic.main.activity_main.button_equal
import kotlinx.android.synthetic.main.activity_main.button_nine
import kotlinx.android.synthetic.main.activity_main.button_sum
import kotlinx.android.synthetic.main.activity_main.button_multiply
import kotlinx.android.synthetic.main.activity_main.button_point
import kotlinx.android.synthetic.main.activity_main.button_share
import kotlinx.android.synthetic.main.activity_main.button_subtract

class CalculatorActivity : AppCompatActivity() {
    private var presenter = CalculatorPresenter(CalculatorView(this), CalculatorModel())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setListeners()
    }

    private fun setListeners() {
        button_zero.setOnClickListener { presenter.onNumberPressed(ZERO) }
        button_one.setOnClickListener { presenter.onNumberPressed(ONE) }
        button_two.setOnClickListener { presenter.onNumberPressed(TWO) }
        button_three.setOnClickListener { presenter.onNumberPressed(THREE) }
        button_four.setOnClickListener { presenter.onNumberPressed(FOUR) }
        button_five.setOnClickListener { presenter.onNumberPressed(FIVE) }
        button_six.setOnClickListener { presenter.onNumberPressed(SIX) }
        button_seven.setOnClickListener { presenter.onNumberPressed(SEVEN) }
        button_eight.setOnClickListener { presenter.onNumberPressed(EIGHT) }
        button_nine.setOnClickListener { presenter.onNumberPressed(NINE) }
        button_sum.setOnClickListener { presenter.onOperatorPressed(SUM) }
        button_subtract.setOnClickListener { presenter.onOperatorPressed(MINUS) }
        button_multiply.setOnClickListener { presenter.onOperatorPressed(MULTIPLY) }
        button_share.setOnClickListener { presenter.onOperatorPressed(SHARE) }
        button_equal.setOnClickListener { presenter.onEqualsPressed() }
        button_point.setOnClickListener { presenter.onPointPressed() }
        button_clear.setOnClickListener { presenter.onClearPressed() }
        button_clear_all.setOnClickListener { presenter.onClearAllPressed() }
    }
}