import 'dart:async';

import 'package:flutter/services.dart';

class Cafebazaar {
  static const MethodChannel _channel = const MethodChannel('cafebazaar');

  static Future<bool> isUpdateAvailable() async {
    final bool hasUpdate = await _channel.invokeMethod("isUpdateAvailable");
    return hasUpdate;
  }

  static Future<void> goToAppPageOnBazaar() async {
    await _channel.invokeMethod("goToAppPageOnBazaar");
  }

  static Future<void> commentOnBazaar() async {
    await _channel.invokeMethod("commentOnBazaar");
  }
}
