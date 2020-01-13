package ir.easazade.cafebazaar;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * CafebazaarPlugin
 */
public class CafebazaarPlugin implements FlutterPlugin {

  private static final String CHANNEL_NAME = "cafebazaar";
  private MethodChannel channel;

  public static void registerWith(PluginRegistry.Registrar registrar) {
    final CafebazaarPlugin plugin = new CafebazaarPlugin();
    plugin.setupChannel(registrar.messenger(), registrar.context());
  }

  @Override
  public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
    setupChannel(binding.getFlutterEngine().getDartExecutor(), binding.getApplicationContext());
  }

  @Override
  public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
    teardownChannel();
  }

  private void setupChannel(BinaryMessenger messenger, Context context) {
    channel = new MethodChannel(messenger, CHANNEL_NAME);
    MethodCallHandlerImpl handler = new MethodCallHandlerImpl(context);
    channel.setMethodCallHandler(handler);
  }

  private void teardownChannel() {
    channel.setMethodCallHandler(null);
    channel = null;
  }

}
