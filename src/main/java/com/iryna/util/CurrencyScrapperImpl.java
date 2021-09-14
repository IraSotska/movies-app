package com.iryna.util;

import com.iryna.entity.Currency;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.time.LocalDate;

@Component
public class CurrencyScrapperImpl implements CurrencyScrapper {
    @Override
    public double getActualPrice(Currency currency) {
        OkHttpClient client = getUnsafeOkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("bank.gov.ua")
                .addPathSegment("NBUStatService/v1/statdirectory/exchange")
                .addQueryParameter("valcode", currency.name())
                .addQueryParameter("date", getCurrentDate())
                .addQueryParameter("json", "")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return getCurrencyPriceFromJsonResponse(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected double getCurrencyPriceFromJsonResponse(String json) {

        int start = json.indexOf("\"rate\":") + 7;
        StringBuilder stringBuilder = new StringBuilder();
        char currentChar = json.charAt(start);
        while (currentChar != ',') {
            stringBuilder.append(currentChar);
            start++;
            currentChar = json.charAt(start);
        }
        return Double.parseDouble(stringBuilder.toString());
    }

    private String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(localDate.getYear());
        stringBuffer.append(formatDateToTwoDigits(localDate.getMonthValue()));
        stringBuffer.append(formatDateToTwoDigits(localDate.getDayOfMonth()));
        return stringBuffer.toString();
    }

    private String formatDateToTwoDigits(int date) {
        String dateToFormat = String.valueOf(date);
        if (dateToFormat.length() == 1) {
            return "0" + dateToFormat;
        }
        return dateToFormat;
    }

    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
