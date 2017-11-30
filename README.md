# HttpURLConnection-EdgarAPI

<h2>Description</h2>
Android Application uses HttpURLConnection with AsyncTask on MainActivity to parse JSON from Edgar API.
Financial Data are made available for viewing.
<p><p>

<center><div><img src="HttpURLConnection_screenshot.jpg" width="60%"></div></center>

<h2>HttpURLConnection</h2>
<p>HttpURLConnection establishes connection with API, and data on the web are accessible with Get method.  <br>
  <code>urlConnection = (HttpURLConnection) url.openConnection();
    urlConnection.setRequestMethod("GET");
    urlConnection.connect();</code>
  <p></p>
  <h2>Edgar API</h2>
  <p>Edgar API&trade; provides outlet for company financial data for educational, non commercial use. HttpURLConnection activity ought to be conducted in non-main thread, for it shall cause NetworkException. </p>
<img src="/EDGAR_API_img.png" style="width:40%"/>

  <h2>AsyncTask, doInBackground(), onPostExecute()</h2>
  <p>AsyncTask methods are doInBackground() and onPostExecute().
  <code>HttpURLConnection urlConnection = null;</br>BufferedReader reader = null;</br>String jsonStr = null;</code>
  <p></p>
  <p>InputStreamReader, StringBuffer processed jsonStr is parsed as Json. This program is configured to process Json output from v2-Core Financials YTD.  Return values resulting from unctions through doInBackground() gets passed to onPostExecute().</p>
  
  <h2>jsonParser</h2>
  <p>jsonParser constructs Json object from String. JsonObjects, JsonArrays, JsonElements comprise json tree.</p>
  <code>JsonParser parser = new JsonParser();</br>JsonObject o = parser.parse(jsonStr).getAsJsonObjet();</br>JsonObject result = o.getAsJsonObject("result");</br>JsonArray rows = result.getAsJsonArray("rows");</code>
