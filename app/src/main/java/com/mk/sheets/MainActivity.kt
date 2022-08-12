package com.mk.sheets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.Header
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.color.ColorDialog
import com.maxkeppeler.sheets.color.models.*
import com.maxkeppeler.sheets.info.InfoDialog
import com.maxkeppeler.sheets.info.models.Body
import com.maxkeppeler.sheets.info.models.InfoSelection
import com.mk.sheets.ui.theme.SheetsComposeTheme
import java.time.LocalDate
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

                    val infoDialogVisible = remember { mutableStateOf(true) }

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

                    }


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