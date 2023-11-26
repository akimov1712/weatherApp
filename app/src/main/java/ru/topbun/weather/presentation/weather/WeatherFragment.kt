package ru.topbun.weather.presentation.weather

import android.content.res.ColorStateList
import android.util.Half.toFloat
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.topbun.weather.Const.mapIcon
import ru.topbun.weather.R
import ru.topbun.weather.databinding.FragmentWeatherBinding
import ru.topbun.weather.domain.entity.WeatherEntity
import ru.topbun.weather.presentation.base.BaseFragment
import ru.topbun.weather.utils.convertTimeStampToCountTime
import ru.topbun.weather.utils.convertTimeStampToDate
import ru.topbun.weather.utils.convertTimeStampToLocalDateTime
import ru.topbun.weather.utils.convertTimeStampToTime
import java.util.Date

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding>(FragmentWeatherBinding::inflate) {

    private val viewModel by viewModels<WeatherViewModel>()

    override fun setViews() {
        super.setViews()
        setRefreshData()
    }

    override fun observeViewModel() {
        with(binding){
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.state.collect {
                        when (it) {
                            is WeatherState.WeatherItem -> {
                                progressBar.visibility = View.GONE
                                clError.visibility = View.GONE
                                clContent.visibility = View.VISIBLE
                                swipeRefresh.isRefreshing = false
                                setDataInView(it.weather)
                            }

                            is WeatherState.Loading -> {
                                progressBar.visibility = View.VISIBLE
                                clContent.visibility = View.GONE
                                clError.visibility = View.GONE
                                tvCity.text = "Загрузка"
                            }

                            is WeatherState.ClientError -> {
                                onStateError()
                                tvError.text = "Данные не найдены"
                            }

                            is WeatherState.ConnectError -> {
                                Toast.makeText(requireContext(), "Нет подключения к интернету", Toast.LENGTH_SHORT).show()
                            }

                            is WeatherState.ServerError -> {
                                onStateError()
                                tvError.text = "Сервер не отвечает\nПопробуйте через некоторое время"
                            }

                            is WeatherState.CachedDataError -> {
                                onStateError()
                                tvError.text = "Ошибка\nПроверьте инетернет подключение"
                            }
                        }
                    }
                }
            }
        }
    }

    override fun setListenersInView() {
        with(binding){
            btnRefresh.setOnClickListener {
                viewModel.getWeatherCity("Moschfgow")
            }
        }
    }

    private fun setRefreshData(){
        with(binding.swipeRefresh){
            setColorSchemeResources(R.color.backgroundCard)
            setOnRefreshListener {
                viewModel.getWeatherCity("Severnoe")
            }
        }
    }

    private fun onStateError(){
        with(binding){
            progressBar.visibility = View.GONE
            clContent.visibility = View.GONE
            clError.visibility = View.VISIBLE
            tvCity.text = "Ошибка"
        }
    }

    private fun setDataInView(weather: WeatherEntity) {
        with(binding){
            tvCity.text = weather.infoWeather.name
            tvUpdateData.text = convertTimeStampToDate(weather.dataRequestTime)

            tvTempNow.text = "${weather.temperature.temp}°С"
            tvTitleWeather.text = weather.infoWeather.titleWeather.capitalize()
            tvFeelsTemp.text = weather.temperature.feelsTemp.toString() + "°С"

            tvSunrise.text = convertTimeStampToTime(weather.sunrise)
            tvSunset.text = convertTimeStampToTime(weather.sunset)

            val timeNowInDay = convertTimeStampToLocalDateTime(System.currentTimeMillis())
            val timeSunsetInDay = convertTimeStampToLocalDateTime(weather.sunset)

            tvDaylight.text = convertTimeStampToCountTime((timeSunsetInDay - timeNowInDay).toLong())
            val progress = (timeNowInDay / timeSunsetInDay) * 100
            if (progress >= 100) viewEndPoint.setBackgroundResource(R.drawable.background_oval_active)
            circularProgressBar.progress = progress
            mapIcon[weather.infoWeather.icon]?.let { drawable ->
                ivWeather.setImageResource(
                    drawable
                )
            }
        }
    }
}