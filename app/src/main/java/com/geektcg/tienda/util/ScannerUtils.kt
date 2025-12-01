package com.geektcg.tienda.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun abrirScanner(context: Context) {
    val intent = Intent("com.google.zxing.client.android.SCAN")

    // Si existe una app que soporte escaneo, abrirla
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        // Si NO existe → abrir Google Play para instalar un lector QR
        val market = Intent(Intent.ACTION_VIEW)
        market.data = Uri.parse("market://details?id=com.google.zxing.client.android")
        context.startActivity(market)
    }
}
