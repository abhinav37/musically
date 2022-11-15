# Musically Mobile Dev Test

## Overview

- I decided to rewrite the majority of the App in compose as its the latest tech stack and works
  well if you scale it up with features with a working design system
- The app follows a MVVM architecture and clean code principles and uses hilt to mange DI
- Im using retrofit as a network library to fetch artist data
- Have added Shimmer Effect as Loading Placeholders
- Comments: Usually in practise I use detailed PRs to explain about the code rather than leaving
  comments when absolutely necessary

## Features

- Simple app to search Artist data
- Details page displays artists as well a list of their albums
- Uses https://musicbrainz.org/ws/2/ for data
    - see: https://musicbrainz.org/doc/MusicBrainz_API for API documentation
    - data sent is very partial so you might see a lot of unknowns

## Modularization

- The app is modularied with multiple modules for faster build time and gradle caching
    - App
        - Main Module with applications and dependencies to all modules
        - Entry point of the app
    - Network
        - Module handling network requests and retrofit
    - Common
        - Module containing all common shared resources ex CorutineDispachers etc
    - Feature
        - Usually each feature is supposed to have its own module but due to the small nature of the
          App single module is used
        - Contains Viewmodels, screens (compose views), and Repository to fetch data
    - DesignSystem
        - Having a common design for an app is good for UX and consistancy
        - It also makes writing common features a breeze with compose as a lot of components can be
          reused
        - All Color, Typography data is store here as well

## Testing

- I'm using Fakes to test viewmodel, repository and usecases as there is no need for mocking and
  final classes can be used easily
- Im also injecting Coroutine dispatchers so its easier to replace with Test dispatchers while
  testing suspended functions

## Screenshots

- Please see screenshots attached
- <Img src="./screenshots/ss_1.png" width="300px">
- <Img src="./screenshots/ss_2.png" width="300px">
- <Img src="./screenshots/ss_3.png" width="300px">
- <Img src="./screenshots/ss_4.png" width="300px">

## Build

- Check Release tab
