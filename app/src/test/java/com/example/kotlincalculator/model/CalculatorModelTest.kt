package com.example.kotlincalculator.model

import com.example.kotlincalculator.mvp.model.CalculatorModel
import com.example.kotlincalculator.utils.EMPTY_STRING
import junit.framework.Assert.assertEquals
import org.junit.Test

class CalculatorModelTest {

    companion object {
        private const val ZERO: String = "0"
        private const val ONE: String = "1"
        private const val PLUS: String = "+"
    }

    private var modelTest = CalculatorModel()

    @Test
    fun clearVisorTest() {
        modelTest.cleanVisor()
        assertEquals(EMPTY_STRING, modelTest.firstOperand)
        assertEquals(EMPTY_STRING, modelTest.secondOperand)
        assertEquals(EMPTY_STRING, modelTest.operator)
    }

    @Test
    fun setFirstOperand() {
        modelTest.firstOperand = ONE
        assertEquals(ONE, modelTest.firstOperand)
    }

    @Test
    fun setSecondOperand() {
        modelTest.secondOperand = ONE
        assertEquals(ONE, modelTest.secondOperand)
    }

    @Test
    fun setOperator() {
        modelTest.operator = PLUS
        assertEquals(PLUS, modelTest.operator)
    }

    @Test
    fun setResult() {
        modelTest.result = ONE
        assertEquals(ONE, modelTest.result)
    }
}