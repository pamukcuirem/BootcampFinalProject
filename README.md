Watch Out is a movie purchasing application designed to offer an exceptional user experience with its clean, simple, 
and highly usable interface. Whether you're a movie enthusiast or a casual viewer, Watch Out makes buying, organizing
and discovering movies effortless and enjoyable.

_**KEY FEATURES**_
**Unlimited Purchases**: Buy as many movies as you like and build your ultimate collection.
**User-Friendly Interface**: Enjoy a clean and intuitive UI designed for seamless navigation.
**Favorites List**: Add movies to your favorites list for quick access to the ones you love most.
**Advanced Filtering**: Easily find your movies by filtering through name, category, or director.

_**TECHNOLOGY STACK**_
Watch Out is built using modern Android development practices and tools to ensure a high-quality application that is efficient and easy to maintain:
**MVVM Architecture**: Leverages the Model-View-ViewModel architecture for a clear separation of concerns and better code maintainability.
**Hilt**: Dependency injection for managing dependencies and improving testability.
**Room**: Local database for storing user data like movie lists and favorites.
**Retrofit**: HTTP client for fetching movie data from APIs.
**Data Storage**: Utilized Android’s secure and efficient data storage mechanisms.
**Glide**: Efficient image loading and caching for movie posters and thumbnails.
**Jetpack Compose**: Modern UI toolkit for building beautiful, responsive, and declarative user interfaces.

_**SCREENS**_
**MainActivity** -> Runs SplashPage and Screens
**AllChips** -> Includes alL chip composables for all screens.

**Screens **-> Includes Bottom Bar and manages navigation between MainScreenNavigation, SearchScreenNavigation and CartScreenNavigation.
**MainScreenNavigation** -> Manages navigation between MainPage, CategoryPage, CategoryDetailPage and MovieDetailPage
**SearchScreenNavigation** -> Manages navigation between SearchPage and MovieDetailPage
**CartScreenNavigation** -> Manages navigation between CartPage and PurchaseCompletedPage
