package ir.easazade.cafebazaar;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/**
 * CafebazaarPlugin
 */
public class CafebazaarPlugin implements FlutterPlugin, ActivityAware {

  private static final String CHANNEL_NAME = "cafebazaar";


  private MethodChannel channel;

  private Activity activity;

  private Application application;

  private CafeBazaarDelegate delegate;

  private FlutterPluginBinding flutterBinding;

  private ActivityPluginBinding activityBinding;


  public static void registerWith(PluginRegistry.Registrar registrar) {
    if (registrar.activity() == null) {
      // If a background flutter view tries to register the plugin, there will be no activity from the registrar,
      // we stop the registering process immediately because the ImagePicker requires an activity.
      return;
    }
    Activity activity = registrar.activity();
    Application application = null;
    if (registrar.context() != null) {
      application = (Application) (registrar.context().getApplicationContext());
    }
    CafebazaarPlugin plugin = new CafebazaarPlugin();
    plugin.setup(registrar.messenger(), application, activity, registrar, null);
  }

  @Override
  public void onAttachedToEngine(final FlutterPluginBinding binding) {
    flutterBinding = binding;
  }

  @Override
  public void onAttachedToActivity(final ActivityPluginBinding binding) {
    activityBinding = binding;
    setup(
        flutterBinding.getBinaryMessenger(),
        (Application) flutterBinding.getApplicationContext(),
        activityBinding.getActivity(),
        null,
        activityBinding
    );
  }

  @Override
  public void onDetachedFromActivity() {
    tearDown();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    onDetachedFromActivity();
  }

  @Override
  public void onReattachedToActivityForConfigChanges(final ActivityPluginBinding binding) {
    onAttachedToActivity(binding);
  }

  @Override
  public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
    teardownChannel();
  }

  private void setup(
      final BinaryMessenger messenger,
      final Application application,
      final Activity activity,
      final PluginRegistry.Registrar registrar,
      final ActivityPluginBinding activityBinding) {
    this.activity = activity;
    this.application = application;
    this.delegate = new CafeBazaarDelegate(activity);
    channel = new MethodChannel(messenger, "cafebazaar");
    channel.setMethodCallHandler(delegate);
//    observer = new LifeCycleObserver(activity);
    if (registrar != null) {
      Log.d("tagtag", "using V1");
      // V1 embedding setup for activity listeners.
//      application.registerActivityLifecycleCallbacks(observer);
      registrar.addActivityResultListener(delegate);
//      registrar.addRequestPermissionsResultListener(delegate);
    } else {
      Log.d("tagtag", "using V2");
      // V2 embedding setup for activity listeners.
      activityBinding.addActivityResultListener(delegate);
//      activityBinding.addRequestPermissionsResultListener(delegate);
//      lifecycle = FlutterLifecycleAdapter.getActivityLifecycle(activityBinding);
//      lifecycle.addObserver(observer);
    }
  }

  private void tearDown() {
    activityBinding.removeActivityResultListener(delegate);
//    activityBinding.removeRequestPermissionsResultListener(delegate);
    activityBinding = null;
//    lifecycle.removeObserver(observer);
//    lifecycle = null;
    delegate = null;
    channel.setMethodCallHandler(null);
    channel = null;
//    application.unregisterActivityLifecycleCallbacks(observer);
    application = null;
  }

  private void teardownChannel() {
    channel.setMethodCallHandler(null);
    channel = null;
  }

}
