@file:Suppress("UNUSED_PARAMETER")
package mmcs.assignment2

import mmcs.assignment2.Matrix
import mmcs.assignment2.createMatrix

/**
 * Пример
 *
 * Транспонировать заданную матрицу matrix.
 */
fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0 until matrix.width) {
        for (j in 0 until matrix.height) {
            result[i, j] = matrix[j, i]
        }
    }
    return result
}
fun <E> rotate(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0 until matrix.width) {
        for (j in 0 until matrix.height) {
            result[i, j] = matrix[matrix.width - j - 1, i]
        }
    }
    return result
}

operator fun Matrix<Int>.plus(other: Matrix<Int>): Matrix<Int> {
    if (width != other.width || height != other.height) {
        throw IllegalArgumentException("")
    }
    val result = createMatrix(height, width, 0)
    for (i in 0 until height) {
        for (j in 0 until width) {
            result[i, j] = this[i, j] + other[i, j]
        }
    }
    return result
}

operator fun Matrix<Int>.unaryMinus(): Matrix<Int> {
    val result = createMatrix(height = this.height, width = this.width, e = 0)
    for (i in 0 until this.width) {
        for (j in 0 until this.height) {
            result[i, j] = -this[i, j]
        }
    }
    return result
}

operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> {
    require(this.width == other.height) {"IllegalArgumentException"}
    val result = createMatrix(height = this.height, width = other.width, e = 0)
    for (i in 0 until this.height) {
        for (j in 0 until other.width) {
            for (k in 0 until this.width) {
                result[i, j] += this[i, k] * other[k, j]
            }
        }
    }
    return result
}

data class Holes(val rows: List<Int>, val columns: List<Int>)
fun findHoles(matrix: Matrix<Int>): Holes {
    val rows = mutableListOf<Int>()
    val columns = mutableListOf<Int>()
    for (i in 0 until matrix.height) {
        var allZeroRow = true
        for (j in 0 until matrix.width) {
            if (matrix[i, j] == 1) {
                allZeroRow = false
                break
            }
        }
        if (allZeroRow) rows.add(i)
    }
    for (j in 0 until matrix.width) {
        var allZeroColumn = true
        for (i in 0 until matrix.height) {
            if (matrix[i, j] == 1) {
                allZeroColumn = false
                break
            }
        }
        if (allZeroColumn) columns.add(j)
    }
    return Holes(rows, columns)
}


fun canOpenLock(key: Matrix<Int>, lock: Matrix<Int>): Triple<Boolean, Int, Int> {
    // Проверяем все смещения ключа по вертикали/горизонтали
    for (i in 0..lock.height - key.height) {
        for (j in 0..lock.width - key.width) {
            var canOpen = true
   
            for (k in 0 until key.height) {
                for (l in 0 until key.width) {
                    val lockValue = lock[i + k, j + l]
                    val keyValue = key[k, l]
                    if (lockValue == 1 && keyValue == 0) {
                        canOpen = false
                        break
                    }
                    if (lockValue == 0 && keyValue == 1) {
                        canOpen = false
                        break
                    }
                }
                if (!canOpen) break
            }
            if (canOpen) {
                return Triple(true, i, j)
            }
        }
    }
    
    return Triple(false, 0, 0)
}
