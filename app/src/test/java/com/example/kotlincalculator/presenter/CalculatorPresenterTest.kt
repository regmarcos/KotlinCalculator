package com.example.kotlincalculator.presenter

import com.example.kotlincalculator.CalculatorActivity
import com.example.kotlincalculator.R
import com.example.kotlincalculator.mvp.model.CalculatorModel
import com.example.kotlincalculator.mvp.presenter.CalculatorPresenter
import com.example.kotlincalculator.mvp.view.CalculatorView
import com.example.kotlincalculator.utils.EMPTY_STRING
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

class CalculatorPresenterTest {

    companion object {
        private const val ZERO: String = "0"
        private const val ONE: String = "1"
        private const val TWO: String = "2"
        private const val FOUR: String = "4"
        private const val ELEVEN: String = "11"
        private const val ZERO_FLOAT: String = "0.0"
        private const val TWO_FLOAT: String = "2.0"
        private const val FOUR_FLOAT: String = "4.0"
        private const val PLUS: String = "+"
        private const val MINUS: String = "-"
        private const val MULTIPLY: String = "*"
        private const val SHARE: String = "/"
        private const val POINT: String = "."
    }

    private val mockModel: CalculatorModel = spy()
    private val mockView: CalculatorView = mock()
    private val activity: CalculatorActivity = mock()
    private lateinit var presenter: CalculatorPresenter

    @Before
    fun setup() {
        whenever(mockView.activity).thenReturn(activity)
        presenter = CalculatorPresenter(mockView, mockModel)
    }

    @Test
    fun onNumberPressedWithFirstOperandEmpty() {
        mockModel.firstOperand = EMPTY_STRING
        presenter.onNumberPressed(ONE)
        assertEquals(ONE, mockModel.firstOperand)
    }

    @Test
    fun onNumberPressedWithFirstOperandNotEmpty() {
        mockModel.firstOperand = ONE
        presenter.onNumberPressed(ONE)
        assertEquals(ELEVEN, mockModel.firstOperand)
    }

    @Test
    fun onNumberPressedWithOperatorAndSecondOperandEmpty() {
        whenever(mockModel.firstOperand).thenReturn(ZERO)
        whenever(mockModel.operator).thenReturn(PLUS)
        mockModel.secondOperand = EMPTY_STRING
        presenter.onNumberPressed(ONE)
        assertEquals(ONE, mockModel.secondOperand)
    }

    @Test
    fun onNumberPressedWithOperatorAndSecondOperandNotEmpty() {
        whenever(mockModel.operator).thenReturn(PLUS)
        whenever(mockModel.firstOperand).thenReturn(ZERO)
        mockModel.secondOperand = ONE
        presenter.onNumberPressed(ONE)
        assertEquals(ELEVEN, mockModel.secondOperand)
    }

    @Test
    fun onOperatorPressedWithFirstOperandEmptyAndResultEmpty() {
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        whenever(mockModel.result).thenReturn(EMPTY_STRING)
        mockModel.operator = PLUS
        presenter.onOperatorPressed(PLUS)
        assertEquals(PLUS, mockModel.operator)
    }

    @Test
    fun onOperatorPressedWithFirstOperandEmptyAndResultNotEmpty() {
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        whenever(mockModel.result).thenReturn(ONE)
        presenter.onOperatorPressed(PLUS)
        assertEquals(PLUS, mockModel.operator)
    }

    @Test
    fun onOperatorPressedWithFirstOperandNotEmpty() {
        whenever(mockModel.firstOperand).thenReturn(ONE)
        presenter.onOperatorPressed(PLUS)
        assertEquals(PLUS, mockModel.operator)
    }

    @Test
    fun onPointPressedFirstOperand() {
        mockModel.firstOperand = ONE
        presenter.onPointPressed()
        assertEquals("$ONE$POINT", mockModel.firstOperand)
    }

    @Test
    fun onPointPressedSecondOperand() {
        whenever(mockModel.firstOperand).thenReturn(ZERO)
        whenever(mockModel.operator).thenReturn(PLUS)
        mockModel.secondOperand = ONE
        presenter.onPointPressed()
        assertEquals("$ONE$POINT", mockModel.secondOperand)
    }

    @Test
    fun onClearPressedSecondOperand() {
        mockModel.secondOperand = ELEVEN
        whenever(mockModel.operator).thenReturn(PLUS)
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        presenter.onClearPressed()
        assertEquals(ONE, mockModel.secondOperand)
    }

    @Test
    fun onClearPressedOperator() {
        whenever(mockModel.firstOperand).thenReturn(ONE)
        mockModel.operator = PLUS
        whenever(mockModel.secondOperand).thenReturn(EMPTY_STRING)
        presenter.onClearPressed()
        assertEquals(EMPTY_STRING, mockModel.operator)
    }

    @Test
    fun onClearPressedFirstOperand() {
        mockModel.firstOperand = ELEVEN
        whenever(mockModel.operator).thenReturn(EMPTY_STRING)
        whenever(mockModel.secondOperand).thenReturn(EMPTY_STRING)
        presenter.onClearPressed()
        assertEquals(ONE, mockModel.firstOperand)
    }

    @Test
    fun onClearAllPressed() {
        mockModel.firstOperand = ONE
        mockModel.operator = PLUS
        mockModel.secondOperand = ONE
        mockModel.result = TWO
        presenter.onClearAllPressed()
        assertEquals(EMPTY_STRING, mockModel.firstOperand)
        assertEquals(EMPTY_STRING, mockModel.secondOperand)
        assertEquals(EMPTY_STRING, mockModel.operator)
        assertEquals(EMPTY_STRING, mockModel.result)
    }

    @Test
    fun onEqualsPressedSUMOperator() {
        mockModel.firstOperand = ONE
        mockModel.operator = PLUS
        mockModel.secondOperand = ONE
        presenter.onEqualsPressed()
        assertEquals(TWO_FLOAT, mockModel.result)
    }

    @Test
    fun onEqualsPressedMINUSOperator() {
        mockModel.firstOperand = ONE
        mockModel.operator = MINUS
        mockModel.secondOperand = ONE
        presenter.onEqualsPressed()
        assertEquals(ZERO_FLOAT, mockModel.result)
    }

    @Test
    fun onEqualsPressedMULTIPLYOperator() {
        mockModel.firstOperand = TWO
        mockModel.operator = MULTIPLY
        mockModel.secondOperand = TWO
        presenter.onEqualsPressed()
        assertEquals(FOUR_FLOAT, mockModel.result)
    }

    @Test
    fun onEqualsPressedSHAREOperator() {
        mockModel.firstOperand = FOUR
        mockModel.operator = SHARE
        mockModel.secondOperand = TWO
        presenter.onEqualsPressed()
        assertEquals(TWO_FLOAT, mockModel.result)
    }

    @Test
    fun onEqualsPressedSHAREOperatorButZeroDivisor() {
        mockModel.firstOperand = FOUR
        mockModel.operator = SHARE
        mockModel.secondOperand = ZERO
        presenter.onEqualsPressed()
        verify(mockView).sendErrorMessage(R.string.toast_msg_divide)
    }

}