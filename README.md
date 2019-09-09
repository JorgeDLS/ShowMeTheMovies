# <img src="app/ic_launcher-web.png" width="26" alt="logo" align="bottom"> ShowMeTheMovies

A simple app that tries to follow Clean Architecture, the SOLID principles and best practices.

## Architecture used
* The whole app is written in Kotlin and uses Kotlin-Extensions for Android.
* Clean Architecture
* 3 main modules: app (UI and common module), data & domain.
* Model-View-ViewModel pattern used inside the app module.
* Repository pattern used to fetch data through DataSources
* UseCases are in charge of fetching the data from the Repository and return the data to the ViewModels
* Different objects for each DataSource and a Domain object who will be the only one appearing in app module, the other models belong to other layers.
* Dagger2 used to handle Dependency Injection.
* Retrofit is used to get data from TheMovieDB API (v3)
* Glide is used for image loading.
* For testing the application, Mockito, jUnit4, assertJ and espresso are used.

<img src="app/appGifExample.gif" width="180" alt="App example Gif">

## Working Scenario
* This project has been developed using Android Studio 3.5 (latest in that moment), with Kotlin plugin v1.3.50 and Android Gradle Plugin 3.5.0.
* The project uses android-29 api, so that's also needed in order to run the project without any problem.

## TODO
* Room as Single Source of Truth ¯\\_(ツ)_/¯
* Use AAC Paging :page_with_curl:
* More features :apple:
* More tests :bug:

## License

    Copyright 2019 Jorge De Los Santos.

    Licensed to the Apache Software Foundation (ASF) under one or more contributor
    license agreements. See the NOTICE file distributed with this work for
    additional information regarding copyright ownership. The ASF licenses this
    file to you under the Apache License, Version 2.0 (the "License"); you may not
    use this file except in compliance with the License. You may obtain a copy of
    the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
    License for the specific language governing permissions and limitations under
    the License.