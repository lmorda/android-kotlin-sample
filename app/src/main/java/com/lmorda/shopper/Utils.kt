import com.lmorda.shopper.MAX_PRICE
import java.math.RoundingMode

fun Double.getPriceText() =
    when {
        this < 0 -> "$0.00" // server bug
        this > MAX_PRICE -> "$0.00" // server bug
        else -> "$" + this.twoDecimalsFloor()
    }

fun Double.twoDecimalsFloor(): String =
    this.toBigDecimal().setScale(2, RoundingMode.FLOOR).toString()