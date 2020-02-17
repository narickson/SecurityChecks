package cordova.plugin.securityChecks;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        if (action.equals("checkEmulator")) {
            this.CheckEmulator(args, callbackContext);
            return true;
        }
        
        if (action.equals("checkDebuggable")) {
            this.CheckDebuggable(args, callbackContext);
            return true;
        }
        
        if (action.equals("checkDownloadSource")) {
            this.CheckDownloadSource(args, callbackContext);
            return true;
        }
        
                if (action.equals("checkRoot")) {
            this.CheckRoot(args, callbackContext);
            return true;
        }
        return false;
    }
    
     private void CheckEmulator(JSONArray args, CallbackContext callback) {
         try{
        boolean isEmulator = (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator");
         callback.success(String.valueOf(isEmulator));
             } catch(Exception ex) {
            callback.error("An error occured");
        }
    }
    
    public void CheckDownloadSource(JSONArray args, CallbackContext callback) {
         try {
        // A list with valid installers package name
        List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));

        // The package name of the app that has installed your app
        final String installer = mContext.getPackageManager().getInstallerPackageName(mContext.getPackageName());

        // true if your app has been downloaded from Play Store
        boolean isFromPlayStore = installer != null && validInstallers.contains(installer);
            callback.success(String.valueOf(isFromPlayStore));
             } catch(Exception ex) {
            callback.error("An error occured");
        }
    }
    
    public void CheckDebuggable(JSONArray args, CallbackContext callback) {
        try{
        boolean debuggable =  ( 0 != ( mContext.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE ) );
            callback.success(String.valueOf(debuggable));
        } catch(Exception ex) {
            callback.error("An error occured");
        }
    }
    
      private void CheckRoot(JSONArray args, CallbackContext callback) {
         Process process = null;
   try {
      process = Runtime.getRuntime().exec("su");
                   callback.success("Rooted");
   } catch (Exception e) {
            callback.success("NotRooted");
   } finally {
      if (process != null) {
         try {
            process.destroy();
         } catch (Exception e) { }
      }
   }
    }

    private void validate(JSONArray args, CallbackContext callback) {
      
    }
}
