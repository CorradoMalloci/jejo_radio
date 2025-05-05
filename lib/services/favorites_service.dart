import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';
import '../models/radio_station_model.dart';

class FavoritesService {
  static const String _key = 'favorite_stations';
  final SharedPreferences _prefs;

  FavoritesService(this._prefs);

  static Future<FavoritesService> create() async {
    final prefs = await SharedPreferences.getInstance();
    return FavoritesService(prefs);
  }

  List<RadioStationModel> getFavorites() {
    final String? jsonString = _prefs.getString(_key);
    if (jsonString == null) return [];

    try {
      final List<dynamic> jsonList = json.decode(jsonString);
      return jsonList.map((json) => RadioStationModel.fromJson(json)).toList();
    } catch (e) {
      print('Error loading favorites: $e');
      return [];
    }
  }

  Future<bool> toggleFavorite(RadioStationModel station) async {
    final favorites = getFavorites();
    final isCurrentlyFavorite = favorites.any((s) => s.stationUuid == station.stationUuid);

    if (isCurrentlyFavorite) {
      favorites.removeWhere((s) => s.stationUuid == station.stationUuid);
    } else {
      favorites.add(station);
    }

    final jsonString = json.encode(favorites.map((s) => s.toJson()).toList());
    return await _prefs.setString(_key, jsonString);
  }

  bool isFavorite(String stationUuid) {
    return getFavorites().any((s) => s.stationUuid == stationUuid);
  }

  Future<void> removeFavorite(String stationUuid) async {
    final favorites = getFavorites();
    favorites.removeWhere((s) => s.stationUuid == stationUuid);
    final jsonString = json.encode(favorites.map((s) => s.toJson()).toList());
    await _prefs.setString(_key, jsonString);
  }

  Future<void> clearFavorites() async {
    await _prefs.remove(_key);
  }
}
