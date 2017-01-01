package com.journaldev.youplusmegaevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;




public class RootActivity extends Activity {

  private SessionManager sessionManager;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = getApplicationContext();
        sessionManager = new SessionManager(getApplicationContext());
        finish();
        // sessionManager  = new SessionManager();
       /* OAuthClient.initiat(this);
        Url.init(getResources());
        oAuthClient = OAuthClient.OAuthClient();*/
      /*  sessionManager=SessionManager.SessionManager();*/
        AsyncTask at = new AsyncTask<Object, Boolean ,Boolean>(){
            @Override
            protected Boolean doInBackground(Object... params) {
               // SessionManager client = new SessionManager();
                Log.d(RootActivity.class.getSimpleName(), "before ====");
                SessionManager client = (SessionManager) params[0];
                Log.d(RootActivity.class.getSimpleName(), "before ====" + client.checkLogin());


                if(client.checkLogin())
                return true;
                return false;

            }


            @Override
            protected void onPostExecute(Boolean s) {
                finish();
                if(s){
                    Log.d(RootActivity.class.getSimpleName(), "Root====" + s);
                    Intent homeIntene = new Intent(context,PromoterPageActivit.class);

                    startActivity(homeIntene);
                }else {
                   /* Intent loginIntene = new Intent(context,Login.class);

                    startActivity(loginIntene);*/
                    finish();
                }
                super.onPostExecute(s);
                finish();
            }
        };
        at.execute(sessionManager);
        finish();
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_root, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
