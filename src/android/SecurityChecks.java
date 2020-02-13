package cordova.plugin.securityChecks;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.provider.Settings;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.app.Activity;
import android.provider.Settings.Secure;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class SecurityChecks extends CordovaPlugin {
    Context mContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        mContext = cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("validate")) {
            this.validate(args, callbackContext);
            return true;
        }
        return false;
    }

    private void validate(JSONArray args, CallbackContext callback) {
        if (args != null) {
            try {
                // int p1 = Integer.parseInt(args.getJSONObject(0).getString("param1"));
                // int p2 = Integer.parseInt(args.getJSONObject(0).getString("param2"));
                // String imei;
                // // final TelephonyManager mTelephony = (TelephonyManager) mContext
                // // .getSystemService(Context.TELEPHONY_SERVICE);
                // // if (mTelephony.getDeviceId() != null) {
                // // imei = mTelephony.getDeviceId();
                // } else {
                imei = Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID);

                // }
                callback.success("" + imei);

            } catch (Exception ex) {
                callback.error("Something went wrong!");
            }

        } else {
            callback.error("Expected one non-empty string argument.");
        }
    }
}
