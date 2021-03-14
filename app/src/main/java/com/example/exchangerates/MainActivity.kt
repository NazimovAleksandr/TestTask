package com.example.exchangerates

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangerates.json.Currencies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*

class MainActivity : AppCompatActivity(), Callback<Currencies> {
    private val dataFile = File("data.bin")
    private var data: Currencies? = null
    private var adapter: CurrenciesAdapter? = null

    private lateinit var value: EditText
    private lateinit var result: TextView
    private lateinit var copyButton: Button
    private lateinit var convertButton: Button
    private lateinit var refreshButton: ImageButton
    private lateinit var currenciesList: Spinner

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
        copyButton = findViewById(R.id.copyButton)
        convertButton = findViewById(R.id.convertButton)
        refreshButton = findViewById(R.id.refreshButton)
        currenciesList = findViewById(R.id.currenciesList)

        try {
            ObjectInputStream(this.openFileInput(dataFile.name)).use {
                data = it.readObject() as Currencies?
            }
        } catch (ignore: FileNotFoundException) {
        }

        if (data != null) {
            createAdapter(data!!, currenciesList)
        }

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                mainHandler.postDelayed(this, 300000)
                update()
            }
        })

        refreshButton.setOnClickListener {
            update()
        }

        convertButton.setOnClickListener {
            if (data != null && value.text.isNotEmpty()) {
                result.text = adapter?.convert(
                    value.text.toString(),
                    currenciesList.selectedItemPosition
                ).toString()
            }

            if (value.text.isEmpty()) {
                result.text = "0"
            }
        }

        copyButton.setOnClickListener {
            val clipboard: ClipboardManager =
                this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("", result.text.toString())
            clipboard.setPrimaryClip(clip)
        }
    }

    override fun onResponse(call: Call<Currencies>, response: Response<Currencies>) {
        data = response.body()
        createAdapter(data!!, currenciesList)

        val file: FileOutputStream = this.openFileOutput(dataFile.name, Context.MODE_PRIVATE)

        ObjectOutputStream(file).use { out ->
            out.writeObject(data)
        }
    }

    override fun onFailure(call: Call<Currencies>, t: Throwable) {
        Toast.makeText(this, "Ошибка подключения", Toast.LENGTH_SHORT).show()
    }

    private fun createAdapter(data: Currencies, spinner: Spinner) {
        if (adapter == null) {
            adapter = CurrenciesAdapter(this, data, spinner)
        }

        adapter?.data = data
        adapter?.listFill()
    }

    fun update() {
        val call = service.getCurrencies()
        call.enqueue(this@MainActivity)
    }
}