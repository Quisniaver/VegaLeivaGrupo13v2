package com.geektcg.tienda.util

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import java.util.Calendar


// Abre el calendario nativo del teléfono
fun abrirCalendarioNativo(context: Context) {
    val intent = Intent(Intent.ACTION_MAIN).apply {
        addCategory(Intent.CATEGORY_APP_CALENDAR)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    // Si existe una app de calendario, la abre
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        // Si no existe, abre Google Calendar en Play Store
        val fallbackIntent = Intent(Intent.ACTION_VIEW).apply {
            data = android.net.Uri.parse("market://details?id=com.google.android.calendar")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(fallbackIntent)
    }
}



// Agrega un torneo como evento en el calendario nativo
fun agregarEventoCalendario(
    context: Context,
    titulo: String,
    descripcion: String,
    inicioMillis: Long,
    finMillis: Long
) {
    val intent = Intent(Intent.ACTION_INSERT).apply {
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, titulo)
        putExtra(CalendarContract.Events.DESCRIPTION, descripcion)
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, inicioMillis)
        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, finMillis)
    }
    context.startActivity(intent)
}
fun agregarEventoTorneoAutomatico(context: Context) {
    // Torneo ejemplo: 20 marzo 2026 a las 15:00 — 17:00

    val inicio = Calendar.getInstance().apply {
        set(2026, Calendar.MARCH, 20, 15, 0)
    }.timeInMillis

    val fin = Calendar.getInstance().apply {
        set(2026, Calendar.MARCH, 20, 17, 0)
    }.timeInMillis

    val intent = Intent(Intent.ACTION_INSERT).apply {
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, "Torneo Pokémon TCG")
        putExtra(CalendarContract.Events.EVENT_LOCATION, "Geek TCG Store")
        putExtra(CalendarContract.Events.DESCRIPTION, "Competencia oficial — Categoría Libre")
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, inicio)
        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, fin)
    }

    context.startActivity(intent)
}
