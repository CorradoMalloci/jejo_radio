import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import '../models/radio_station_model.dart';
import '../services/radio_audio_handler.dart';

class PlayerControls extends StatelessWidget {
  final RadioAudioHandler audioHandler;
  final ScrollController? scrollController;
  final List<RadioStationModel> stations;

  const PlayerControls({
    super.key,
    required this.audioHandler,
    this.scrollController,
    this.stations = const [],
  });

  void _scrollToCurrentStation(RadioStationModel station) {
    if (scrollController == null) return;
    
    final index = stations.indexWhere((s) => s.stationUuid == station.stationUuid);
    if (index == -1) return;

    final itemHeight = 100.0; // Altezza approssimativa di una card
    final screenHeight = 600.0; // Altezza approssimativa dello schermo
    final offset = (index * itemHeight) - (screenHeight / 2) + (itemHeight / 2);

    scrollController?.animateTo(
      offset.clamp(0, scrollController!.position.maxScrollExtent),
      duration: const Duration(milliseconds: 500),
      curve: Curves.easeInOut,
    );
  }

  @override
  Widget build(BuildContext context) {
    return ValueListenableBuilder<RadioStationModel?>(
      valueListenable: audioHandler.currentStation,
      builder: (context, currentStation, _) {
        if (currentStation == null) return const SizedBox.shrink();

        return GestureDetector(
          onTap: () => _scrollToCurrentStation(currentStation),
          child: Container(
            decoration: BoxDecoration(
              color: Theme.of(context).cardColor,
              boxShadow: [
                BoxShadow(
                  color: Colors.black.withOpacity(0.1),
                  blurRadius: 8,
                  offset: const Offset(0, -2),
                ),
              ],
            ),
            child: SafeArea(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  // Progress Indicator
                  ValueListenableBuilder<bool>(
                    valueListenable: audioHandler.isPlaying,
                    builder: (context, isPlaying, _) {
                      return AnimatedContainer(
                        duration: const Duration(milliseconds: 300),
                        height: 2,
                        child: isPlaying
                          ? const LinearProgressIndicator()
                          : null,
                      );
                    },
                  ),
                  // Player Content
                  Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Row(
                      children: [
                        // Station Logo
                        if (currentStation.hasFavicon)
                          ClipRRect(
                            borderRadius: BorderRadius.circular(8),
                            child: CachedNetworkImage(
                              imageUrl: currentStation.favicon,
                              width: 50,
                              height: 50,
                              fit: BoxFit.contain,
                              placeholder: (_, __) => Container(
                                color: Colors.black.withOpacity(0.03),
                                child: const Icon(Icons.radio),
                              ),
                              errorWidget: (_, __, ___) => Container(
                                color: Colors.black.withOpacity(0.03),
                                child: const Icon(Icons.radio),
                              ),
                            ),
                          ),
                        const SizedBox(width: 16),
                        // Station Info
                        Expanded(
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            mainAxisSize: MainAxisSize.min,
                            children: [
                              Text(
                                currentStation.name,
                                style: Theme.of(context).textTheme.titleMedium,
                                maxLines: 1,
                                overflow: TextOverflow.ellipsis,
                              ),
                              const SizedBox(height: 4),
                              Text(
                                currentStation.location,
                                style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                                  color: Theme.of(context).textTheme.bodySmall?.color,
                                ),
                                maxLines: 1,
                                overflow: TextOverflow.ellipsis,
                              ),
                            ],
                          ),
                        ),
                        // Controls
                        ValueListenableBuilder<bool>(
                          valueListenable: audioHandler.isPlaying,
                          builder: (context, isPlaying, _) {
                            return Row(
                              mainAxisSize: MainAxisSize.min,
                              children: [
                                IconButton(
                                  icon: const Icon(Icons.stop),
                                  onPressed: () => audioHandler.stop(),
                                  iconSize: 32,
                                ),
                                IconButton(
                                  icon: Icon(isPlaying ? Icons.pause : Icons.play_arrow),
                                  onPressed: () => audioHandler.togglePlayback(currentStation),
                                  iconSize: 32,
                                ),
                              ],
                            );
                          },
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }
}
