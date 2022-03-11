package edu.nextstep.camp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.domain.Calculation

@Entity
internal data class Memory(
    @ColumnInfo
    val expression: String,

    @ColumnInfo
    val result: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) {

    fun toCalculation(): Calculation {
        return Calculation(
            expression = this.expression,
            result = this.result
        )
    }
}