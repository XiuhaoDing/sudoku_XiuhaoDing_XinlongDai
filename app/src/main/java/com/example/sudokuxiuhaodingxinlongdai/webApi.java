package com.example.sudokuxiuhaodingxinlongdai;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * web api to get data.
 */
public class webApi {
    /**
     * Retrieve the document at the specified URL by sending a GET request;
     * return null if the request/connection fails.
     */
    public String sendGet(String urlString) {
        HttpURLConnection con = null;
        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    public static String mainA(int diff) {
        String FORMAT = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";
        int size = 9;
        int level = diff;
        String url = String.format(FORMAT, size, level);
        String response = new webApi().sendGet(url);
        return response;
    }
}