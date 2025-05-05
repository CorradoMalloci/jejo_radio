import 'package:flutter/material.dart';
import '../services/theme_service.dart';

class ThemeSelector extends StatelessWidget {
  final ThemeNotifier themeNotifier;

  const ThemeSelector({
    super.key,
    required this.themeNotifier,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 4),
      child: InkWell(
        borderRadius: BorderRadius.circular(8),
        onTap: () => _showThemeSelector(context),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              Icon(
                _getThemeIcon(),
                size: 28,
              ),
              const SizedBox(height: 2),
              Text(
                'Tema',
                style: Theme.of(context).textTheme.labelSmall,
              ),
            ],
          ),
        ),
      ),
    );
  }

  void _showThemeSelector(BuildContext context) {
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
              'Seleziona Tema',
              style: Theme.of(context).textTheme.titleLarge,
            ),
            const SizedBox(height: 16),
            _buildThemeOption(
              context,
              'Tema di Sistema',
              Icons.brightness_auto,
              ThemeMode.system,
            ),
            _buildThemeOption(
              context,
              'Tema Chiaro',
              Icons.brightness_5,
              ThemeMode.light,
            ),
            _buildThemeOption(
              context,
              'Tema Scuro',
              Icons.brightness_4,
              ThemeMode.dark,
            ),
            const SizedBox(height: 16),
          ],
        ),
      ),
    );
  }

  Widget _buildThemeOption(
    BuildContext context,
    String title,
    IconData icon,
    ThemeMode mode,
  ) {
    final isSelected = themeNotifier.themeMode == mode;
    return ListTile(
      leading: Icon(
        icon,
        color: isSelected ? Theme.of(context).colorScheme.primary : null,
      ),
      title: Text(
        title,
        style: TextStyle(
          color: isSelected ? Theme.of(context).colorScheme.primary : null,
          fontWeight: isSelected ? FontWeight.bold : null,
        ),
      ),
      trailing: isSelected
          ? Icon(
              Icons.check,
              color: Theme.of(context).colorScheme.primary,
            )
          : null,
      onTap: () {
        themeNotifier.setThemeMode(mode);
        Navigator.pop(context);
      },
    );
  }

  IconData _getThemeIcon() {
    switch (themeNotifier.themeMode) {
      case ThemeMode.system:
        return Icons.brightness_auto;
      case ThemeMode.light:
        return Icons.brightness_5;
      case ThemeMode.dark:
        return Icons.brightness_4;
    }
  }
}
