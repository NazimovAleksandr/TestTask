package com.example.exchangerates.json

data class JPY(
    override val CharCode: String,
    override val ID: String,
    override val Name: String,
    override val Nominal: Int,
    override val NumCode: String,
    override val Previous: Double,
    override val Value: Double
) : Currency