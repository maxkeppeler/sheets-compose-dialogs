package com.mk.sheets

import android.os.Bundle
import android.util.Range
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.calendar.models.CalendarTimeline
import com.mk.sheets.ui.theme.SheetsComposeTheme
import java.time.LocalDate
import kotlin.random.Random
import kotlin.random.nextInt

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

                    val dialogVisible = remember { mutableStateOf(true) }

                    val date = remember { mutableStateOf(LocalDate.now()) }
                    Text(
                        text = date.value.toString(),
                        modifier = Modifier.clickable { dialogVisible.value = true })

                    val disabledDates = listOf(
                        LocalDate.now().minusDays(Random.nextInt(60).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                        LocalDate.now().minusDays(Random.nextInt(30).toLong()),
                    )

                    CalendarDialog(
                        show = dialogVisible,
                        config = CalendarConfig(
                            minYear =  1900,
                            maxYear = 2100,
//                            monthSelection = true,
                            yearSelection = true,
                            monthSelection = true,
//                            disabledTimeline = CalendarTimeline.FUTURE,
                            style = CalendarStyle.WEEK,
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