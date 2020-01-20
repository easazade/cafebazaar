package ir.easazade.cafebazaar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class BazaarUtils {

  static void commentOnBazaar(Activity activity) {
    String bazzarPackageName = "com.farsitel.bazaar";
    String appPackageName = activity.getApplication().getPackageName();
    if (appPackageName != null) {
      try {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setData(Uri.parse("bazaar://details?id=$appPackageName"));
        intent.setPackage(bazzarPackageName);
        activity.startActivity(intent);
      } catch (Exception e) {
        Log.e("cafebazaar","error when : commenting on bazaar market",e);
      }
    }
  }

  static void goToAppPageOnBazaar(Activity activity) {
    try {
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse("bazaar://details?id=${activity.packageName}"));
      intent.setPackage("com.farsitel.bazaar");
      activity.startActivity(intent);
    } catch (Exception e) {
      Log.e("cafebazaar","error when : opening application page on bazaar",e);
    }
  }

}
