package com.example.kotlincalculator.mvp.presenter

import com.example.kotlincalculator.mvp.model.CalculatorModel
import com.example.kotlincalculator.mvp.view.CalculatorView
import com.example.kotlincalculator.utils.ZERO

class CalculatorPresenter(view: CalculatorView, model: CalculatorModel) {
    val view: CalculatorView = view
    var model: CalculatorModel = model
    fun onNumberPressed(number: String) {
        when {
            model. firstOperand.isEmpty() -> {
                model.firstOperand = number
                view.setVisor(model.firstOperand)
            }
            model.firstOperand.isNotEmpty() && model.operator.isEmpty()-> {
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
        when {
            model.firstOperand.isEmpty()&& model.result.isEmpty() -> {
                model.operator = operator
                view.setVisor("$ZERO  ${model.operator}")
            }
            model.firstOperand.isEmpty() && model.result.isNotEmpty()-> {
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

    fun onEqualsPressed() {
        //TODO equals funcionality
    }
}