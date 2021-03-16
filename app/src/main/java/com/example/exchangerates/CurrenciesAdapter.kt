package com.example.exchangerates

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.exchangerates.json.Currencies
import com.example.exchangerates.json.Currency
import java.text.DecimalFormat

class CurrenciesAdapter(
    data: Currencies,
    private val activity: MainActivity,
    private val spinner: Spinner
) {

    private var currenciesList: ArrayList<Currency> = arrayListOf(
        data.Valute.component1(),
        data.Valute.component2(),
        data.Valute.component3(),
        data.Valute.component4(),
        data.Valute.component5(),
        data.Valute.component6(),
        data.Valute.component7(),
        data.Valute.component8(),
        data.Valute.component9(),
        data.Valute.component10(),
        data.Valute.component11(),
        data.Valute.component12(),
        data.Valute.component13(),
        data.Valute.component14(),
        data.Valute.component15(),
        data.Valute.component16(),
        data.Valute.component17(),
        data.Valute.component18(),
        data.Valute.component19(),
        data.Valute.component20(),
        data.Valute.component21(),
        data.Valute.component22(),
        data.Valute.component23(),
        data.Valute.component24(),
        data.Valute.component25(),
        data.Valute.component26(),
        data.Valute.component27(),
        data.Valute.component28(),
        data.Valute.component29(),
        data.Valute.component30(),
        data.Valute.component31(),
        data.Valute.component32(),
        data.Valute.component33(),
        data.Valute.component34(),
    )
    private val namesList = mutableListOf<String>()

    fun updateData(data: Currencies) {
        currenciesList = arrayListOf(
            data.Valute.component1(),
            data.Valute.component2(),
            data.Valute.component3(),
            data.Valute.component4(),
            data.Valute.component5(),
            data.Valute.component6(),
            data.Valute.component7(),
            data.Valute.component8(),
            data.Valute.component9(),
            data.Valute.component10(),
            data.Valute.component11(),
            data.Valute.component12(),
            data.Valute.component13(),
            data.Valute.component14(),
            data.Valute.component15(),
            data.Valute.component16(),
            data.Valute.component17(),
            data.Valute.component18(),
            data.Valute.component19(),
            data.Valute.component20(),
            data.Valute.component21(),
            data.Valute.component22(),
            data.Valute.component23(),
            data.Valute.component24(),
            data.Valute.component25(),
            data.Valute.component26(),
            data.Valute.component27(),
            data.Valute.component28(),
            data.Valute.component29(),
            data.Valute.component30(),
            data.Valute.component31(),
            data.Valute.component32(),
            data.Valute.component33(),
            data.Valute.component34(),
        )
    }

    fun listFill(): Spinner {
        for (e in currenciesList) {
            namesList.add(e.Name)
        }

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(activity, android.R.layout.simple_spinner_item, namesList)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        return spinner
    }

    fun convert(value: String, selectedItemPosition: Int): Double {
        val result = currenciesList[selectedItemPosition].Value / currenciesList[selectedItemPosition].Nominal * value.toDouble()

        return DecimalFormat("#.#####").format(result).toDouble()
    }
}