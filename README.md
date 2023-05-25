# android-basekit

BaseKit is a starter template for Android projects that provides a solid foundation with commonly used and updated libraries and configurations. It aims to save development time by providing a boilerplate codebase with basic configurations already implemented.

## Features

- MVVM architecture with Data Binding
- Reactive programming with Kotlin Coroutines and Flow
- Navigation Component for handling navigation
- Dependency Injection using Dagger Hilt
- Retrofit and okHttp for networking
- Room for local data persistence
- Material Design components and guidelines
- Unit testing with JUnit and Mockito
- Robolectric for running tests on Android framework

 ### Modularization
 
  The app is divided into separate modules according to the layers from [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).
   - **domain module** - contains models/entities that are being used within the app.
   - **persistence module** - contains classes that handle local storage such database, daos and shared preference.
   - **network module** - contains classes that handle networking such api services and remote data sources.
   - **data module** - contains repository classes and responsible for combining network and local data sources.
   - **app module** - contains the presentation layer (Activities, Fragments, ViewModels, UI logic)
   - **core module** - contains the common core classes such as extension and widgets
   - **buildSrc** - contains the Config file where all libraries and their versions are declared.
