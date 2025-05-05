import 'package:flutter/material.dart';
import 'theme_selector.dart';

class ModernAppBar extends StatelessWidget implements PreferredSizeWidget {
  final String? country;
  final String? state;
  final Map<String, String> countries;
  final Function(String?) onCountrySelected;
  final Function(String?) onStateSelected;
  final VoidCallback onExitPressed;
  final VoidCallback onFavoritesPressed;
  final ThemeSelector themeSelector;

  const ModernAppBar({
    super.key,
    required this.country,
    required this.state,
    required this.countries,
    required this.onCountrySelected,
    required this.onStateSelected,
    required this.onExitPressed,
    required this.onFavoritesPressed,
    required this.themeSelector,
  });

  @override
  Size get preferredSize => const Size.fromHeight(70);

  @override
  Widget build(BuildContext context) {
    String title = '';
    if (country == 'IT') {
      title = state ?? 'Italia';
    } else if (country != null) {
      title = countries.entries.firstWhere(
        (e) => e.value == country,
        orElse: () => MapEntry(country!, country!),
      ).key;
    }

    return Container(
      color: Theme.of(context).appBarTheme.backgroundColor,
      child: SafeArea(
        child: SizedBox(
          height: 70,
          child: Row(
            children: [
              _buildActionButton(
                context: context,
                icon: Icons.public,
                label: 'Paese',
                onPressed: () => _showCountrySelection(context),
              ),
              Expanded(
                child: Text(
                  title,
                  style: Theme.of(context).textTheme.titleLarge,
                  textAlign: TextAlign.center,
                  overflow: TextOverflow.ellipsis,
                ),
              ),
              if (country == 'IT')
                _buildActionButton(
                  context: context,
                  icon: Icons.location_on,
                  label: 'Regione',
                  onPressed: () => _showRegionSelection(context),
                ),
              _buildActionButton(
                context: context,
                icon: Icons.favorite_outline,
                label: 'Preferiti',
                onPressed: onFavoritesPressed,
              ),
              themeSelector,
              _buildActionButton(
                context: context,
                icon: Icons.exit_to_app,
                label: 'Esci',
                onPressed: onExitPressed,
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildActionButton({
    required BuildContext context,
    required IconData icon,
    required String label,
    required VoidCallback onPressed,
  }) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 4),
      child: InkWell(
        onTap: onPressed,
        borderRadius: BorderRadius.circular(8),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              Icon(icon, size: 28),
              const SizedBox(height: 2),
              Text(
                label,
                style: Theme.of(context).textTheme.labelSmall,
              ),
            ],
          ),
        ),
      ),
    );
  }

  void _showCountrySelection(BuildContext context) {
    showModalBottomSheet(
      context: context,
      backgroundColor: Colors.transparent,
      builder: (context) => Container(
        decoration: BoxDecoration(
          color: Theme.of(context).scaffoldBackgroundColor,
          borderRadius: const BorderRadius.vertical(top: Radius.circular(20)),
        ),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Container(
              margin: const EdgeInsets.only(top: 8),
              width: 40,
              height: 4,
              decoration: BoxDecoration(
                color: Colors.grey[300],
                borderRadius: BorderRadius.circular(2),
              ),
            ),
            const SizedBox(height: 16),
            Text(
              'Seleziona Paese',
              style: Theme.of(context).textTheme.titleLarge,
            ),
            const SizedBox(height: 16),
            Expanded(
              child: ListView(
                padding: EdgeInsets.zero,
                children: [
                  ListTile(
                    leading: const Icon(Icons.public),
                    title: const Text('Tutti i Paesi'),
                    onTap: () {
                      onCountrySelected(null);
                      Navigator.pop(context);
                    },
                  ),
                  ...countries.entries.map((e) => ListTile(
                    leading: e.value == 'IT'
                        ? const Icon(Icons.star)
                        : const SizedBox(width: 24),
                    title: Text(e.key),
                    onTap: () {
                      onCountrySelected(e.value);
                      Navigator.pop(context);
                    },
                  )),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  void _showRegionSelection(BuildContext context) {
    final regions = [
      'Abruzzo', 'Basilicata', 'Calabria', 'Campania',
      'Emilia-Romagna', 'Friuli-Venezia Giulia', 'Lazio',
      'Liguria', 'Lombardia', 'Marche', 'Molise', 'Piemonte',
      'Puglia', 'Sardegna', 'Sicilia', 'Toscana',
      'Trentino-Alto Adige', 'Umbria', "Valle d'Aosta", 'Veneto'
    ];

    showModalBottomSheet(
      context: context,
      backgroundColor: Colors.transparent,
      builder: (context) => Container(
        decoration: BoxDecoration(
          color: Theme.of(context).scaffoldBackgroundColor,
          borderRadius: const BorderRadius.vertical(top: Radius.circular(20)),
        ),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Container(
              margin: const EdgeInsets.only(top: 8),
              width: 40,
              height: 4,
              decoration: BoxDecoration(
                color: Colors.grey[300],
                borderRadius: BorderRadius.circular(2),
              ),
            ),
            const SizedBox(height: 16),
            Text(
              'Seleziona Regione',
              style: Theme.of(context).textTheme.titleLarge,
            ),
            const SizedBox(height: 16),
            Expanded(
              child: ListView(
                padding: EdgeInsets.zero,
                children: [
                  ListTile(
                    leading: const Icon(Icons.location_on),
                    title: const Text('Tutte le Regioni'),
                    onTap: () {
                      onStateSelected(null);
                      Navigator.pop(context);
                    },
                  ),
                  ...regions.map((region) => ListTile(
                    contentPadding: const EdgeInsets.symmetric(
                      horizontal: 24,
                      vertical: 4,
                    ),
                    title: Text(region),
                    onTap: () {
                      onStateSelected(region);
                      Navigator.pop(context);
                    },
                  )),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
