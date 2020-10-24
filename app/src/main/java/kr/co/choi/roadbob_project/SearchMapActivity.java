package kr.co.choi.roadbob_project;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchMapActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addr);
    }

    protected boolean isNetworkAvailable(){
        boolean available = false;
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isAvailable())available=true;
        return available;
    }

    private String converUrl(String addr) throws IOException {
        String s = null;
        String strUrl = "https://www.leelab.co.kr/api/geocode.php";
        byte[] buffer = new byte[1000];
        InputStream iStream = null;

        try{
            URL url = new URL(strUrl);
            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
            httpConn.setRequestMethod("POST");
            OutputStream outputStream = httpConn.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("addr="+addr);
            bufferedWriter.flush();

            iStream = httpConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(iStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            StringBuffer readTextBuf = new StringBuffer();
            while(line != null){
                readTextBuf.append(line);
                line = bufferedReader.readLine();
            }

            httpConn.disconnect();
            s=readTextBuf.toString();
        }catch (Exception e){
            Log.d("Exception convert", e.toString());
        }finally {
            iStream.close();
        }
        return s;
    }

    private class ConvertTask extends AsyncTask<String, Integer, String> {
        String s = null;

        @Override
        protected String doInBackground(String...addr){
            try{
                Log.d("Background Task",addr.toString());
                s = converUrl(addr[0]);
            }catch (Exception e){
                Log.d("Background Task", addr.toString());
            }
            return s;
        }
        @Override
        protected void onPostExecute(String result){
            Toast.makeText(getBaseContext(),"Web page converted successfully",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
