import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/radio_station_model.dart';

class RadioApiService {
  static const String _baseUrl = 'https://de1.api.radio-browser.info/json/stations';
  static const int defaultLimit = 200;

  Future<List<RadioStationModel>> getStations({
    String? countryCode,
    String? state,
    String? searchTerm,
    int offset = 0,
    int limit = defaultLimit,
    String? orderBy = 'name',
  }) async {
    try {
      var queryParams = {
        'offset': offset.toString(),
        'limit': limit.toString(),
        'hidebroken': 'true',
        'order': orderBy ?? 'name',
      };

      if (countryCode != null) queryParams['countrycode'] = countryCode;
      if (state != null) queryParams['state'] = state;
      if (searchTerm != null && searchTerm.isNotEmpty) {
        queryParams['name'] = searchTerm;
        queryParams['nameExact'] = 'false';
      }

      final uri = Uri.parse(_baseUrl + '/search').replace(queryParameters: queryParams);

      final response = await http.get(uri, headers: {
        'User-Agent': 'JeJoRadio/1.0',
        'Content-Type': 'application/json',
      });

      if (response.statusCode != 200) {
        throw Exception('Failed to load stations: ${response.statusCode}');
      }

      final List<dynamic> rawData = jsonDecode(response.body);
      return rawData
          .where((station) => 
              station['url_resolved'] != null && 
              station['url_resolved'].toString().isNotEmpty)
          .map((json) => RadioStationModel.fromJson(json))
          .toList();
    } catch (e) {
      print('Error fetching stations: $e');
      return [];
    }
  }

  Future<Map<String, String>> getCountries() async {
    try {
      final response = await http.get(
        Uri.parse('https://de1.api.radio-browser.info/json/countries'),
        headers: {
          'User-Agent': 'JeJoRadio/1.0',
          'Content-Type': 'application/json',
        },
      );

      if (response.statusCode != 200) {
        throw Exception('Failed to load countries');
      }

      final List<dynamic> rawData = jsonDecode(response.body);
      final Map<String, String> countries = {};

      // Aggiungi prima l'Italia
      for (var country in rawData) {
        if (country['iso_3166_1'] == 'IT' && 
            country['name'] != null && 
            country['stationcount'] != null &&
            country['stationcount'] > 0) {
          countries['Italia'] = 'IT';
          break;
        }
      }

      // Aggiungi gli altri paesi
      for (var country in rawData) {
        if (country['name'] != null && 
            country['iso_3166_1'] != null && 
            country['stationcount'] != null &&
            country['stationcount'] > 0 &&
            country['iso_3166_1'] != 'IT') {
          countries[country['name']] = country['iso_3166_1'];
        }
      }

      return countries;
    } catch (e) {
      print('Error fetching countries: $e');
      return {};
    }
  }
}
