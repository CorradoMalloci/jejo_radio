import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ThemeService {
  static const String _key = 'theme_mode';
  final SharedPreferences _prefs;

  ThemeService(this._prefs);

  static Future<ThemeService> create() async {
    final prefs = await SharedPreferences.getInstance();
    return ThemeService(prefs);
  }

  ThemeMode getThemeMode() {
    final String? value = _prefs.getString(_key);
    switch (value) {
      case 'ThemeMode.light':
        return ThemeMode.light;
      case 'ThemeMode.dark':
        return ThemeMode.dark;
      default:
        return ThemeMode.system;
    }
  }

  Future<void> setThemeMode(ThemeMode mode) async {
    await _prefs.setString(_key, mode.toString());
  }
}

class ThemeNotifier extends ChangeNotifier {
  final ThemeService _themeService;
  late ThemeMode _themeMode;

  ThemeNotifier(this._themeService) {
    _themeMode = _themeService.getThemeMode();
  }

  ThemeMode get themeMode => _themeMode;

  void setThemeMode(ThemeMode mode) {
    _themeMode = mode;
    _themeService.setThemeMode(mode);
    notifyListeners();
  }
}
