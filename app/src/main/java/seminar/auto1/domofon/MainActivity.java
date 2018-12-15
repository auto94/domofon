package seminar.auto1.domofon;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    String nextCode;
    Integer nextCodeInt;
    Integer pseudoRandomSeed[] = {18, 9, 7, 46, 28, 7, 8, 4, 11, 23, 7, 24, 2, 50, 31, 4, 32, 23, 6, 6};
    List<Integer> pseudoRandom = new ArrayList<>();
    String lastAccessedSeed;
    Integer lastAccessedSeedInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pseudoRandom.add(18);
        pseudoRandom.add(9);
        pseudoRandom.add(7);
        pseudoRandom.add(46);
        pseudoRandom.add(28);
        pseudoRandom.add(7);
        pseudoRandom.add(8);
        pseudoRandom.add(4);
        pseudoRandom.add(11);
        pseudoRandom.add(23);
        pseudoRandom.add(7);
        pseudoRandom.add(24);
        pseudoRandom.add(2);
        pseudoRandom.add(50);
        pseudoRandom.add(31);
        pseudoRandom.add(4);
        pseudoRandom.add(32);
        pseudoRandom.add(23);
        pseudoRandom.add(6);
        pseudoRandom.add(6);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        final Context context = getApplicationContext();
        Button theButton = findViewById(R.id.buttonID);

        nextCode = sharedPrefsData.getNextCode(context);
        nextCodeInt = Integer.parseInt(nextCode);

        lastAccessedSeed = sharedPrefsData.getArrayPosition(context);
        lastAccessedSeedInt = Integer.parseInt(lastAccessedSeed);

        SimpleRNG theRNG = new SimpleRNG(context);


        for (int i = 0; i<50; i++) {
            System.out.println(theRNG.getNextHex());
        }


        theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputManager != null && getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }

                ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = connManager.getActiveNetworkInfo();


                if (netInfo != null && netInfo.isConnected()) {
                    new HttpRequestAsyncTask("192.168.0.102", "55", nextCode).execute();

                    nextCodeInt = nextCodeInt + pseudoRandom.get(lastAccessedSeedInt);
                    sharedPrefsData.saveNextCode(context, nextCodeInt.toString());
                    nextCode = sharedPrefsData.getNextCode(context);

                    if (lastAccessedSeedInt >= 19) {
                        sharedPrefsData.saveArrayPosition(context, "0");
                    }
                    else {
                        lastAccessedSeedInt = lastAccessedSeedInt + 1;
                        String tempLastSeed = lastAccessedSeedInt.toString();
                        sharedPrefsData.saveArrayPosition(context, tempLastSeed);
                    }

                }
                else {
                    //occurs when no data/wifi connection is available
                    Toast toast = Toast.makeText(getApplicationContext(), "No network", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }



            }
        });

        super.onResume();
    }

}
