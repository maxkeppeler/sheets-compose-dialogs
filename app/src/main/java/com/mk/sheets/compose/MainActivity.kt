package com.mk.sheets.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeker.sheets.core.models.ImageSource
import com.maxkeppeker.sheets.core.models.base.BaseSelection
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.clock_time.ClockTimeDialog
import com.maxkeppeler.sheets.clock_time.models.ClockTimeConfig
import com.maxkeppeler.sheets.clock_time.models.ClockTimeSelection
import com.maxkeppeler.sheets.color.ColorDialog
import com.maxkeppeler.sheets.color.models.*
import com.maxkeppeler.sheets.emoji.EmojiDialog
import com.maxkeppeler.sheets.emoji.models.EmojiCategoryStyle
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.maxkeppeler.sheets.emoji.models.EmojiProvider
import com.maxkeppeler.sheets.emoji.models.EmojiSelection
import com.maxkeppeler.sheets.info.InfoDialog
import com.maxkeppeler.sheets.info.models.Body
import com.maxkeppeler.sheets.info.models.InfoSelection
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.*
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import com.maxkeppeler.sheets.state.models.StateSelection
import com.maxkeppeler.sheets.time.TimeDialog
import com.maxkeppeler.sheets.time.models.TimeConfig
import com.maxkeppeler.sheets.time.models.TimeFormat
import com.maxkeppeler.sheets.time.models.TimeSelection
import com.mk.sheets.compose.ui.theme.SheetsComposeTheme
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SheetsComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val calendarDialogVisible = remember { mutableStateOf(false) }
                    val date = remember { mutableStateOf(LocalDate.now()) }

                    val colorDialogVisible = remember { mutableStateOf(false) }
                    val color = remember { mutableStateOf(Color.White.toArgb()) }

                    val infoDialogVisible = remember { mutableStateOf(false) }
                    val stateDialogVisible = remember { mutableStateOf(false) }
                    val timeDialogVisible = remember { mutableStateOf(false) }
                    val clockTimeDialogVisible = remember { mutableStateOf(false) }
                    val optionDialogVisible = remember { mutableStateOf(false) }
                    val emojiDialogVisible = remember { mutableStateOf(false) }
                    val emojiDialogText = remember { mutableStateOf("") }
                    val listDialogVisible = remember { mutableStateOf(false) }
                    val coreDialogVisible = remember { mutableStateOf(true) }

                    Column(Modifier.padding(24.dp)) {
                        TextButton(onClick = { calendarDialogVisible.value = true }) {
                            Text(text = "Calendar Dialog")
                        }

                        Text(
                            text = date.value.toString(),
                            modifier = Modifier.background(Color(color.value))
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        TextButton(onClick = { colorDialogVisible.value = true }) {
                            Text(text = "Color Dialog")
                        }

                        Spacer(modifier = Modifier.height(16.dp))


                        TextButton(onClick = { infoDialogVisible.value = true }) {
                            Text(text = "Info Dialog")
                        }

                        Spacer(modifier = Modifier.height(16.dp))


                        TextButton(onClick = { stateDialogVisible.value = true }) {
                            Text(text = "State Dialog")
                        }


                        Spacer(modifier = Modifier.height(16.dp))


                        TextButton(onClick = { timeDialogVisible.value = true }) {
                            Text(text = "Time Dialog")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        TextButton(onClick = { clockTimeDialogVisible.value = true }) {
                            Text(text = "Clock Dialog")
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        TextButton(onClick = { optionDialogVisible.value = true }) {
                            Text(text = "Option Dialog")
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        TextButton(onClick = { emojiDialogVisible.value = true }) {
                            Text(text = "Emoji Dialog" + emojiDialogText.value)
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        TextButton(onClick = { listDialogVisible.value = true }) {
                            Text(text = "List Dialog")
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        TextButton(onClick = { coreDialogVisible.value = true }) {
                            Text(text = "Core Dialog")
                        }
                    }

                    CoreDialog(show = coreDialogVisible,
                        selection = object : BaseSelection() {
                            override val withButtonView = true
                            override val positiveButtonText = "hahah"
                        },
                        header = Header.Default(
                            titleText = "Custom Dialog"
                        ),
                        onPositiveValid = { false },
                        body = {
                            Text(text = "Test")
                        }
                    )


                    ListDialog(
                        show = listDialogVisible,
                        selection = ListSelection.Multiple(
                            showCheckBoxes = true,
                            minChoices = 2,
                            maxChoices = 3,
                            options = listOf(
                                ListOption(
//                                    ImageSource(Icons.Rounded.Notifications),
                                    titleText = "Banana",
                                ),
                                ListOption(
//                                    ImageSource(Icons.Rounded.Notifications),
                                    titleText = "Bananaaa",
                                ),
                                ListOption(titleText = "Great")
                            )
                        ) { selectedIndices, selectedOptions ->
                            Log.d(
                                "ListDialog",
                                "selectedIndices:$selectedIndices, selectedOptions:$selectedOptions"
                            )
                        },
                    )

                    val options = listOf(
                        Option(ImageSource(Icons.Rounded.Create), titleText = "Apple"),
                        Option(
                            ImageSource(Icons.Rounded.Notifications),
                            titleText = "Banana",
                            disabled = true
                        ),
                        Option(
                            ImageSource(Icons.Rounded.Create),
                            titleText = "Strawberry_1",
                            selected = true
                        ),
//                        Option(ImageSource(Icons.Rounded.Add), titleText = "Strawberry_2"),
//                        Option(ImageSource(Icons.Rounded.Create), titleText = "Strawberry_3"),
//                        Option(
//                            ImageSource(Icons.Rounded.Create),
//                            titleText = "Pineapple",
//                            details = OptionDetails(
//                                "A rare fruit",
//                                "This is a long beautiful description of a pineapple."
//                            )
//                        ),
                    )
                    OptionDialog(
                        show = optionDialogVisible,
                        selection = OptionSelection.Single(options) { index, option ->
                            Log.d("OptionDialog", "index:$index, options:$option")
                        },
                        config = OptionConfig(style = DisplayMode.LIST),
//                        selection = OptionSelection.Multiple(options, minChoices = 2, maxChoices = 4, maxChoicesStrict = true) { indicies, options ->
//                            Log.d("OptionDialog", "indicies:indicies, options:$options")
//                        }
                    )

                    ClockTimeDialog(
                        show = clockTimeDialogVisible,
                        selection = ClockTimeSelection.HoursMinutes { hour, minute ->
                            Log.d("ClockTime", "hour:$hour,minute:$minute")
                        },
                        config = ClockTimeConfig(
                            currentTime = LocalTime.of(23, 20, 0),
                            is24HourFormat = true
                        ),
                    )

                    TimeDialog(
                        show = timeDialogVisible,
                        selection = TimeSelection {},
                        config = TimeConfig(
                            timeFormat = TimeFormat.HH_MM_SS,
                            currentTime = 45,
                            minTime = 48,
                            maxTime = 180
                        )
                    )

//                    StateDialog(
//                        show = stateDialogVisible,
//                        selection = StateSelection { },
//                        config = StateConfig(
//                            state = State.Loading("Wait a moment", ProgressIndicator.Linear(0.5f)),
//                        )
//                    )


                    StateDialog(
                        show = stateDialogVisible,
                        selection = StateSelection { },
                        config = StateConfig(
//                            state = State.Loading("Wait a moment", ProgressIndicator.Linear())
//                            state = State.Failure(
//                                labelText = "An unknown error has occurred"
//                            )
                            state = State.Success(
                                labelText = "Upload finished, amazing!"
                            )
                        )
                    )

                    InfoDialog(
                        show = infoDialogVisible,
                        header = Header.Default(
                            titleText = "Do you want to work?",
                            subtitleText = "It's essential"
                        ),
                        body = Body.Default(
                            bodyText = "this is a very long text bla bla",
                            postBody = {
                                TextButton(onClick = { /*TODO*/ }) {
                                    Text(text = "Test")
                                }
                            }
                        ),
                        selection = InfoSelection(onPositiveClick = {}, onNegativeClick = {})
                    )


                    EmojiDialog(
                        show = emojiDialogVisible,
                        selection = EmojiSelection.Unicode(
                            withButtonView = false,
                            onPositiveClick = {
                                emojiDialogText.value = it
                            }
                        ),
                        config = EmojiConfig(
                            emojiProvider = EmojiProvider.IOS
                        )
                    )


                    val disabledDates = listOf(
                        LocalDate.now().minusDays(Random.nextInt(60).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                    )

                    ColorDialog(
                        show = colorDialogVisible,
                        selection = ColorSelection(
                            selectedColor = SelectedColor(color.value),
                            onSelectNone = { color.value = Color.White.toArgb() },
                            onSelectColor = { color.value = it },
                        ),
                        config = ColorConfig(
                            templateColors = TemplateColors.ColorsInt(
                                Color.Red.copy(alpha = 0.1f).toArgb(),
                                Color.Red.copy(alpha = 0.3f).toArgb(),
                                Color.Red.copy(alpha = 0.5f).toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                                Color.Red.toArgb(),
                                Color.Green.toArgb(),
                                Color.Yellow.toArgb(),
                                Color.Gray.toArgb(),
                            ),
                            defaultDisplayMode = ColorSelectionMode.TEMPLATE,
//                            displayMode = ColorSelectionMode.TEMPLATE
                        ),
                        header = Header.Default("Select your favorite color", "Choose wisely")
                    )

                    CalendarDialog(
                        show = calendarDialogVisible,
                        config = CalendarConfig(
                            minYear = 1900,
                            maxYear = 2100,
//                            monthSelection = true,
                            yearSelection = true,
                            monthSelection = true,
//                            disabledTimeline = CalendarTimeline.FUTURE,
                            style = CalendarStyle.MONTH,
//                            disabledDates = disabledDates,
//                            disabledTimeline = CalendarTimeline.PAST
                        ),
//                        selection = CalendarSelection.Date(withButtons = true) {
//                            date.value = it
//                        }
                        selection = CalendarSelection.Dates(
                            positiveButtonText = "Yeahh",
                            negativeButtonText = "Nahh"
                        ) {
                            date.value = it.lastOrNull()
                        }
//                        selection = CalendarSelection.Period(
//                            withButtons = Random.nextBoolean(),
//                            selectedRange = Range(LocalDate.now().minusDays(4), LocalDate.now())
//                        ) { start, end ->
//                            date.value = end
//                        }
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SheetsComposeTheme {
        Greeting("Android")
    }
}