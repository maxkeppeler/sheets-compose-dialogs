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

<img src="docs/res/showcase.png" alt="sheets Library">

# Get started
The library contains various use-cases. Each module represents one use-case and contains a `*Dialog` and `*View`. You can use the `*Dialog` directly while you can use the `*View` for bottom sheets, popups and other non-scrollable elements.

As the `core` module is the foundation of all other use-cases, you have to implement that additionally to the ones you want to use.

## Requirement

In your top-level `build.gradle`

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
  implementation 'com.maxkeppeler.sheets-compose-dialogs:core:<version>' // necessary
  implementation 'com.maxkeppeler.sheets-compose-dialogs:<module>:<version>'
}
```
Replace `<version>` with the (latest or preferred) version of the library.

Replace `<module>` with the module you want to use.

Available modules: `core` | `info` | `color` | `calendar` | `clock` | `duration` | `date_time` | `option` | `list` | `input` | `emoji` | `state`  

# Resources 

📖 Get a better insight into the API\
[Sheets-Compose-Dialogs API Documentation](https://maxkeppeler.github.io/sheets-compose-dialogs/api/){target="_blank"}

✨ General documentation, resources and setup samples and more\
[Sheets-Compose-Dialogs General Documentation](https://maxkeppeler.notion.site/sheets-compose-dialogs-804f0ebcb2c84b98b7afa5f687295aed){target="_blank"}

# Showcase
Check out some of the use-cases as dialogs. All of them could be displayed within a PopUp, BottomSheet or another container view as well.
</br>
</br>

## Color Dialog / View
The color dialog / view allows you to pick a color from a range of template colors or a custom color.
<table>
  <tr>
    <th>Sample 1</th>
    <th>Sample 2</th>
    <th>Sample 3</th>
    <th></th>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/light/dialog_color_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_color_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_color_sample_3.gif" /></td>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/dark/dialog_color_sample_1.gif" /></td> 
    <td width="25%"><img src="docs/res/dark/dialog_color_sample_2.gif" /></td> 
    <td width="25%"><img src="docs/res/dark/dialog_color_sample_3.gif" /></td> 
  </tr>
</table>

## Calendar Dialog / View
The calendar dialog / view allows you to pick a date, multiple dates or a range.
</br>
<table>
  <tr>
    <th>Sample 1</th>
    <th>Sample 2</th>
    <th>Sample 3</th>
    <th></th>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/light/dialog_calendar_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_calendar_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_calendar_sample_3.gif" /></td>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/dark/dialog_calendar_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_calendar_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_calendar_sample_3.gif" /></td>
  </tr>
</table>


## Clock Dialog / View
The clock dialog / view allows you to pick a clock time, in either the 12-Hour-Format or the 24-Hour-Format.
</br>
<table>
  <tr>
    <th>Sample 1</th>
    <th>Sample 2</th>
    <th></th>
    <th></th>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/light/dialog_clock_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_clock_sample_2.gif" /></td>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/dark/dialog_clock_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_clock_sample_2.gif" /></td>
  </tr>
</table>


## Duration Dialog / View
The duration dialog / view allows you to pick a duration time within the bounds of a time format.
</br>
<table>
  <tr>
    <th>Sample 1</th>
    <th>Sample 2</th>
    <th></th>
    <th></th>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/light/dialog_duration_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_duration_sample_2.gif" /></td>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/dark/dialog_duration_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_duration_sample_2.gif" /></td>
  </tr>
</table>

## Date-Time Dialog / View
The date-time dialog / view allows you to pick a date and/ or clock-time in a quick spinner-styled way.
</br>
<table>
  <tr>
    <th>Sample 1</th>
    <th>Sample 2</th>
    <th>Sample 3</th>
    <th></th>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/light/dialog_date-time_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_date-time_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_date-time_sample_3.gif" /></td>
  </tr>
  <tr>
     <td width="25%"><img src="docs/res/dark/dialog_date-time_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_date-time_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_date-time_sample_3.gif" /></td>
 </tr>
</table>


## Option Dialog / View
The option dialog / view allows you to pick one or multiple options. Options can be preselected, disabled and the selection can be limited.
</br>
<table>
  <tr>
    <th>Sample 1</th>
    <th>Sample 2</th>
    <th>Sample 3</th>
    <th></th>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/light/dialog_option_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_option_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_option_sample_3.gif" /></td>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/dark/dialog_option_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_option_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_option_sample_3.gif" /></td>
  </tr>
</table>


## List Dialog / View
The list dialog / view allows you to pick one or multiple options within a vertical list. Options can be preselected and the selection can be limited. The options can be displayed with a CheckBoxes and RadioButtons depending on the selection type.
</br>
<table>
  <tr>
    <th>Sample 1</th>
    <th>Sample 2</th>
    <th>Sample 3</th>
    <th>Sample 4</th>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/light/dialog_list_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_list_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_list_sample_3.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_list_sample_4.gif" /></td>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/dark/dialog_list_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_list_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_list_sample_3.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_list_sample_4.gif" /></td>
  </tr>
</table>


## Input Dialog / View
The input dialog / view allows you to build up a small form of various components to collect quickly information.
</br>
<table>
  <tr>
    <th>Sample 1</th>
    <th>Sample 2</th>
    <th>Sample 3</th>
    <th>Sample 4</th>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/light/dialog_input_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_input_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_input_sample_3.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_input_sample_4.gif" /></td>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/dark/dialog_input_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_input_sample_2.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_input_sample_3.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_input_sample_4.gif" /></td>
  </tr>
</table>

## Emoji Dialog / View
The emoji dialog / view allows you to pick an emoji.
</br>
<table>
  <tr>
    <th>Sample 1</th>
    <th>Sample 2</th>
    <th></th>
    <th></th>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/light/dialog_emoji_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/light/dialog_emoji_sample_2.gif" /></td>
  </tr>
  <tr>
    <td width="25%"><img src="docs/res/dark/dialog_emoji_sample_1.gif" /></td>
    <td width="25%"><img src="docs/res/dark/dialog_emoji_sample_2.gif" /></td>
  </tr>
</table>

Credits to [Vannik Tech's Emoji library](https://github.com/vanniktech/Emoji) - this module makes use of that lib.

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

- [Mangata (Soon)](http://mangata-mk.com)

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
