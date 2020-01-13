package ir.easazade.cafebazaar;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

public class MethodCallHandlerImpl implements MethodCallHandler {

  private Context context;


  public MethodCallHandlerImpl(final Context context) {
    this.context = context;
  }

  @Override
  public void onMethodCall(@NonNull final MethodCall call, @NonNull final Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("showToast")) {
      Toast.makeText(context, "this is a toast form native code", Toast.LENGTH_SHORT).show();
    } else {
      result.notImplemented();
    }
  }
}
