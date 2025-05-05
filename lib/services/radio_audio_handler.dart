import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:just_audio/just_audio.dart';
import 'package:just_audio_background/just_audio_background.dart';
import '../models/radio_station_model.dart';

class RadioAudioHandler {
  final AudioPlayer _player = AudioPlayer();
  final ValueNotifier<bool> isPlaying = ValueNotifier<bool>(false);
  final ValueNotifier<RadioStationModel?> currentStation = ValueNotifier<RadioStationModel?>(null);

  static Future<void> init() async {
    await JustAudioBackground.init(
      androidNotificationChannelId: 'com.jejo.radio.channel.audio',
      androidNotificationChannelName: 'Je Jo Radio',
      androidNotificationChannelDescription: 'Controlli di riproduzione per Je Jo Radio',
      androidShowNotificationBadge: true,
      androidStopForegroundOnPause: false,
      notificationColor: const Color(0xFF3F51B5),
      androidNotificationIcon: 'mipmap/ic_launcher',
      androidNotificationClickStartsActivity: true,
    );
  }

  Future<void> play(RadioStationModel station) async {
    try {
      print("Avvio riproduzione stazione: ${station.name}");
      
      // Aggiorna immediatamente lo stato corrente
      currentStation.value = station;
      
      if (_player.playing) {
        await _player.stop();
      }
      
      final mediaItem = MediaItem(
        id: station.stationUuid,
        title: station.name,
        artist: station.location.isNotEmpty ? station.location : "Je Jo Radio",
        displayTitle: station.name,
        displaySubtitle: station.displayInfo,
        artUri: station.hasFavicon ? Uri.parse(station.favicon) : null,
        playable: true,
        album: 'Je Jo Radio',
        genre: station.tags.isNotEmpty ? station.tags.first : 'Radio',
      );
      
      final audioSource = AudioSource.uri(
        Uri.parse(station.streamUrl),
        tag: mediaItem,
      );
      
      await _player.setAudioSource(audioSource);
      await _player.play();
      isPlaying.value = true;
      
    } catch (e) {
      print('Errore durante la riproduzione: $e');
      isPlaying.value = false;
      // In caso di errore, mantieni la stazione corrente ma indica che non sta riproducendo
    }
  }

  Future<void> pause() async {
    if (_player.playing) {
      await _player.pause();
      isPlaying.value = false;
    }
  }
  
  Future<void> resume() async {
    if (!_player.playing && currentStation.value != null) {
      await _player.play();
      isPlaying.value = true;
    }
  }

  Future<void> stop() async {
    if (_player.playing) {
      await _player.stop();
    }
    currentStation.value = null;
    isPlaying.value = false;
  }
  
  Future<void> togglePlayback(RadioStationModel station) async {
    final currentStationValue = currentStation.value;
    
    if (currentStationValue != null && currentStationValue.stationUuid == station.stationUuid) {
      if (_player.playing) {
        await pause();
      } else {
        await resume();
      }
    } else {
      await play(station);
    }
  }

  bool isCurrentlyPlaying(String stationUuid) {
    return currentStation.value != null && 
           currentStation.value!.stationUuid == stationUuid && 
           _player.playing;
  }
  
  void dispose() {
    _player.dispose();
  }
}
