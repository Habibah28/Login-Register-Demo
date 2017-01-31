package thescone.loginregisterdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Habibah on 31-Jan-17.
 */
public class Register extends AsyncTask<String, Void, String> {
    RegisterUserClass ruc = new RegisterUserClass();

    public interface RegisterResponse {
        void processFinished(String output);
    }

    public RegisterResponse delegate = null;

    public Register(RegisterResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinished(s);
    }

    @Override
    protected String doInBackground(String... params) {

        // TODO: Send data to PHP
        /***
         * - The four strings (name, username, password and email) come from MainActivity
         * - The variable name for these four is intentionally same with column name in database and variable name in PHP,
         *      so that no silly mistakes will be made
         */

        HashMap<String, String> data = new HashMap<String,String>();
        data.put("name",params[0]);
        data.put("username",params[1]);
        data.put("password",params[2]);
        data.put("email",params[3]);

        String result = ruc.sendPostRequest(Config.REGISTER_URL,data);

        return result;
    }
}
