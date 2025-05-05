class RadioStation {
  final String uuid;
  final String name;
  final String urlResolved;
  final String favicon;
  final String countryCode;
  final List<String> tags;

  const RadioStation({
    required this.uuid,
    required this.name,
    required this.urlResolved,
    required this.favicon,
    required this.countryCode,
    required this.tags,
  });

  factory RadioStation.fromJson(Map<String, dynamic> json) {
    return RadioStation(
      uuid: json['stationuuid'] ?? '',
      name: json['name'] ?? '',
      urlResolved: json['url_resolved'] ?? '',
      favicon: json['favicon'] ?? '',
      countryCode: json['countrycode'] ?? '',
      tags: (json['tags'] ?? '').toString().split(','),
    );
  }
}
