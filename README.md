# ChipDog
A simple Android application to search for random sets of images of various dog breeds.

## Architecture
### MVVM
An MVVM architecture that takes advantage of mutableStateOf in ViewModels.

### Compose
All UI elements developed in Jetpack Compose.

## Points of Interest
### Material 3
#### Components
The app takes advantage of several Material 3 components including LargeTopAppBar, ElevatedCard and ExtendedFloatingActionButton.
#### Dark Theme
The app also takes advantage of a custom Material 3 theming setup, with bespoke light and dark theme.

### Dependency Injection
Hilt is used to Inject ViewModels to their respective View, and to Inject Retrofit into ViewModels.

## Dependencies
### Hilt
### Coroutines
### Gson
### Retrofit
### Glide
### Accompanist Libraries
#### Swipe Refresh
#### Placeholder
### Mockito
### Google Truth

## Testing Testing
### User Testing
User testing performed on an emulated Pixel 5 running API 21, and a physical Pixel 6 Pro running API 31.
### Unit Testing
Unit tests passing for 100% ViewModel coverage.
### UI Testing
UI tests passing for 100% View coverage.