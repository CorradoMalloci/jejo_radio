import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import '../services/api_service.dart';
import '../services/favorites_service.dart';
import '../models/radio_station_model.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> with SingleTickerProviderStateMixin {
  final ApiService _apiService = ApiService();
  final FavoritesService _favoritesService = FavoritesService();
  late TabController _tabController;

  // Alphabet tabs - letters A-Z + 'ALL'
  final List<String> _alphabetTabs = ['ALL', ...List.generate(26, (index) => String.fromCharCode(65 + index))];
  
  // Get supported countries from API service
  final Map<String, String> _allCountries = ApiService.supportedCountries;
  String _selectedCountry = '';
  String _selectedLetter = '';
      print('Received ${stations.length} stations from API');
  List<String> _favoriteStationIds = [];
  bool _isLoading = true;
  bool _hasError = false;
  String _errorMessage = '';
  String _searchQuery = '';

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: _alphabetTabs.length, vsync: this);
    _tabController.addListener(_handleTabSelection);
    _loadData();
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  void _handleTabSelection() {
    if (_tabController.indexIsChanging) {
      _loadData(letter: _alphabetTabs[_tabController.index] == 'ALL' ? null : _alphabetTabs[_tabController.index]);
    }
  }

  // Load both stations and favorites
  Future<void> _loadData({String? letter}) async {
    setState(() {
      _isLoading = true;
      _hasError = false;
      if (letter != null) _selectedLetter = letter;
    });

    try {
      // Load favorite station IDs
      final favoriteIds = await _favoritesService.getFavoriteIds();
      
      // Load stations from API with current filters
      final stations = await _apiService.getStations(
        countryCode: _selectedCountry.isEmpty ? null : _selectedCountry,
        nameStartsWith: letter == 'ALL' ? null : letter,
      );
      
      // Update stations with favorite status
      final updatedStations = stations.map((station) {
        final isFavorite = favoriteIds.contains(station.stationUuid);
        return station.copyWith(isFavorite: isFavorite);
      }).toList();
      
      if (mounted) {
        setState(() {
          _stations = updatedStations;
          _favoriteStationIds = favoriteIds;
          _isLoading = false;
        });
      }
    } catch (e) {
      if (mounted) {
        setState(() {
          _isLoading = false;
          _hasError = true;
          _errorMessage = 'Failed to load radio stations: $e';
        });
      }
    }
  }

  // Toggle favorite status for a station
  Future<void> _toggleFavorite(RadioStationModel station) async {
    try {
      await _favoritesService.toggleFavorite(station);
      
      // Refresh favorite IDs
      final favoriteIds = await _favoritesService.getFavoriteIds();
      
      if (mounted) {
        setState(() {
          _favoriteStationIds = favoriteIds;
          
          // Update the station in the list
          final index = _stations.indexWhere(
            (s) => s.stationUuid == station.stationUuid
          );
          
          if (index != -1) {
            final updatedStation = _stations[index].copyWith(
              isFavorite: !_stations[index].isFavorite
            );
            
            _stations[index] = updatedStation;
          }
        });
      }
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Failed to update favorites: $e'))
      );
    }
  }

  // Filter stations based on search query
  List<RadioStationModel> get _filteredStations {
    if (_searchQuery.isEmpty) {
      return _stations;
    }
    
    return _stations.where((station) {
      return station.name.toLowerCase().contains(_searchQuery.toLowerCase());
    }).toList();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Radio App'),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: () => _loadData(letter: _alphabetTabs[_tabController.index] == 'ALL' ? null : _alphabetTabs[_tabController.index]),
          ),
        ],
        bottom: TabBar(
          controller: _tabController,
          isScrollable: true,
          tabs: _alphabetTabs.map((letter) => Tab(text: letter)).toList(),
        ),
      ),
      body: Column(
        children: [
          // Country filter
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
            child: DropdownButtonFormField<String>(
              value: _selectedCountry.isEmpty ? null : _selectedCountry,
              hint: const Text('Filter by country'),
              items: _allCountries.entries.map((entry) {
                return DropdownMenuItem<String>(
                  value: entry.value,
                  child: Text('${entry.key} (${entry.value})'),
                );
              }).toList(),
              onChanged: (value) {
                setState(() {
                  _selectedCountry = value ?? '';
                });
                _loadData();
              },
            ),
          ),
          
          // Search bar
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
            child: TextField(
              decoration: const InputDecoration(
                labelText: 'Search stations',
                prefixIcon: Icon(Icons.search),
               
          
          // Main content
          // Station counter
          Container(
          if (_alphabetTabs[_tabController.index] == 'ALL')
            Container(
              padding: const EdgeInsets.all(8),
              color: Colors.orange[100],
              child: const Text(
                'Nota: Per visualizzare tutte le stazioni, usa i filtri alfabetici',
                style: TextStyle(fontSize: 12),
              ),
            ),

          // Station counter
          Container(
            padding: const EdgeInsets.all(8),
            child: Text(
              'Stazioni con "${_alphabetTabs[_tabController.index]}" (${_stations.length})',
              style: const TextStyle(fontWeight: FontWeight.bold),
            ),
          ),
          Expanded(
            child: _buildContent(),
          ),
    );
  }

  Widget _buildContent() {
    if (_isLoading) {
      return const Center(child: CircularProgressIndicator());
    }
    
    if (_hasError) {
      return Center(
            Text(_errorMessage, textAlign: TextAlign.center),
            if (_stations.isNotEmpty)
              Text('Partial results: ${_stations.length} stations loaded',
                style: TextStyle(color: Colors.grey[600])),
            const SizedBox(height: 16),
            ElevatedButton(
              onPressed: _loadData,
              child: const Text('Retry'),
            ),
              onPressed: _loadData,
              child: const Text('Retry'),
            ),
          ],
        ),
      );
    }
    
    if (_stations.isEmpty) {
      return const Center(
        child: Text('No radio stations found'),
      );
    }
    
    // Display stations with pull-to-refresh
    return RefreshIndicator(
      onRefresh: _loadData,
      child: ListView.builder(
        itemCount: _filteredStations.length,
        itemBuilder: (context, index) {
          final station = _filteredStations[index];
          return _buildStationTile(station);
        },
      ),
    );
  }

  Widget _buildStationTile(RadioStationModel station) {
    return ListTile(
      leading: _buildStationIcon(station),
      title: Text(
        station.name,
        maxLines: 1,
        overflow: TextOverflow.ellipsis,
      ),
      subtitle: station.countryCode.isNotEmpty
          ? Text(station.countryCode)
          : null,
      trailing: IconButton(
        icon: Icon(
          station.isFavorite ? Icons.favorite : Icons.favorite_border,
          color: station.isFavorite ? Colors.red : null,
        ),
        onPressed: () => _toggleFavorite(station),
      ),
      onTap: () {
        // Handle station selection (to be implemented later)
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Selected station: ${station.name}')),
        );
      },
    );
  }

  Widget _buildStationIcon(RadioStationModel station) {
    // If no favicon or it's an empty string, show a placeholder
    if (station.favicon.isEmpty) {
      return const CircleAvatar(
        child: Icon(Icons.radio),
      );
    }
    
    // Use CachedNetworkImage for favicon
    return CircleAvatar(
      backgroundColor: Colors.white,
      child: ClipOval(
        child: CachedNetworkImage(
          imageUrl: station.favicon,
          placeholder: (context, url) => const SizedBox(
            width: 24,
            height: 24,
            child: CircularProgressIndicator(
              strokeWidth: 2,
            ),
          ),
          errorWidget: (context, url, error) => const Icon(
            Icons.radio,
            size: 24,
          ),
          fit: BoxFit.cover,
          width: 40,
          height: 40,
        ),
      ),
    );
  }
}
