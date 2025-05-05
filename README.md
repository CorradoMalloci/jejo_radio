# Je Jo Radio

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
