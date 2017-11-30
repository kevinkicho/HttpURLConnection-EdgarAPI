# HttpURLConnection-EdgarAPI

<h2>Description</h2>
Android Application uses HttpURLConnection with AsyncTask on MainActivity to parse JSON from Edgar API.
Financial Data are made available for viewing.
<p><p>

<center><div><img src="HttpURLConnection_screenshot.jpg" width="60%"></div></center>

<h2>Implementation</h2>
<p>HttpURLConnection establishes connection with API, and data on the web are accessible with Get method.  Edgar API&trade; provides outlet for company financial data for educational, non commercial use. HttpURLConnection activity ought to be conducted in non-main thread, for it shall cause NetworkException. AsyncTask methods are doInBackground() and onPostExecute(). InputStreamReader, StringBuffer processed jsonStr is parsed as Json. This program is configured to process Json output from v2-Core Financials YTD.  Return values resulting from unctions through doInBackground() gets passed to onPostExecute(). ListView Adapter declared within onPostExecute puts an adapter, making data accessible on screen.</p>
<p></p>
