package expiry.cbk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

    @Test
//    pay_10000_won
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(LocalDate.of(2019, 3, 1),
                10_000,
                LocalDate.of(2019, 4, 1));

        assertExpiryDate(LocalDate.of(2019, 5, 5),
                10_000,
                LocalDate.of(2019, 6, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(LocalDate.of(2019, 1, 31),
                10_000,
                LocalDate.of(2019, 2, 28));

        assertExpiryDate(LocalDate.of(2019, 5, 31),
                10_000,
                LocalDate.of(2019, 6, 30));

        assertExpiryDate(LocalDate.of(2020, 1, 31),
                10_000,
                LocalDate.of(2020, 2, 29));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate realExpiryDate = calculator.calculateExpiryDate(billingDate, payAmount);

        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
