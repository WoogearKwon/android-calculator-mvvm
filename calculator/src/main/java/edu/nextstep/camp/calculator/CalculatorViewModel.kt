package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()

    private val _expressionEvent = SingleLiveEvent<Expression>()
    val expressionEvent: LiveData<Expression> get() = _expressionEvent

    private val _errorEvent = SingleLiveEvent<Void>()
    val errorEvent: LiveData<Void> get() = _errorEvent

    init {
        _expressionEvent.value = Expression.EMPTY
    }

    fun addToExpression(operand: Int) {
        val newExpression = _expressionEvent.value!! + operand
        _expressionEvent.value =  newExpression
    }

    fun addToExpression(operator: Operator) {
        val newExpression = _expressionEvent.value!! + operator
        _expressionEvent.value =  newExpression
    }

    fun removeLast() {
        val newExpression = _expressionEvent.value!!.removeLast()
        _expressionEvent.value =  newExpression
    }

    fun calculate() {
        val result = calculator.calculate(_expressionEvent.value!!.toString())
        if (result == null) {
            _errorEvent.call()
            return
        }

        _expressionEvent.value = Expression(listOf(result))
    }
}