package com.example.kotlincalculator.presenter

import com.example.kotlincalculator.CalculatorActivity
import com.example.kotlincalculator.R
import com.example.kotlincalculator.mvp.model.CalculatorModel
import com.example.kotlincalculator.mvp.presenter.CalculatorPresenter
import com.example.kotlincalculator.mvp.view.CalculatorView
import com.example.kotlincalculator.utils.EMPTY_STRING
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class CalculatorPresenterTest {

    companion object {
        private const val ZERO: String = "0"
        private const val ONE: String = "1"
        private const val TWO: String = "2"
        private const val FOUR: String = "4"
        private const val ELEVEN: String = "11"
        private const val PLUS: String = "+"
        private const val MINUS: String = "-"
        private const val MULTIPLY: String = "*"
        private const val SHARE: String = "/"
        private const val POINT: String = "."
    }

    private val mockModel: CalculatorModel = mock()
    private val mockView: CalculatorView = mock()
    private val activity: CalculatorActivity = mock()
    private var presenter: CalculatorPresenter? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        whenever(mockView.activity).thenReturn(activity)
        presenter = CalculatorPresenter(mockView, mockModel)
    }

    @Test
    fun onNumberPressedWithFirstOperandEmpty() {
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        whenever(mockModel.operator).thenReturn(EMPTY_STRING)
        presenter?.onNumberPressed(ONE)
        verify(mockModel).firstOperand =
            ONE
        verify(mockView).setVisor(mockModel.firstOperand)

    }

    @Test
    fun onNumberPressedWithFirstOperandNotEmpty() {
        whenever(mockModel.firstOperand).thenReturn(ONE)
        whenever(mockModel.operator).thenReturn(EMPTY_STRING)
        presenter?.onNumberPressed(ONE)
        verify(mockModel).firstOperand =
            ELEVEN
        verify(mockView).setVisor(mockModel.firstOperand)
    }

    @Test
    fun onNumberPressedWithOperatorAndSecondOperandEmpty() {
        whenever(mockModel.operator).thenReturn(PLUS)
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        whenever(mockModel.secondOperand).thenReturn(EMPTY_STRING)
        presenter?.onNumberPressed(ONE)
        verify(mockModel).secondOperand =
            ONE
        verify(mockView).setVisor("${mockModel.firstOperand} ${mockModel.operator} ${mockModel.secondOperand}")
    }

    @Test
    fun onNumberPressedWithOperatorAndSecondOperandNotEmpty() {
        whenever(mockModel.operator).thenReturn(PLUS)
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        whenever(mockModel.secondOperand).thenReturn(ONE)
        presenter?.onNumberPressed(ONE)
        verify(mockModel).secondOperand =
            ELEVEN
        verify(mockView).setVisor("${mockModel.firstOperand} ${mockModel.operator} ${mockModel.secondOperand}")
    }

    @Test
    fun onOperatorPressedWithFirstOperandEmptyandResultEmpty() {
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        whenever(mockModel.operator).thenReturn(EMPTY_STRING)
        whenever(mockModel.result).thenReturn(EMPTY_STRING)
        presenter?.onOperatorPressed(PLUS)
        verify(mockModel).operator =
            PLUS
        verify(mockView).setVisor("${mockModel.firstOperand} ${mockModel.operator}")
    }

    @Test
    fun onOperatorPressedWithFirstOperandEmptyAndResultNotEmpty() {
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        whenever(mockModel.operator).thenReturn(EMPTY_STRING)
        whenever(mockModel.result).thenReturn(ONE)
        presenter?.onOperatorPressed(PLUS)
        verify(mockModel).operator =
            PLUS
        verify(mockView).setVisor("${mockModel.firstOperand} ${mockModel.operator}")
    }

    @Test
    fun onOperatorPressedWithFirstOperandNotEmpty() {
        whenever(mockModel.firstOperand).thenReturn(ONE)
        whenever(mockModel.operator).thenReturn(EMPTY_STRING)
        presenter?.onOperatorPressed(PLUS)
        verify(mockModel).operator =
            PLUS
        verify(mockView).setVisor("${mockModel.firstOperand} ${mockModel.operator}")
    }

    @Test
    fun onPointPressedFirstOperand() {
        whenever(mockModel.firstOperand).thenReturn(ONE)
        whenever(mockModel.operator).thenReturn(EMPTY_STRING)
        presenter?.onPointPressed()
        verify(mockModel).firstOperand = "$ONE$POINT"
        verify(mockView).setVisor(mockModel.firstOperand)
    }

    @Test
    fun onPointPressedSecondOperand() {
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        whenever(mockModel.secondOperand).thenReturn(ONE)
        whenever(mockModel.operator).thenReturn(PLUS)
        presenter?.onPointPressed()
        verify(mockModel).secondOperand = "$ONE$POINT"
        verify(mockView).setVisor("${mockModel.firstOperand} ${mockModel.operator} ${mockModel.secondOperand}")
    }

    @Test
    fun onClearPressedSecondOperand() {
        whenever(mockModel.secondOperand).thenReturn(ELEVEN)
        whenever(mockModel.operator).thenReturn(PLUS)
        whenever(mockModel.firstOperand).thenReturn(EMPTY_STRING)
        presenter?.onClearPressed()
        verify(mockModel).secondOperand =
            ONE
        verify(mockView).setVisor("${mockModel.firstOperand} ${mockModel.operator} ${mockModel.secondOperand}")
    }

    @Test
    fun onClearPressedOperator() {
        whenever(mockModel.firstOperand).thenReturn(ONE)
        whenever(mockModel.operator).thenReturn(PLUS)
        whenever(mockModel.secondOperand).thenReturn(EMPTY_STRING)
        presenter?.onClearPressed()
        verify(mockModel).operator = EMPTY_STRING
        verify(mockView).setVisor(mockModel.firstOperand)
    }

    @Test
    fun onClearPressedFirstOperand() {
        whenever(mockModel.firstOperand).thenReturn(ELEVEN)
        whenever(mockModel.operator).thenReturn(EMPTY_STRING)
        whenever(mockModel.secondOperand).thenReturn(EMPTY_STRING)
        presenter?.onClearPressed()
        verify(mockModel).firstOperand =
            ONE
        verify(mockView).setVisor(mockModel.firstOperand)
    }

    @Test
    fun onClearAllPressed() {
        whenever(mockModel.firstOperand).thenReturn(ONE)
        whenever(mockModel.operator).thenReturn(PLUS)
        whenever(mockModel.secondOperand).thenReturn(ONE)
        whenever(mockModel.result).thenReturn(ONE)
        presenter?.onClearAllPressed()
        verify(mockModel).cleanVisor()
        verify(mockView).setVisor(EMPTY_STRING)
        verify(mockView).showResult(EMPTY_STRING)
    }

    @Test
    fun onEqualsPressedSUMOperator() {
        whenever(mockModel.firstOperand).thenReturn(ONE)
        whenever(mockModel.operator).thenReturn(PLUS)
        whenever(mockModel.secondOperand).thenReturn(ONE)
        whenever(mockModel.result).thenReturn(TWO)
        presenter?.onEqualsPressed()
        verify(mockView).showResult(mockModel.result)
        assertEquals(TWO, mockModel.result)
    }

    @Test
    fun onEqualsPressedMINUSOperator() {
        whenever(mockModel.firstOperand).thenReturn(ONE)
        whenever(mockModel.operator).thenReturn(MINUS)
        whenever(mockModel.secondOperand).thenReturn(ONE)
        whenever(mockModel.result).thenReturn(ZERO)
        presenter?.onEqualsPressed()
        verify(mockView).showResult(mockModel.result)
        assertEquals(ZERO, mockModel.result)
    }

    @Test
    fun onEqualsPressedMULTIPLYOperator() {
        whenever(mockModel.firstOperand).thenReturn(TWO)
        whenever(mockModel.operator).thenReturn(MULTIPLY)
        whenever(mockModel.secondOperand).thenReturn(TWO)
        whenever(mockModel.result).thenReturn(FOUR)
        presenter?.onEqualsPressed()
        verify(mockView).showResult(mockModel.result)
        assertEquals(FOUR, mockModel.result)
    }


    @Test
    fun onEqualsPressedSHAREOperator() {
        whenever(mockModel.firstOperand).thenReturn(TWO)
        whenever(mockModel.operator).thenReturn(SHARE)
        whenever(mockModel.secondOperand).thenReturn(TWO)
        whenever(mockModel.result).thenReturn(ONE)
        presenter?.onEqualsPressed()
        verify(mockView).showResult(mockModel.result)
        assertEquals(ONE, mockModel.result)
    }

    @Test
    fun onEqualsPressedSHAREOperatorButZeroDivisor() {
        whenever(mockModel.firstOperand).thenReturn(TWO)
        whenever(mockModel.operator).thenReturn(SHARE)
        whenever(mockModel.secondOperand).thenReturn(ZERO)
        whenever(mockModel.result).thenReturn(EMPTY_STRING)
        presenter?.onEqualsPressed()
        verify(mockView).sendErrorMessage(R.string.toast_msg_divide)
    }

}