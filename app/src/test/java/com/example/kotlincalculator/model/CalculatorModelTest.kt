package com.example.kotlincalculator.model

import com.example.kotlincalculator.mvp.model.CalculatorModel
import com.example.kotlincalculator.utils.EMPTY_STRING
import junit.framework.Assert.assertEquals
import org.junit.Test

class CalculatorModelTest {

    private var modelTest = CalculatorModel()

    @Test
    fun clearVisorTest() {
        modelTest.cleanVisor()
        assertEquals(EMPTY_STRING, modelTest.firstOperand)
        assertEquals(EMPTY_STRING, modelTest.secondOperand)
        assertEquals(EMPTY_STRING, modelTest.operator)
    }
}