# HttpURLConnection-EdgarAPI

<h2>Description</h2>
Android Application uses HttpURLConnection with AsyncTask on MainActivity to parse JSON from Edgar API.
Financial Data are made available for viewing.
<p><p>

<center><div><img src="HttpURLConnection_screenshot.jpg" width="60%"></div></center>

<h2>HttpURLConnection</h2>
<p>HttpURLConnection establishes connection with API, and data on the web are accessible with Get method.  <br>
  <code>urlConnection = (HttpURLConnection) url.openConnection();</code><br>
   <code>urlConnection.setRequestMethod("GET");</code><br>
    <code>urlConnection.connect();</code><br>
  <p></p>
  <h2>Edgar API</h2>
  <p>Edgar API&trade; provides outlet for company financial data for educational, non commercial use. HttpURLConnection activity ought to be conducted in non-main thread, for it shall cause NetworkException. </p>
<img src="EDGAR_API_img.PNG" style="width:40%"/>

  <h2>AsyncTask, doInBackground(), onPostExecute()</h2>
  <p>AsyncTask methods are comprised of doInBackground() and onPostExecute(). URLConnection is established with <code>.setRequestMethod("GET")</code>. This program is configured to process Json output from "v2-Core Financials YTD".  Upon Connection, InputStreamReader, StringBuffer process data and output as String.</p>
  <p></p>
  <p>Processed jsonStr is parsed as Json. Return values resulting from actions through doInBackground() gets passed to onPostExecute().</p>
  
  <h2>jsonParser</h2>
  <p>jsonParser constructs Json object from String. JsonObjects, JsonArrays, JsonElements comprise json tree.</p>
  <code>JsonParser parser = new JsonParser();</code><br>
  <code>JsonObject o = parser.parse(jsonStr).getAsJsonObjet();</code><br>
  <code>JsonObject result = o.getAsJsonObject("result");</code></br>
  <code>JsonArray rows = result.getAsJsonArray("rows");</code><br>
