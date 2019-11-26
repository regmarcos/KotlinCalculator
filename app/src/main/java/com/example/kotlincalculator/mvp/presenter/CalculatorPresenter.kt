package com.example.kotlincalculator.mvp.presenter

import com.example.kotlincalculator.R
import com.example.kotlincalculator.mvp.model.CalculatorModel
import com.example.kotlincalculator.mvp.view.CalculatorView
import com.example.kotlincalculator.utils.EMPTY_STRING
import com.example.kotlincalculator.utils.MINUS
import com.example.kotlincalculator.utils.MULTIPLY
import com.example.kotlincalculator.utils.POINT
import com.example.kotlincalculator.utils.SHARE
import com.example.kotlincalculator.utils.SUM
import com.example.kotlincalculator.utils.ZERO

class CalculatorPresenter(private val view: CalculatorView, private var model: CalculatorModel) {
    fun onNumberPressed(number: String) {
        when {
            model.firstOperand.isEmpty() -> {
                model.firstOperand = number
                view.setVisor(model.firstOperand)
            }
            model.firstOperand.isNotEmpty() && model.operator.isEmpty() -> {
                model.firstOperand += number
                view.setVisor(model.firstOperand)
            }
            model.operator.isNotEmpty() && model.secondOperand.isEmpty() -> {
                model.secondOperand = number
                view.setVisor("${model.firstOperand} ${model.operator} ${model.secondOperand}")
            }
            else -> {
                model.secondOperand += number
                view.setVisor("${model.firstOperand}  ${model.operator}  ${model.secondOperand}")
            }
        }
    }

    fun onOperatorPressed(operator: String) {
        if (model.operator.isEmpty()) {
            when {
                model.firstOperand.isEmpty() && model.result.isEmpty() -> {
                    model.operator = operator
                    view.setVisor("$  ${model.operator}")
                }
                model.firstOperand.isEmpty() && model.result.isNotEmpty() -> {
                    model.operator = operator
                    model.firstOperand = model.result
                    view.setVisor("${model.firstOperand} ${model.operator}")
                }
                else -> {
                    model.operator = operator
                    view.setVisor("${model.firstOperand} ${model.operator}")
                }
            }
        }
    }

    fun onEqualsPressed() {
        when (model.operator) {
            SUM -> {
                model.result =
                    (model.firstOperand.toFloat() + model.secondOperand.toFloat()).toString()
            }
            MINUS -> {
                model.result =
                    (model.firstOperand.toFloat() - model.secondOperand.toFloat()).toString()
            }
            MULTIPLY -> {
                model.result =
                    (model.firstOperand.toFloat() * model.secondOperand.toFloat()).toString()
            }
            SHARE -> {
                if (model.secondOperand != ZERO) {
                    model.result =
                        (model.firstOperand.toFloat() / model.secondOperand.toFloat()).toString()
                } else {
                    view.sendErrorMessage(R.string.toast_msg_divide)
                }
            }
        }
        view.showResult(model.result)
        model.firstOperand = EMPTY_STRING
        model.secondOperand = EMPTY_STRING
        model.operator = EMPTY_STRING
    }

    fun onPointPressed() {
        when {
            !model.firstOperand.contains(POINT) && model.operator.isEmpty() -> {
                model.firstOperand += POINT
                view.setVisor(model.firstOperand)
            }
            model.operator.isNotEmpty() && !model.secondOperand.contains(POINT) -> {
                model.secondOperand += POINT
                view.setVisor("${model.firstOperand}  ${model.operator}  ${model.secondOperand}")
            }
        }
    }

    fun onClearPressed(){
        when {
            model.secondOperand.isNotEmpty() -> {
                model.secondOperand = model.secondOperand.dropLast(1)
                view.setVisor("${model.firstOperand}  ${model.operator}  ${model.secondOperand}")
            }
            model.operator.isNotEmpty() -> {
                model.operator = model.operator.drop(1)
                view.setVisor(model.firstOperand)
            }
            model.firstOperand.isNotEmpty() -> {
                model.firstOperand = model.firstOperand.dropLast(1)
                view.setVisor(model.firstOperand)
            }
        }
    }

    fun onClearAllPressed(){
        model.cleanVisor()
        model.result = EMPTY_STRING
        view.setVisor(EMPTY_STRING)
        view.showResult(EMPTY_STRING)
    }
}