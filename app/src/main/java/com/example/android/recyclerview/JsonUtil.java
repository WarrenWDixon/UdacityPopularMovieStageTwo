package com.example.android.recyclerview;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static boolean dataRead = false;
    private static List<String> titlesArray      = new ArrayList<>();
    private static List<String> popularityArray  = new ArrayList<>();
    private static List<String> overviewArray    = new ArrayList<>();
    private static List<String> releaseDateArray = new ArrayList<>();
    private static List<String> imagePathArray   = new ArrayList<>();
    private static List<String> posterPathArray  = new ArrayList<>();
    private static List<String> idArray          = new ArrayList<>();

    private static List<String> favTitlesArray      = new ArrayList<>();
    private static List<String> favPopularityArray  = new ArrayList<>();
    private static List<String> favOverviewArray    = new ArrayList<>();
    private static List<String> favReleaseDateArray = new ArrayList<>();
    private static List<String> favImagePathArray   = new ArrayList<>();
    private static List<String> favPosterPathArray  = new ArrayList<>();
    private static List<String> favIdArray          = new ArrayList<>();


    public static void parseMovieJson(String json) {
        //Log.d("WWD", "in parseMovieJson input json is " + json);
        // first convert entire response to JSON object
        JSONObject jOBJ = null;
        try {
            jOBJ = new JSONObject(json);
        } catch (JSONException e) {
            Log.d("WWD", "exception on creating JSON object from json string");
            e.printStackTrace();
        }
       // Log.d("WWD", "created JSONObject form string " + jOBJ);

        // parse out results array of objects from response
        JSONArray jsonResults = new JSONArray();
        try {
            jsonResults  = jOBJ.getJSONArray("results");
        } catch (JSONException e) {
            Log.d("WWD", "got exception parsing results");
            e.printStackTrace();
        }

        //Log.d("WWD", "jsonResults is " + jsonResults);
        int len = jsonResults.length();
       // Log.d("WWD", "length of Results array is " + len);

        // create arrays and Strings to hold data
        List<String> results          = new ArrayList<>();

        String titleString       = "";
        String popularityString  = "";
        String overviewString    = "";
        String posterPathString  = "";
        String releaseDateString = "";
        String idString          = "";

        if (!titlesArray.isEmpty())
            titlesArray.clear();
        if (!popularityArray.isEmpty())
            popularityArray.clear();
        if (!overviewArray.isEmpty())
            overviewArray.clear();
        if (!posterPathArray.isEmpty())
            posterPathArray.clear();
        if (!releaseDateArray.isEmpty())
            releaseDateArray.clear();
        if (!idArray.isEmpty())
            idArray.clear();

        for (int i =0 ; i < len ;i++) {
            try {
                JSONObject result = jsonResults.getJSONObject(i);

                titleString = result.get("title").toString();
                titlesArray.add(titleString);

                popularityString = result.get("popularity").toString();
                popularityArray.add(popularityString);

                overviewString = result.get("overview").toString();
                overviewArray.add(overviewString);

                posterPathString = result.get("poster_path").toString();
                posterPathArray.add(posterPathString);

                releaseDateString = result.get("release_date").toString();
                releaseDateArray.add(releaseDateString);

                idString = result.get("id").toString();
                idArray.add(idString);

               // Log.d("WWD", "i = " + i + " title is " + titleString);
               // Log.d("WWD", "i = " + i + " popularity is " + popularityString);
              //  Log.d("WWD", "i = " + i + " overview is " + overviewString);
              //  Log.d("WWD", "i = " + i + " poster path is " + posterPathString);
              //  Log.d("WWD", "i = " + i + " release date is " + releaseDateString);
              //  Log.d("WWD", "i = " + i + " id is "  + idString);
                dataRead = true;

            } catch (JSONException e) {
                dataRead = false;
                Log.d("WWD", "error parsing results JSONArray");
                e.printStackTrace();
            }
        }
    }

    public static String parseDetailJson(String json) {
       // Log.d("WWD", "in parseDetailJson input json is " + json);
        // first convert entire response to JSON object
        JSONObject jOBJ = null;
        JSONObject videoObj = null;
        JSONArray jsonVideos = null;
        JSONObject result = null;
        try {
            jOBJ = new JSONObject(json);
        } catch (JSONException e) {
            Log.d("WWD", "exception on creating JSON object from json string");
            e.printStackTrace();
        }
       // Log.d("WWD", "created JSONObject form string " + jOBJ);

        try {
            videoObj = jOBJ.getJSONObject("videos");
           // Log.d("WWD", "videoObj is " + videoObj);
        } catch (JSONException e) {
            Log.d("WWD", "exception on parsing videos object");
            e.printStackTrace();
        }

        try {
            jsonVideos = new JSONArray();
            jsonVideos  = videoObj.getJSONArray("results");
            //String key = videoObj.get("key").toString();
          //  Log.d("WWD", "jsonVideos array is "  + jsonVideos);
        } catch (JSONException e) {
            Log.d("WWD", "exception on parsing results array");
            e.printStackTrace();
        }

        try {
            result = jsonVideos.getJSONObject(0);
        } catch (JSONException e) {
            Log.d("WWD", "exception on parsing result from results arrary");
            e.printStackTrace();
        }

        try {
            String key = result.get("key").toString();
            return key;
        }catch (JSONException e) {
            Log.d("WWD", "exception on parsing key  from result object");
            e.printStackTrace();
        }
        return null;
    }

    public static String parseReviewJson(String json) {
       // Log.d("WWD", "in parseReviewJson input json is " + json);
        // first convert entire response to JSON object
        JSONObject jOBJ = null;
        JSONArray jsonResults = null;
        JSONObject result = null;
       // Log.d("WWD", "do first try block in parseReviewJson");
        try {
            jOBJ = new JSONObject(json);
        } catch (JSONException e) {
            Log.d("WWD", "exception on creating JSON object from json string");
            e.printStackTrace();
        }
       // Log.d("WWD", "created JSONObject form string " + jOBJ);
       // Log.d("WWD", "do second try block in parseReviewJson");
        try {
            jsonResults = new JSONArray();
            jsonResults  = jOBJ.getJSONArray("results");
            //String key = videoObj.get("key").toString();
           // Log.d("WWD", "jsonVideos array is "  + jsonResults);
        } catch (JSONException e) {
            Log.d("WWD", "exception on parsing results array");
            e.printStackTrace();
        }

       // Log.d("WWD", "do third try block in parseReviewJson");
        try {
            result = jsonResults.getJSONObject(0);
        } catch (JSONException e) {
            Log.d("WWD", "exception on parsing result from results arrary");
            e.printStackTrace();
        }

       // Log.d("WWD", "do fourth try block in parseReviewJson");
        try {
            String url = result.get("url").toString();
            Log.d("WWD", " the parsed url is " + url);
            return url;
        }catch (JSONException e) {
            Log.d("WWD", "exception on parsing url from result object");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getDataRead() {
        return dataRead;
    }

    public static String getTitle(int index) {
        return titlesArray.get(index);
    }

    public static String getPopularity(int index) {
        return popularityArray.get(index);
    }

    public static String getOverview(int index) {
        return overviewArray.get(index);
    }

    public static String getReleaseDate(int index) {
        return releaseDateArray.get(index);
    }

    public static String getPosterPath(int index) {
        return posterPathArray.get(index);
    }

    public static String getID(int index) { return idArray.get(index); }

    public static void addFavTitle(String title) {
        favTitlesArray.add(title);
        Log.d("WWD","added title" + title);
    }

    public static void addFavPopularity(String popularity) {
        favPopularityArray.add(popularity);
        Log.d("WWD","added popularity" + popularity);

    }

    public static void addFavOverview(String overview) {
        favOverviewArray.add(overview);
        Log.d("WWD","added overview" + overview);
    }

    public static void addFavReleaseDate(String releaseDate){
        favReleaseDateArray.add(releaseDate);
        Log.d("WWD","added releaseDate" + releaseDate);
    }


    public static void addFavPosterPath(String posterPath) {
        favPosterPathArray.add(posterPath);
        Log.d("WWD","added posterPath" + posterPath);
    }

    public static void addFavId(String id) {
        favIdArray.add(id);
        Log.d("WWD","added id" + id);
    }

    public static void copyFavMovies() {
        if (!titlesArray.isEmpty())
            titlesArray.clear();
        if (!popularityArray.isEmpty())
            popularityArray.clear();
        if (!overviewArray.isEmpty())
            overviewArray.clear();
        if (!posterPathArray.isEmpty())
            posterPathArray.clear();
        if (!releaseDateArray.isEmpty())
            releaseDateArray.clear();
        if (!idArray.isEmpty())
            idArray.clear();
        int size = favIdArray.size();
        Log.d("WWD", "in copyFavMovies favIdArray size is " + size);
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                titlesArray.add(favIdArray.get(i));
                popularityArray.add(favPopularityArray.get(i));
                overviewArray.add(favOverviewArray.get(i));
                posterPathArray.add(favPosterPathArray.get(i));
                releaseDateArray.add(favReleaseDateArray.get(i));
                idArray.add(favIdArray.get(i));
            }
        }
        dataRead = true;
        Log.d("WWD", "in copy arrays titlesArray size is " + titlesArray.size());
    }

    public static int getFavArraySize() {
        return favIdArray.size();
    }

    public static void updateFavoriteMovies(List<Movie> movies) {
        int n = movies.size();
        Log.d("WWD", "in updateFavoriteMovies movies.size is " + n);
        if (!favTitlesArray.isEmpty())
            favTitlesArray.clear();
        if (!favPopularityArray.isEmpty())
            favPopularityArray.clear();
        if (!favOverviewArray.isEmpty())
            favOverviewArray.clear();
        if (!favPosterPathArray.isEmpty())
            favPosterPathArray.clear();
        if (!favReleaseDateArray.isEmpty())
            favReleaseDateArray.clear();
        if (!favIdArray.isEmpty())
            favIdArray.clear();

        for(int i = 0; i < n; i++) {
            Log.d("WWD", "i = " + i + " title is " + movies.get(i).getTitle());
            favTitlesArray.add(movies.get(i).getTitle());
            favPopularityArray.add(movies.get(i).getPopularity());
            favOverviewArray.add(movies.get(i).getOverview());
            favPosterPathArray.add(movies.get(i).getPoster());
            favReleaseDateArray.add(movies.get(i).getReleaseDate());
            favIdArray.add(movies.get(i).getMovieID());
        }
        Log.d("WWD", "at end fav Array size is " + titlesArray.size());
    }

}
