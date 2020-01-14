import 'dart:async';

import 'package:flutter/services.dart';

class Cafebazaar {
  static const MethodChannel _channel = const MethodChannel('cafebazaar');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> showToast() async {
    await _channel.invokeMethod("showToast");
  }

  static Future<bool> isUpdateAvailable() async {
    final bool hasUpdate = await _channel.invokeMethod("isUpdateAvailable");
    return hasUpdate;
  }

  static Future<void> goToAppPageOnBazaar() async{
    await _channel.invokeMethod("goToAppPageOnBazaar");
  }

  static Future<void> commentOnBazaar() async{
    await _channel.invokeMethod("commentOnBazaar");
  }

}
