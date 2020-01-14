# cafebazaar

flutter plugin for cafebazaar android market (only works on android obviously)

## Getting Started

### Checking for update (if newer version is available)

```dart
bool isUpdateAvailable = await Cafebazaar.isUpdateAvailable();
 if(isUpdateAvailable) {
    Cafebazaar.goToAppPageOnBazaar();
    //or just show a dialog ask if user wants to update then call Cafebazaar.goToAppPageOnBazaar();
 }
```

### Comment

```dart
//launches comment bazaar dialog
Cafebazaar.commentOnBazaar();
```

### Go to Application page on Bazaar

```dart
//launches application page on bazaar app if bazaar is installed
Cafebazaar.goToAppPageOnBazaar();
```