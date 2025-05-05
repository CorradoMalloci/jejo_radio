# Je Jo Radio

[English](#english) | [Italiano](#italiano)

# English

A modern streaming radio application developed with Flutter.

## Main Features

- 🎵 Radio streaming with background playback support
- 🌍 Search and filter by country and region (special support for Italy)
- ⭐ Favorites management with persistence
- 🎨 Theme support:
  - Light theme
  - Dark theme
  - System theme synchronization
- 📱 Material 3 design with modern interface
- 🔄 Infinite station loading
- 🎧 Persistent player with advanced controls

## System Requirements

### For Users
- Android 5.0 (API 21) or higher
- Storage space: ~12MB
- Internet connection for streaming

### For Developers
- Flutter SDK ≥3.0.0
- Dart SDK ≥3.0.0
- Android Studio / VS Code with Flutter plugin
- JDK ≥11

## Installation

### Android Users
Choose the appropriate APK for your device:

- `JeJoRadio-beta2-arm64.apk`: For modern devices (Android 5.0+)
- `JeJoRadio-beta2-armv7.apk`: For older devices
- `JeJoRadio-beta2-x64.apk`: For x86_64 emulators

### Developers
```bash
# Clone the repository
git clone https://github.com/CorradoMalloci/jejo_radio.git

# Navigate to directory
cd jejo_radio

# Install dependencies
flutter pub get

# Run in debug mode
flutter run

# Create release build
flutter build apk --release --split-per-abi
```

## Technologies Used

- **Flutter**: Cross-platform UI framework
- **just_audio**: Audio streaming engine
- **just_audio_background**: Background playback support
- **shared_preferences**: Local data persistence
- **provider**: State management
- **cached_network_image**: Image caching
- **google_fonts**: Montserrat and Inter fonts
- **http**: Network requests

## Project Structure

```
lib/
├── models/         # Data models
├── screens/        # App screens
├── services/       # Services (API, audio, favorites)
├── theme/          # Theme configuration
└── widgets/        # Reusable widgets
```

## Detailed Features

### Radio Management
- Worldwide radio station streaming
- Country and region filtering
- Station text search
- Favorites management
- Advanced playback controls

### UI/UX
- Material 3 design
- Customizable themes
- Fluid animations
- Responsive layout
- Visual action feedback

### Performance
- Lazy station loading
- Image caching
- Memory consumption optimizations
- Efficient streaming management

## Versions

### Beta 2 (0.2.0-beta.2+2) - May 2024
- Added theme selector (System/Light/Dark)
- Improved contrast and readability
- Performance optimizations
- Added advanced player controls
- System theme support

### Beta 1 (0.1.0-beta.1) - April 2024
- First public release
- Basic streaming functionality
- Initial UI/UX
- Favorites support

## Roadmap
- [ ] Add audio equalizer
- [ ] Custom playlist support
- [ ] Sleep timer
- [ ] Lock screen widget
- [ ] Listening statistics
- [ ] Multi-language support

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
Copyright © 2024 Corrado Malloci. All rights reserved.

## Contact
- Author: Corrado Malloci
- Email: ifenicotteri@gmail.com
- GitHub: [CorradoMalloci](https://github.com/CorradoMalloci)

## Acknowledgments
- Radio Browser API for the radio station database
- Flutter team for the framework
- All library contributors

---

# Italiano

Una moderna applicazione per l'ascolto di radio in streaming, sviluppata con Flutter.

## Caratteristiche Principali

- 🎵 Streaming radio con supporto riproduzione in background
- 🌍 Ricerca e filtro per paese e regione (supporto speciale per l'Italia)
- ⭐ Gestione dei preferiti con persistenza
- 🎨 Supporto temi:
  - Tema chiaro
  - Tema scuro
  - Sincronizzazione con il tema di sistema
- 📱 Design Material 3 con interfaccia moderna
- 🔄 Caricamento infinito delle stazioni
- 🎧 Player persistente con controlli avanzati

## Requisiti di Sistema

### Per gli utenti
- Android 5.0 (API 21) o superiore
- Spazio di archiviazione: ~12MB
- Connessione Internet per lo streaming

### Per gli sviluppatori
- Flutter SDK ≥3.0.0
- Dart SDK ≥3.0.0
- Android Studio / VS Code con plugin Flutter
- JDK ≥11

## Installazione

### Utenti Android
Scegli l'APK appropriato per il tuo dispositivo:

- `JeJoRadio-beta2-arm64.apk`: Per dispositivi moderni (Android 5.0+)
- `JeJoRadio-beta2-armv7.apk`: Per dispositivi più vecchi
- `JeJoRadio-beta2-x64.apk`: Per emulatori x86_64

### Sviluppatori
```bash
# Clona il repository
git clone https://github.com/CorradoMalloci/jejo_radio.git

# Naviga nella directory
cd jejo_radio

# Installa le dipendenze
flutter pub get

# Esegui in modalità debug
flutter run

# Crea il build release
flutter build apk --release --split-per-abi
```

## Tecnologie Utilizzate

- **Flutter**: Framework UI cross-platform
- **just_audio**: Engine audio per lo streaming
- **just_audio_background**: Supporto riproduzione in background
- **shared_preferences**: Persistenza dati locali
- **provider**: Gestione dello stato
- **cached_network_image**: Caching delle immagini
- **google_fonts**: Font Montserrat e Inter
- **http**: Richieste di rete

## Struttura del Progetto

```
lib/
├── models/         # Modelli dati
├── screens/        # Schermate dell'app
├── services/       # Servizi (API, audio, preferiti)
├── theme/          # Configurazione tema
└── widgets/        # Widget riutilizzabili
```

## Funzionalità Dettagliate

### Gestione Radio
- Streaming di stazioni radio da tutto il mondo
- Filtro per paese e regione
- Ricerca testuale delle stazioni
- Gestione preferiti
- Controlli di riproduzione avanzati

### UI/UX
- Design Material 3
- Temi personalizzabili
- Animazioni fluide
- Layout responsivo
- Feedback visivi per le azioni

### Performance
- Caricamento lazy delle stazioni
- Caching delle immagini
- Ottimizzazioni per il consumo di memoria
- Gestione efficiente dello streaming

## Versioni

### Beta 2 (0.2.0-beta.2+2) - Maggio 2024
- Aggiunto selettore tema (Sistema/Chiaro/Scuro)
- Migliorato contrasto e leggibilità
- Ottimizzate le performance
- Aggiunti controlli player avanzati
- Supporto per il tema di sistema

### Beta 1 (0.1.0-beta.1) - Aprile 2024
- Prima release pubblica
- Funzionalità base di streaming
- UI/UX iniziale
- Supporto per preferiti

## Roadmap
- [ ] Aggiunta equalizzatore audio
- [ ] Supporto per playlist personalizzate
- [ ] Timer di spegnimento
- [ ] Widget per la schermata di blocco
- [ ] Statistiche di ascolto
- [ ] Supporto per più lingue

## Contribuire
Le pull request sono benvenute. Per modifiche importanti, apri prima un issue per discutere cosa vorresti cambiare.

## Licenza
Copyright © 2024 Corrado Malloci. Tutti i diritti riservati.

## Contatti
- Autore: Corrado Malloci
- Email: ifenicotteri@gmail.com
- GitHub: [CorradoMalloci](https://github.com/CorradoMalloci)

## Ringraziamenti
- Radio Browser API per il database delle stazioni radio
- Flutter team per il framework
- Tutti i contributori delle librerie utilizzate
