package ir.easazade.cafebazaar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;

public class CafeBazaarDelegate implements MethodCallHandler, PluginRegistry.ActivityResultListener {

  private Context activity;


  public CafeBazaarDelegate(Activity activity) {
    this.activity = activity;
    Log.d("tagtag", activity.toString());
  }

  @Override
  public boolean onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    return false;
  }

  @Override
  public void onMethodCall(@NonNull final MethodCall call, @NonNull final Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + activity.toString());
    } else if (call.method.equals("showToast")) {
      Toast.makeText(activity, "this is a toast form native code", Toast.LENGTH_SHORT).show();
    } else {
      result.notImplemented();
    }
  }
}
