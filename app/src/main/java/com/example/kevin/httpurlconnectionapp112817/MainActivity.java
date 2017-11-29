package com.example.kevin.httpurlconnectionapp112817;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    Button btn;
    ListView lv;
    EditText editText;

    //Please change your API_KEY at Strings.xml
    //Edgar API TM url: developer.edgar-online.com/docs
    String API_KEY = getString(R.string.API_KEY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);

        lv = (ListView)findViewById(R.id.list_view);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new FetchJsonData().execute();
            }
        });
    }

    private class FetchJsonData extends AsyncTask<Void, Void, ArrayList<ArrayList<String>>> {

        @Override
        protected ArrayList<ArrayList<String>> doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonStr = null;

            try {
                editText = (EditText)findViewById(R.id.edit_text);
                String stock_symbol = editText.getText().toString();
                URL url = new URL("http://edgaronline.api.mashery.com/v2/corefinancials/ytd?fields=BalanceSheetConsolidated&primarysymbols=" + stock_symbol + "&activecompanies=false&deleted=false&sortby=primarysymbol+asc&debug=false&appkey=" + API_KEY);

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();

                JsonParser parser = new JsonParser();
                JsonObject o = parser.parse(jsonStr).getAsJsonObject();

                //Result, rows
                JsonObject result = o.getAsJsonObject("result");
                JsonArray rows = result.getAsJsonArray("rows");

                //First Row
                JsonElement jsonElement = rows.get(0);
                String temp = jsonElement.toString();

                //Value Set: "values"
                JsonObject o1 = parser.parse(temp).getAsJsonObject();
                JsonArray array1 = o1.getAsJsonArray("values");
                String array1_size = String.valueOf(array1.size());
                JsonElement jElement1 = array1.get(1);

                //LV size
                String size = String.valueOf(array1.size());
                //String[] result_array = new String[]{size, field, value};

                ArrayList<ArrayList<String>> result_array = new ArrayList<ArrayList<String>>();
                ArrayList<String> temp_result_array = new ArrayList<String>();

                //temp_result_array.add(size);
                //temp_result_array.add(size);
                //result_array.add(temp_result_array);
                for (int i =0; i<array1.size(); i++){
                    temp_result_array = new ArrayList<String>();

                    JsonElement temp_element = array1.get(i);
                    String temp_element_str = temp_element.toString();
                    String temp_value = temp_element_str.substring(temp_element_str.lastIndexOf(":")+1,temp_element_str.length()-1);
                    String temp_field = temp_element_str.substring(temp_element_str.indexOf(":")+2,temp_element_str.indexOf(",")-1);

                    temp_result_array.add(temp_field);
                    temp_result_array.add(temp_value);

                    result_array.add(temp_result_array);
                }

                return result_array;
            } catch (IOException e) {
                Log.e(TAG, "Error ", e);

                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<String>> s) {
            super.onPostExecute(s);

            ArrayList<String> ss = new ArrayList<String>();

            for (int i=0; i<s.size(); i++){
                ss.add(s.get(i).get(0)+" " +s.get(i).get(1));
            }

            LvArrayAdapter adapter = new LvArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, ss);
            lv.setAdapter(adapter);

            Log.i("json", s.get(0).get(0));
        }
    }
}