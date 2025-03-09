package dev.invest.utils;

import java.math.BigDecimal;
import lombok.experimental.UtilityClass;
import ru.tinkoff.piapi.contract.v1.MoneyValue;
import ru.tinkoff.piapi.contract.v1.Quotation;

@UtilityClass
public class MoneyUtils {

    public static BigDecimal toBigDecimal(Quotation quotation) {
        long units = quotation.getUnits();
        int nano = quotation.getNano();

        return new BigDecimal(units + "." + nano);
    }

    public static BigDecimal toBigDecimal(MoneyValue moneyValue) {
        if (moneyValue == null) {
            return null;
        }
        long units = moneyValue.getUnits();
        int nano = moneyValue.getNano();

        return new BigDecimal(units + "." + nano);
    }
}
