package com.example.exchangerates.json

import android.os.Handler
import android.os.Looper
import com.example.exchangerates.MainActivity
import java.io.Serializable

data class Currencies(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Valute,
    override val CharCode: String,
    override val ID: String,
    override val Name: String,
    override val Nominal: Int,
    override val NumCode: String,
    override val Previous: Double,
    override val Value: Double
) : Currency, Serializable