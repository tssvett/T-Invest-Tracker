package dev.invest.utils;

import lombok.experimental.UtilityClass;
import ru.tinkoff.piapi.contract.v1.BrandData;

@UtilityClass
public class BrandUtils {

    public static String toString(BrandData brandData) {
        if (brandData == null) {
            return null;
        }

        return brandData.toString();
    }
}
