package com.bilyi.viacheslav.weather.ui.fragment

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bilyi.viacheslav.weather.R
import com.bilyi.viacheslav.weather.data.gson.ForecastWeatherResult
import com.bilyi.viacheslav.weather.data.gson.WeatherResult
import com.bilyi.viacheslav.weather.util.getSuitableColor
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_forecast_weather.*
import kotlinx.android.synthetic.main.item_weather_info.view.*
import javax.inject.Inject

class ForecastWeatherFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ForecastWeatherViewModel

    private lateinit var forecastAdapter: ForecastDayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[ForecastWeatherViewModel::class.java]

        viewModel.forecastWeatherLiveData.observe(this, Observer { weatherResult ->
            showForecastWeather(weatherResult)
        })
    }

    private fun showForecastWeather(forecastResult: ForecastWeatherResult) {
        Log.d("###", "show ${forecastResult.forecastDayResultList.size} items")

        forecastAdapter.weatherResultList.clear()
        forecastAdapter.weatherResultList.addAll(forecastResult.forecastDayResultList)
        forecastAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_forecast_weather, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forecastAdapter = ForecastDayAdapter(resources)

        forecastWeatherRecycler.layoutManager = LinearLayoutManager(context)
        forecastWeatherRecycler.adapter = forecastAdapter
    }


    override fun onResume() {
        super.onResume()
        viewModel.updateForecastWeather()
    }
}


class ForecastDayAdapter(private val resources: Resources) :
    RecyclerView.Adapter<ForecastDayAdapter.ForecastViewHolder>() {

    val weatherResultList = mutableListOf<WeatherResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_info, parent, false)

        return ForecastViewHolder(view)
    }

    override fun getItemCount() = weatherResultList.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val weather = weatherResultList[position]

        holder.temperatureTextView.text = resources.getString(
            R.string.celsius_pattern,
            weather.currentCelsius.toString()
        )

        holder.descriptionTextView.text = weather.description

        holder.rootLayout.setBackgroundColor(
            resources.getColor(weather.currentCelsius.getSuitableColor())
        )
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rootLayout: ViewGroup = itemView.weatherInfoRoot

        val temperatureTextView: TextView = itemView.temperatureTextView

        val descriptionTextView: TextView = itemView.descriptionTextView
    }
}
