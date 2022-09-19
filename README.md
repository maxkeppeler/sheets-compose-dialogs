# Sheets-Compose-Dialogs

<p>

  <img src="docs/res/ic_library.png" width="96px" height="96px" alt="Sheets Library" align="left" style="margin-right: 24px; margin-bottom: 24px">

  <p>

An Android library that offers dialogs & views for various use cases - build with Jetpack Compose
for Compose projects. All of the dialogs & views are easy and quick to implement. Views can be used
for PopUps, BottomSheets and other containers.

   <a href="https://search.maven.org/search?q=g:%22com.maxkeppeler.sheets-compose-dialogs%22">
     <img style="margin-right: 4px; margin-bottom: 8px;" alt="Version of Sheets library" src="https://img.shields.io/maven-central/v/com.maxkeppeler.sheets-compose-dialogs/core.svg?label=Maven%20Central">
   </a>

   <a href="https://github.com/maxkeppeler/sheets-compose-dialogs">
     <img style="margin-right: 4px; margin-bottom: 8px;" alt="Codacy code quality of Sheets library" src="https://img.shields.io/codacy/grade/9a3b68b152e149fd82f0873e2fed78d5?label=Code%20Quality">
   </a>

   <a href="https://www.apache.org/licenses/LICENSE-2.0">
     <img style="margin-right: 4px; margin-bottom: 8px;" alt="GitHub" src="https://img.shields.io/github/license/maxkeppeler/sheets-compose-dialogs?color=%23007EC6&label=">
   </a>

<a href="https://github.com/maxkeppeler/sheets-compose-dialogs">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Give this library a star" src="https://img.shields.io/github/stars/maxkeppeler/sheets-compose-dialogs?style=social">
</a>

<a href="https://github.com/maxkeppeler/sheets-compose-dialogs/fork">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Fork this library" src="https://img.shields.io/github/forks/maxkeppeler/sheets-compose-dialogs?style=social">
</a>

<a href="https://github.com/maxkeppeler/sheets-compose-dialogs">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Watch this library" src="https://img.shields.io/github/watchers/maxkeppeler/sheets.svg?style=social&amp;label=Watch">
</a>

<a href="https://github.com/maxkeppeler/">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Follow me on GitHub" src="https://img.shields.io/github/followers/maxkeppeler?style=social&label=Follow">
</a>

<a href="https://twitter.com/intent/tweet?text=Checkout%20this%20beautiful%20library!%20%23android%20%23androiddev%20%23library%20%40maxkeppeler%20%0A%0Ahttps%3A%2F%2Fgithub.com%2Fmaxkeppeler%2Fsheets">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Share this library on Twitter" src="https://img.shields.io/twitter/url?style=social&url=https%3A%2F%2Fgithub.com%2Fmaxkeppeler%2Fsheets&label=Share">
</a>

<a href="https://twitter.com/max_keppeler">
  <img style="margin-right: 4px; margin-bottom: 8px" alt="Follow Maximilian Keppeler on Twitter" src="https://img.shields.io/twitter/follow/maxkeppeler?label=Follow&style=social">
</a>

<img src="art/showcase.png" alt="sheets Library">

## Table of Contents

- [Get started](#get-started)
    - [Info Dialog/ View](#info)
    - [Clock Dialog/ View](#clock)
    - [Duration Dialog/ View](#duration)
    - [Input Dialog/ View](#input)
    - [Calendar Dialog/ View](#calendar)
    - [DateTime Dialog/ View](#date-time)
    - [Color Dialog/ View](#color)
    - [Option Dialog/ View](#option)
    - [List Dialog/ View](#list)
    - [Emoji Dialog/ View](#emoji)
    - [State Dialog/ View](#state)
    - [Core Dialog/ View](#core)
- [Misc](#misc)
    - [Support this project](#support-this-project)
    - [Contribute](#contribute)
    - [Donate](#donate)
    - [Showcase](#showcase)
    - [License](#license)

# Get started

A sheet can dynamically be displayed as either a dialog or as a bottom-sheet. Check out
the [sample](https://github.com/MaxKeppeler/sheets/blob/main/sample/sample.apk).

You have to use the `core` module as it is the foundation of any sheet.

In your top-level `build.gradle` file:

```gradle
repositories {
  ...
  mavenCentral()
}
```

In your app `build.gradle` file:

[ ![Download](https://img.shields.io/maven-central/v/com.maxkeppeler.sheets-compose-dialogs/core.svg?label=Maven%20Central) ](https://search.maven.org/artifact/com.maxkeppeler.sheets/core)

```gradle
dependencies {
  ...
  implementation 'com.maxkeppeler.sheets-compose-dialogs:<module>]:<latest-version>'
}
```

## Support this project

- Leave a star and tell others about it
- Watch for updates and improvements.
- [Open an issue](https://github.com/MaxKeppeler/sheets/issues/) if you see or got any error.
- Leave your
  thanks [here](https://github.com/MaxKeppeler/sheets/discussions/categories/show-and-tell) and
  showcase your implementation.
- Donate me a coffee. ;)

## Contribute

1. Open an issue to discuss what you would like to change.
2. Fork the Project
3. Create your feature branch (feature-[some-name])
4. Commit your changes
5. Push to the branch (origin feature-[some-name])
6. Open a pull request

## Donate

Show your appreciation by donating me a coffee. Thank you very much!

<a href="https://ko-fi.com/maxkeppeler" target='_blank'>
 <img width="180" src='https://cdn.ko-fi.com/cdn/kofi2.png?v=2' alt='Buy Me a Coffee at ko-fi.com' />
</a>

<a href="https://www.buymeacoffee.com/maxkeppeler" target="_blank">
    <img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" width="160">
</a>

<a href="https://www.paypal.me/maximiliankeppeler" target="_blank">
    <img src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif" alt="Donate on PaPal" width="160">
</a>

## Showcase

Check out some apps which are using this library.<br/>

- [Mangata](http://mangata-mk.com)

## License

    Copyright 2022 Maximilian Keppeler https://maxkeppeler.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
