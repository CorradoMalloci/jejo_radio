import 'package:flutter/foundation.dart';

/// Model class for a radio station
class RadioStationModel {
  /// Unique identifier for the station
  final String stationUuid;
  
  /// Display name of the station
  final String name;
  
  /// URL for the audio stream
  final String urlResolved;
  
  /// URL to the station's logo/favicon
  final String favicon;
  
  /// Country code (e.g., 'IT', 'US')
  final String countryCode;
  
  /// List of tags describing the station content
  final List<String> tags;
  
  /// Whether the station is marked as a favorite
  final bool isFavorite;
  
  /// Country name (optional)
  final String? country;
  
  /// State or region (optional)
  final String? state;
  
  /// Vote count (optional)
  final int votes;
  
  /// Creates a new RadioStationModel instance
  const RadioStationModel({
    required this.stationUuid,
    required this.name,
    required this.urlResolved,
    required this.favicon,
    required this.countryCode,
    required this.tags,
    this.isFavorite = false,
    this.country,
    this.state,
    this.votes = 0,
  });

  /// Creates a copy of this model with modified properties
  RadioStationModel copyWith({
    String? stationUuid,
    String? name,
    String? urlResolved,
    String? favicon,
    String? countryCode,
    List<String>? tags,
    bool? isFavorite,
    String? country,
    String? state,
    int? votes,
  }) {
    return RadioStationModel(
      stationUuid: stationUuid ?? this.stationUuid,
      name: name ?? this.name,
      urlResolved: urlResolved ?? this.urlResolved,
      favicon: favicon ?? this.favicon,
      countryCode: countryCode ?? this.countryCode,
      tags: tags ?? this.tags,
      isFavorite: isFavorite ?? this.isFavorite,
      country: country ?? this.country,
      state: state ?? this.state,
      votes: votes ?? this.votes,
    );
  }

  /// Creates a RadioStationModel from JSON data
  factory RadioStationModel.fromJson(Map<String, dynamic> json) {
    return RadioStationModel(
      stationUuid: json['stationuuid'] ?? '',
      name: json['name'] ?? '',
      urlResolved: json['url_resolved'] ?? json['url'] ?? '',
      favicon: json['favicon'] ?? '',
      countryCode: json['countrycode'] ?? '',
      tags: _parseTagsFromJson(json['tags']),
      isFavorite: json['isFavorite'] ?? false,
      country: json['country'],
      state: json['state'],
      votes: json['votes'] != null ? int.tryParse(json['votes'].toString()) ?? 0 : 0,
    );
  }
  
  /// Parse tags from various formats in the API response
  static List<String> _parseTagsFromJson(dynamic tagsData) {
    if (tagsData == null) return [];
    
    final String tagsStr = tagsData.toString();
    if (tagsStr.isEmpty) return [];
    
    return tagsStr
        .split(',')
        .map((tag) => tag.trim())
        .where((tag) => tag.isNotEmpty)
        .toList();
  }

  /// Converts this model to a JSON map
  Map<String, dynamic> toJson() {
    return {
      'stationuuid': stationUuid,
      'name': name,
      'url_resolved': urlResolved,
      'favicon': favicon,
      'countrycode': countryCode,
      'tags': tags.join(','),
      'isFavorite': isFavorite,
      'country': country,
      'state': state,
      'votes': votes,
    };
  }
  
  /// Gets a formatted location string
  String get location {
    final List<String> parts = [];
    
    if (country != null && country!.isNotEmpty) {
      parts.add(country!);
    } else if (countryCode.isNotEmpty) {
      parts.add(countryCode);
    }
    
    if (state != null && state!.isNotEmpty) {
      // Only add state if it's different from country
      if (country == null || !state!.toLowerCase().contains(country!.toLowerCase())) {
        parts.add(state!);
      }
    }
    
    return parts.join(', ');
  }
  
  /// Gets the main tags as a formatted string
  String get mainTags {
    return tags.take(3).join(' • ');
  }
  
  /// Gets a display string that combines location and tags
  String get displayInfo {
    final loc = location;
    final tagStr = mainTags;
    
    if (loc.isNotEmpty && tagStr.isNotEmpty) {
      return '$loc - $tagStr';
    } else if (loc.isNotEmpty) {
      return loc;
    } else if (tagStr.isNotEmpty) {
      return tagStr;
    } else {
      return 'Radio Station';
    }
  }
  
  /// Returns whether the favicon URL is valid
  bool get hasFavicon => favicon.isNotEmpty && favicon.startsWith('http');
  
  /// Gets a fallback URL if the main URL is empty
  String get streamUrl => urlResolved.isNotEmpty ? urlResolved : '';
  
  @override
  bool operator ==(Object other) {
    if (identical(this, other)) return true;
    
    return other is RadioStationModel &&
        other.stationUuid == stationUuid;
  }
  
  @override
  int get hashCode => stationUuid.hashCode;
  
  @override
  String toString() => 'RadioStation(name: $name, country: $countryCode)';
}
