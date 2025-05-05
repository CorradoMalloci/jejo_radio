import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class AppTheme {
  // Light Theme Colors
  static const Color _lightPrimaryColor = Color(0xFF1976D2);    // Blu più vivace
  static const Color _lightSecondaryColor = Color(0xFF2196F3);  // Blu chiaro
  static const Color _lightBackgroundColor = Color(0xFFFFFFFF); // Bianco puro
  static const Color _lightSurfaceColor = Color(0xFFF5F5F5);   // Grigio molto chiaro
  static const Color _lightErrorColor = Color(0xFFD32F2F);      // Rosso acceso
  static const Color _lightTextColor = Color(0xFF212121);       // Quasi nero

  // Dark Theme Colors
  static const Color _darkPrimaryColor = Color(0xFF90CAF9);     // Blu chiaro
  static const Color _darkSecondaryColor = Color(0xFF64B5F6);   // Blu medio
  static const Color _darkBackgroundColor = Color(0xFF121212);  // Grigio molto scuro
  static const Color _darkSurfaceColor = Color(0xFF1E1E1E);    // Grigio scuro
  static const Color _darkErrorColor = Color(0xFFEF5350);       // Rosso chiaro
  static const Color _darkTextColor = Color(0xFFFFFFFF);        // Bianco

  static TextTheme _buildTextTheme(TextTheme base, bool isDark) {
    return GoogleFonts.montserratTextTheme(base).copyWith(
      displayLarge: GoogleFonts.montserrat(
        fontSize: 32,
        fontWeight: FontWeight.bold,
        letterSpacing: -1.5,
        color: isDark ? _darkTextColor : _lightTextColor,
      ),
      displayMedium: GoogleFonts.montserrat(
        fontSize: 28,
        fontWeight: FontWeight.w700,
        letterSpacing: -0.5,
      ),
      titleLarge: GoogleFonts.montserrat(
        fontSize: 22,
        fontWeight: FontWeight.w600,
        letterSpacing: 0.15,
      ),
      titleMedium: GoogleFonts.montserrat(
        fontSize: 17,
        fontWeight: FontWeight.w500,
        letterSpacing: 0.15,
      ),
      bodyLarge: GoogleFonts.inter(
        fontSize: 16,
        fontWeight: FontWeight.w400,
        letterSpacing: 0.5,
      ),
      bodyMedium: GoogleFonts.inter(
        fontSize: 14,
        fontWeight: FontWeight.w400,
        letterSpacing: 0.25,
      ),
      labelLarge: GoogleFonts.montserrat(
        fontSize: 14,
        fontWeight: FontWeight.w500,
        letterSpacing: 1.25,
      ),
    );
  }

  static ThemeData light() {
    final ColorScheme colorScheme = ColorScheme.light(
      primary: _lightPrimaryColor,
      secondary: _lightSecondaryColor,
      surface: _lightSurfaceColor,
      background: _lightBackgroundColor,
      error: _lightErrorColor,
      onPrimary: Colors.white,
      onSecondary: Colors.white,
      onSurface: _lightTextColor,
      onBackground: _lightTextColor,
      onError: Colors.white,
    );

    return ThemeData(
      useMaterial3: true,
      colorScheme: colorScheme,
      textTheme: _buildTextTheme(ThemeData.light().textTheme, false),
      appBarTheme: AppBarTheme(
        elevation: 0,
        backgroundColor: _lightBackgroundColor,
        foregroundColor: _lightTextColor,
        centerTitle: true,
        titleTextStyle: GoogleFonts.montserrat(
          fontSize: 22,
          fontWeight: FontWeight.w600,
          letterSpacing: 0.15,
          color: _lightTextColor,
        ),
      ),
      cardTheme: CardTheme(
        elevation: 2,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
        color: Colors.white,
      ),
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ElevatedButton.styleFrom(
          elevation: 2,
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
        ),
      ),
      iconTheme: const IconThemeData(
        color: _lightPrimaryColor,
        size: 24,
      ),
      dividerTheme: const DividerThemeData(
        color: Color(0xFFE0E0E0),
        thickness: 1,
      ),
    );
  }

  static ThemeData dark() {
    final ColorScheme colorScheme = ColorScheme.dark(
      primary: _darkPrimaryColor,
      secondary: _darkSecondaryColor,
      surface: _darkSurfaceColor,
      background: _darkBackgroundColor,
      error: _darkErrorColor,
      onPrimary: _darkBackgroundColor,
      onSecondary: _darkBackgroundColor,
      onSurface: _darkTextColor,
      onBackground: _darkTextColor,
      onError: _darkBackgroundColor,
    );

    return ThemeData(
      useMaterial3: true,
      colorScheme: colorScheme,
      textTheme: _buildTextTheme(ThemeData.dark().textTheme, true),
      appBarTheme: AppBarTheme(
        elevation: 0,
        backgroundColor: _darkBackgroundColor,
        foregroundColor: _darkTextColor,
        centerTitle: true,
        titleTextStyle: GoogleFonts.montserrat(
          fontSize: 22,
          fontWeight: FontWeight.w600,
          letterSpacing: 0.15,
          color: _darkTextColor,
        ),
      ),
      cardTheme: CardTheme(
        elevation: 2,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
        color: _darkSurfaceColor,
      ),
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ElevatedButton.styleFrom(
          elevation: 2,
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
        ),
      ),
      iconTheme: const IconThemeData(
        color: _darkPrimaryColor,
        size: 24,
      ),
      dividerTheme: const DividerThemeData(
        color: Color(0xFF424242),
        thickness: 1,
      ),
    );
  }
}
