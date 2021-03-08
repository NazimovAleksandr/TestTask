package com.example.exchangerates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.exchangerates.json.Currencies
import com.example.exchangerates.json.Valute
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Callback<Currencies> {
    private var data: Currencies? = null
    private lateinit var value: EditText
    private lateinit var result: TextView
    private lateinit var convertButton: Button
    private lateinit var refreshButton: ImageButton
    private lateinit var currenciesList: Spinner

    private lateinit var adapter: CurrenciesAdapter

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.cbr-xml-daily.ru")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CurrenciesService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        value = findViewById(R.id.value)
        result = findViewById(R.id.result)
        convertButton = findViewById(R.id.convertButton)
        refreshButton = findViewById(R.id.refreshButton)
        currenciesList = findViewById(R.id.currenciesList)

        refreshButton.setOnClickListener {
            val call = service.getCurrencies()
            call.enqueue(this)
        }
    }

    override fun onResponse(call: Call<Currencies>, response: Response<Currencies>) {
        data = response.body()
        createAdapter(data!!)
        result.text = data?.Valute?.AMD?.Name
    }

    override fun onFailure(call: Call<Currencies>, t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    private fun createAdapter(data: Currencies) {
        adapter = CurrenciesAdapter(data)
        var s = arrayOf(data.Valute.component1())


    }
}