package com.example.android.recyclerview;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

   final static String POPULAR_MOVIE_URL =
            "https://api.themoviedb.org/3/movie/popular?api_key=44bb9f3b21602f274a1127bb251ab87d&language=en-US&page=1";

    final static String TOPRATED_MOVIE_URL =
            "https://api.themoviedb.org/3/movie/top_rated?api_key=44bb9f3b21602f274a1127bb251ab87d&language=en-US&page=1";


    private static boolean networkConnected;

    public static URL buildPopularUrl() {
        Uri builtUri = Uri.parse(POPULAR_MOVIE_URL).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildTopRatedUrl() {
        Uri builtUri = Uri.parse(TOPRATED_MOVIE_URL).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

        /**
         * This method returns the entire result from the HTTP response.
         *
         * @param url The URL to fetch the HTTP response from.
         * @return The contents of the HTTP response.
         * @throws IOException Related to network and stream reading
         */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Log.d("WWD", "in getResponseFromHttpUrl url is " + url);
        try {
            InputStream in = urlConnection.getInputStream();
            if (in == null) {
                networkConnected = false;
                Log.d("WWD", "network connection failed");
            }
            else {
                networkConnected = true;
                Log.d("WWD", "network connection worked");
            }

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                Log.d("WWD", "read data from Movie database");
                return scanner.next();
            } else {
                Log.d("WWD", "no data read from Movie database ");
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static boolean getNetworkConnected() {
        return networkConnected;
    }
}
