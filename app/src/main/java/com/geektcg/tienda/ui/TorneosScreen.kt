package com.geektcg.tienda.ui

import android.content.Intent
import android.provider.CalendarContract
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Calendar

@Composable
fun TorneosScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Torneos Geek TCG",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Agrega el próximo torneo de la tienda a tu calendario nativo.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                val now = Calendar.getInstance()

                val start = now.clone() as Calendar
                start.add(Calendar.DAY_OF_YEAR, 1)
                start.set(Calendar.HOUR_OF_DAY, 18)
                start.set(Calendar.MINUTE, 0)

                val end = (start.clone() as Calendar).apply {
                    add(Calendar.HOUR_OF_DAY, 2) // torneo dura 2 horas
                }

                val intent = Intent(Intent.ACTION_INSERT).apply {
                    data = CalendarContract.Events.CONTENT_URI
                    putExtra(CalendarContract.Events.TITLE, "Torneo GeekTCG")
                    putExtra(CalendarContract.Events.DESCRIPTION, "Torneo oficial de cartas")
                    putExtra(CalendarContract.Events.EVENT_LOCATION, "Local GeekTCG")
                    putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start.timeInMillis)
                    putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.timeInMillis)
                }

                context.startActivity(intent)
            }
        ) {
            Text("Agregar torneo al calendario")
        }
    }
}
