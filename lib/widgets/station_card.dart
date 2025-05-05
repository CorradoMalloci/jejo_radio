import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import '../models/radio_station_model.dart';

class StationCard extends StatelessWidget {
  final RadioStationModel station;
  final bool isPlaying;
  final VoidCallback onPlayTap;
  final bool isFavorite;
  final VoidCallback onFavoriteTap;

  const StationCard({
    super.key,
    required this.station,
    required this.isPlaying,
    required this.onPlayTap,
    required this.isFavorite,
    required this.onFavoriteTap,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      elevation: isPlaying ? 4 : 1,
      child: InkWell(
        onTap: onPlayTap,
        borderRadius: BorderRadius.circular(16),
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Row(
            children: [
              _buildStationLogo(context),
              const SizedBox(width: 16),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: [
                        Expanded(
                          child: Text(
                            station.name,
                            style: Theme.of(context).textTheme.titleMedium?.copyWith(
                              fontWeight: FontWeight.w500,
                              letterSpacing: 0.5,
                            ),
                            maxLines: 1,
                            overflow: TextOverflow.ellipsis,
                          ),
                        ),
                        IconButton(
                          icon: Icon(
                            isFavorite ? Icons.favorite : Icons.favorite_outline,
                            size: 24,
                            color: Colors.black,
                          ),
                          onPressed: onFavoriteTap,
                          padding: EdgeInsets.zero,
                        ),
                      ],
                    ),
                    const SizedBox(height: 4),
                    Text(
                      station.displayInfo,
                      style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                        color: Colors.grey[600],
                        letterSpacing: 0.3,
                      ),
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                    ),
                    if (isPlaying) const SizedBox(height: 8),
                    if (isPlaying)
                      Row(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          Icon(
                            Icons.graphic_eq,
                            size: 16,
                            color: Colors.black,
                          ),
                          const SizedBox(width: 6),
                          Text(
                            'In riproduzione',
                            style: TextStyle(
                              color: Colors.black,
                              fontSize: 12,
                              fontWeight: FontWeight.w500,
                              letterSpacing: 0.5,
                            ),
                          ),
                        ],
                      ),
                  ],
                ),
              ),
              const SizedBox(width: 12),
              // Play/Stop button
              Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Icon(
                    isPlaying ? Icons.stop : Icons.play_arrow,
                    color: Colors.black,
                    size: 32,
                  ),
                  const SizedBox(height: 4),
                  Text(
                    isPlaying ? 'Stop' : 'Play',
                    style: const TextStyle(
                      color: Colors.black,
                      fontSize: 12,
                      fontWeight: FontWeight.w500,
                      letterSpacing: 0.5,
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildStationLogo(BuildContext context) {
    return SizedBox(
      width: 64,
      height: 64,
      child: ClipRRect(
        borderRadius: BorderRadius.circular(8),
        child: station.hasFavicon
          ? CachedNetworkImage(
              imageUrl: station.favicon,
              fit: BoxFit.contain,
              placeholder: (_, __) => _buildPlaceholder(context),
              errorWidget: (_, __, ___) => _buildPlaceholder(context),
              fadeInDuration: const Duration(milliseconds: 300),
              memCacheWidth: 128,
              memCacheHeight: 128,
              maxWidthDiskCache: 128,
              maxHeightDiskCache: 128,
            )
          : _buildPlaceholder(context),
      ),
    );
  }

  Widget _buildPlaceholder(BuildContext context) {
    return Container(
      color: Colors.black.withOpacity(0.03),
      child: Center(
        child: Icon(
          Icons.radio,
          size: 24,
          color: Colors.black.withOpacity(0.2),
        ),
      ),
    );
  }
}
