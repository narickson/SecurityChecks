package io.ionic.starter;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;


import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.Context;
import android.widget.Toast;

import android.content.Context;

public class SecurityChecks extends CordovaPlugin {
    // at the initialize function, we can configure the tools we want to use later, like the sensors
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        mContext = cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("SecurityPassFn".equals(action)) {
            SecurityPassFn(args, callbackContext);
            return true;
        }

        return false;
    }

    private void SecurityPassFn(JSONArray msg, CallbackContext callbackContext) {
        if (msg == null || msg.length() == 0) {
            callbackContext.error("Plugin did not work!");
        } else {
            Toast.makeText(webView.getContext(), "You are the man", Toast.LENGTH_LONG).show();
            PluginResult result = new PluginResult("Success", "You boss this shit!");
            callbackContext.sendPluginResult(result);
        }
    }
}
