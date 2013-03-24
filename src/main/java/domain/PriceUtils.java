package domain;

public final class PriceUtils {

    public static Long formatPriceToLong(String price) {
        String str = price.replace(',', '.');
        Double priceDoubleValue = Double.valueOf(str) * 100;
        return Math.round(priceDoubleValue);
    }

    public static String formatPriceLongToString(Long price) {
        if(price == null){
            return "0,00";
        }
        if (price < 0) {
            return "-1,00";
        }
        String strnumber = String.valueOf(price);
        if (strnumber.length() < 2) {
            return "0,0" + strnumber;
        }
        String euro = strnumber.substring(0, strnumber.length() - 2);
        String cent = strnumber.substring(strnumber.length() - 2);
        if (cent.length() == 1) {
            cent = cent + "0";
        }
        if (euro.length() == 0) {
            euro = "0";
        }
        return euro + "," + cent;
    }

}
