package seminar.auto1.domofon;


import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpRequestAsyncTask extends AsyncTask<String, Integer, String>{
    private String ipAddress;
    private String portNumber;
    private String turnOn;

    public HttpRequestAsyncTask(String ipAddress, String portNumber, String turnOn) {
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        this.turnOn = turnOn;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        try {
            URI postReq = new URI("http://" + ipAddress+"/"+turnOn);

            URL postURL = new URL(postReq.toString());
            urlConnection = (HttpURLConnection) postURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                //buffer.append(line);
                System.out.println(line);
            }

            reader.close();
            urlConnection.disconnect();
            return line;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return "Door opening";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }



}