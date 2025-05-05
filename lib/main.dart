import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:provider/provider.dart';
import 'models/radio_station_model.dart';
import 'services/radio_audio_handler.dart';
import 'services/api_service.dart';
import 'services/favorites_service.dart';
import 'services/theme_service.dart';
import 'widgets/player_controls.dart';
import 'widgets/search_bar.dart';
import 'widgets/station_card.dart';
import 'widgets/modern_app_bar.dart';
import 'widgets/theme_selector.dart';
import 'theme/app_theme.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await RadioAudioHandler.init();
  
  final themeService = await ThemeService.create();
  final favoritesService = await FavoritesService.create();
  
  SystemChrome.setSystemUIOverlayStyle(
    const SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
      statusBarIconBrightness: Brightness.dark,
      statusBarBrightness: Brightness.light,
    ),
  );
  
  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(
          create: (_) => ThemeNotifier(themeService),
        ),
      ],
      child: RadioApp(favoritesService: favoritesService),
    ),
  );
}

class RadioApp extends StatelessWidget {
  final FavoritesService favoritesService;

  const RadioApp({
    super.key,
    required this.favoritesService,
  });

  @override
  Widget build(BuildContext context) {
    return Consumer<ThemeNotifier>(
      builder: (context, themeNotifier, _) {
        return MaterialApp(
          title: 'Je Jo Radio',
          themeMode: themeNotifier.themeMode,
          theme: AppTheme.light(),
          darkTheme: AppTheme.dark(),
          home: HomeScreen(
            favoritesService: favoritesService,
            themeNotifier: themeNotifier,
          ),
        );
      },
    );
  }
}

class HomeScreen extends StatefulWidget {
  final FavoritesService favoritesService;
  final ThemeNotifier themeNotifier;

  const HomeScreen({
    super.key,
    required this.favoritesService,
    required this.themeNotifier,
  });

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final RadioAudioHandler audioHandler = RadioAudioHandler();
  final RadioApiService apiService = RadioApiService();
  final ScrollController _scrollController = ScrollController();
  final TextEditingController _searchController = TextEditingController();
  
  List<RadioStationModel> stations = [];
  List<RadioStationModel> favorites = [];
  String? country = 'IT';
  String? state;
  bool loading = false;
  bool hasMoreStations = true;
  int currentPage = 0;
  Map<String, String> countries = {};
  String searchQuery = '';
  bool isExiting = false;
  bool showingFavorites = false;

  @override
  void initState() {
    super.initState();
    _scrollController.addListener(_onScroll);
    _loadInitialData();
  }

  Future<void> _loadInitialData() async {
    await _loadCountries();
    await _loadFavorites();
    await loadStations();
  }

  Future<void> _loadFavorites() async {
    setState(() {
      favorites = widget.favoritesService.getFavorites();
    });
  }

  @override
  void dispose() {
    _scrollController.dispose();
    _searchController.dispose();
    audioHandler.dispose();
    super.dispose();
  }

  Future<void> _loadCountries() async {
    final loadedCountries = await apiService.getCountries();
    if (mounted) {
      setState(() {
        countries = loadedCountries;
      });
    }
  }

  void _onScroll() {
    if (_scrollController.position.pixels >= _scrollController.position.maxScrollExtent - 500) {
      if (!loading && hasMoreStations && !showingFavorites) {
        loadStations(loadMore: true);
      }
    }
  }

  Future<void> loadStations({bool loadMore = false}) async {
    if (loading || showingFavorites) return;
    
    if (!loadMore) {
      setState(() {
        stations = [];
        currentPage = 0;
        hasMoreStations = true;
      });
    }

    setState(() => loading = true);

    try {
      final newStations = await apiService.getStations(
        countryCode: country,
        state: state,
        offset: currentPage * RadioApiService.defaultLimit,
        searchTerm: searchQuery.isNotEmpty ? searchQuery : null,
      );

      if (mounted) {
        setState(() {
          if (loadMore) {
            stations.addAll(newStations);
          } else {
            stations = newStations;
          }
          hasMoreStations = newStations.length == RadioApiService.defaultLimit;
          currentPage++;
          loading = false;
        });
      }
    } catch (e) {
      if (mounted) {
        setState(() => loading = false);
      }
    }
  }

  Future<void> _onSearch(String query) async {
    searchQuery = query;
    showingFavorites = false;
    loadStations();
  }

  void _onClearSearch() {
    searchQuery = '';
    showingFavorites = false;
    loadStations();
  }

  Future<void> _toggleFavorite(RadioStationModel station) async {
    await widget.favoritesService.toggleFavorite(station);
    await _loadFavorites();
    if (showingFavorites) {
      setState(() {});
    }
  }

  void _toggleFavoritesView() {
    setState(() {
      showingFavorites = !showingFavorites;
      if (!showingFavorites) {
        loadStations();
      }
    });
  }

  Future<bool> _onWillPop() async {
    if (isExiting) return true;
    
    isExiting = true;
    bool shouldExit = await showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Chiudi applicazione'),
        content: const Text('Vuoi chiudere l\'applicazione?'),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(context).pop(false),
            child: const Text('No'),
          ),
          TextButton(
            onPressed: () {
              audioHandler.stop();
              Navigator.of(context).pop(true);
            },
            child: const Text('Sì'),
          ),
        ],
      ),
    ) ?? false;
    
    isExiting = false;
    
    if (shouldExit) {
      await SystemNavigator.pop();
    }
    
    return false;
  }

  @override
  Widget build(BuildContext context) {
    final displayStations = showingFavorites ? favorites : stations;

    return WillPopScope(
      onWillPop: _onWillPop,
      child: Scaffold(
        appBar: ModernAppBar(
          country: country,
          state: state,
          countries: countries,
          onCountrySelected: (value) {
            setState(() {
              country = value;
              state = null;
              showingFavorites = false;
              loadStations();
            });
          },
          onStateSelected: (value) {
            setState(() {
              state = value;
              showingFavorites = false;
              loadStations();
            });
          },
          onExitPressed: _onWillPop,
          onFavoritesPressed: _toggleFavoritesView,
          themeSelector: ThemeSelector(themeNotifier: widget.themeNotifier),
        ),
        body: Column(
          children: [
            if (!showingFavorites) RadioSearchBar(
              controller: _searchController,
              onSearch: _onSearch,
              onClear: _onClearSearch,
            ),
            Expanded(
              child: displayStations.isEmpty && !loading
                ? Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Icon(
                          showingFavorites ? Icons.favorite : Icons.radio,
                          size: 64,
                          color: Theme.of(context).colorScheme.primary.withOpacity(0.5),
                        ),
                        const SizedBox(height: 16),
                        Text(
                          showingFavorites
                            ? 'Nessuna stazione preferita'
                            : 'Nessuna stazione trovata',
                          style: Theme.of(context).textTheme.titleMedium,
                        ),
                      ],
                    ),
                  )
                : ListView.builder(
                    controller: _scrollController,
                    padding: const EdgeInsets.only(bottom: 150),
                    itemCount: displayStations.length + (hasMoreStations && !showingFavorites ? 1 : 0),
                    itemBuilder: (context, index) {
                      if (index == displayStations.length) {
                        return Center(
                          child: Padding(
                            padding: const EdgeInsets.all(16.0),
                            child: loading
                              ? const CircularProgressIndicator()
                              : hasMoreStations
                                ? TextButton.icon(
                                    onPressed: () => loadStations(loadMore: true),
                                    icon: const Icon(Icons.refresh),
                                    label: const Text('Carica altre stazioni'),
                                  )
                                : const SizedBox(),
                          ),
                        );
                      }

                      final station = displayStations[index];
                      return ValueListenableBuilder<RadioStationModel?>(
                        valueListenable: audioHandler.currentStation,
                        builder: (context, currentStation, _) {
                          final bool isCurrentStation = currentStation != null &&
                              currentStation.stationUuid == station.stationUuid;

                          return ValueListenableBuilder<bool>(
                            valueListenable: audioHandler.isPlaying,
                            builder: (context, isPlaying, _) {
                              final bool isPlayingThisStation = isCurrentStation && isPlaying;

                              return StationCard(
                                station: station,
                                isPlaying: isPlayingThisStation,
                                isFavorite: widget.favoritesService.isFavorite(station.stationUuid),
                                onPlayTap: () {
                                  if (isPlayingThisStation) {
                                    audioHandler.stop();
                                  } else {
                                    audioHandler.play(station);
                                  }
                                },
                                onFavoriteTap: () => _toggleFavorite(station),
                              );
                            },
                          );
                        },
                      );
                    },
                  ),
            ),
          ],
        ),
        bottomSheet: PlayerControls(
          audioHandler: audioHandler,
          scrollController: _scrollController,
          stations: displayStations,
        ),
      ),
    );
  }
}
