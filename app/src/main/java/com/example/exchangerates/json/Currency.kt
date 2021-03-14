package com.example.exchangerates.json

import java.io.Serializable

interface Currency : Serializable {
    val CharCode: String
    val ID: String
    val Name: String
    val Nominal: Int
    val NumCode: String
    val Previous: Double
    val Value: Double
}