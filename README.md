# ChipDog
A simple Android application to search for random sets of images of various dog breeds.

## Screen Description
### Breed List
A swipe to refresh lazy list that shows a list of dog breeds from https://dog.ceo/dog-api/documentation/. List is alphabetically ordered and utilised sticky headers. Once scrolled, a FAB will show that allows the user to scroll to the top of the list.

### Breed Images
A swipe to refresh lazy grid that shows 10 random images (if available) of a chosen breed from https://dog.ceo/dog-api/documentation/breed

## Architecture
### MVVM
An MVVM architecture that takes advantage of mutableStateOf in ViewModels. Any state change must be performed in the ViewModel.

### Compose
All UI elements developed with Jetpack Compose.

## Points of Interest
### Material 3
#### Components
The app takes advantage of several Material 3 components including LargeTopAppBar, ElevatedCard and ExtendedFloatingActionButton.
#### Dark Theme
The app also takes advantage of a custom Material 3 theming setup, with bespoke light and dark theme.

### Dependency Injection
Hilt is used to Inject ViewModels to their respective View, and to Inject Retrofit into ViewModels.

## Dependency Overview
* Compose
* Material 3
* Hilt
* Coroutines
* Gson
* Retrofit
* Glide
* Accompanist Libraries
    * Swipe Refresh
    * Placeholder
* JUnit
* Mockito
* Google Truth

## Testing Testing
### User Testing
User testing performed on an emulated Pixel 5 running API 21, and a physical Pixel 6 Pro running API 31.
### Unit Testing
Unit tests passing for 100% ViewModel coverage.
### UI Testing
UI tests passing for 100% View coverage.