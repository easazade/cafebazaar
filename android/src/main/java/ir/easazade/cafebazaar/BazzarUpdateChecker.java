package ir.easazade.cafebazaar;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import com.farsitel.bazaar.IUpdateCheckService;
import java.util.List;

class BazzarUpdateChecker implements ServiceConnection {


  private IUpdateCheckService mUpdateCheckService;

  private Activity activity;

  public BazzarUpdateChecker(Activity activity) {
    this.activity = activity;
    Intent intent = new Intent("com.farsitel.bazaar.service.UpdateCheckService.BIND");
    intent.setPackage("com.farsitel.bazaar");
    Intent explicitIntent = convertImplicitIntentToExplicitIntent(activity.getPackageManager(), intent);
    activity.bindService(explicitIntent, this, Context.BIND_AUTO_CREATE);
  }

  @Override
  public void onServiceConnected(final ComponentName name, final IBinder service) {
    mUpdateCheckService = IUpdateCheckService.Stub.asInterface((IBinder) service);
  }

  void checkForNewUpdate(final Runnable onUpdateAvailable, final Runnable onNoUpdateAvailable) {

    if (mUpdateCheckService != null) {
      doCheck(onUpdateAvailable, onNoUpdateAvailable);
    } else {
      new Handler(activity.getMainLooper()).postDelayed(new Runnable() {
        @Override
        public void run() {
          doCheck(onUpdateAvailable, onNoUpdateAvailable);
        }
      }, 1000);
    }
  }

  private void doCheck(Runnable onUpdateAvailable, Runnable onNoUpdateAvailable) {
    try {
      long versionCode = mUpdateCheckService.getVersionCode(activity.getPackageName());
      if (versionCode != -1L) {
        onUpdateAvailable.run();
      } else {
        onNoUpdateAvailable.run();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onServiceDisconnected(final ComponentName name) {

  }

  void releaseService() {
    activity.unbindService(this);
  }

  private Intent convertImplicitIntentToExplicitIntent(PackageManager pm, Intent implicitIntent) {
    List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);
    if (resolveInfoList == null || resolveInfoList.size() != 1) {
      return null;
    }
    ResolveInfo serviceInfo = resolveInfoList.get(0);
    ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
    Intent explicitIntent = new Intent(implicitIntent);
    explicitIntent.setComponent(component);
    return explicitIntent;
  }

}