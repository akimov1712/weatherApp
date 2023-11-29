package ru.topbun.weather.presentation.weather

import android.view.View
import android.widget.Toast
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
                                swipeRefresh.isEnabled = true
                                setDataInView(it.weather)
                            }

                            is WeatherState.Loading -> {
                                progressBar.visibility = View.VISIBLE
                                clContent.visibility = View.GONE
                                clError.visibility = View.GONE
                                swipeRefresh.isEnabled = false
                                tvCity.text = "Загрузка"
                            }

                            is WeatherState.ParseBackendResponseError -> {
                                onStateError()
                                tvError.text = "Ошибка. Данные не были обработаны. Попробуйте снова"
                            }

                            is WeatherState.ConnectError -> {
                                Toast.makeText(requireContext(), "Нет подключения к интернету", Toast.LENGTH_SHORT).show()
                            }

                            is WeatherState.BackendError -> {
                                onStateError()
                                tvError.text = "Ошибка в получении данных"
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
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
            setMainInfoWeather(weather)
            setProgressWeather(weather)
            setOtherInfoWeather(weather)
            mapIcon[weather.infoWeather.icon]?.let { drawable ->
                ivWeather.setImageResource(
                    drawable
                )
            }
        }
    }

    private fun setOtherInfoWeather(weather: WeatherEntity) {
        with(binding) {
            tvTempMax.text = weather.temperature.tempMax.toString() + "°"
            tvTempMin.text = weather.temperature.tempMin.toString() + "°"
            tvHumidity.text = weather.temperature.humidity.toString() + "%"
            tvPressure.text = weather.temperature.pressure.toString() + " мм.р.с."
        }
    }

    private fun setMainInfoWeather(weather: WeatherEntity) {
        with(binding) {
            tvCity.text = weather.infoWeather.name
            tvUpdateData.text = convertTimeStampToDate(weather.dataRequestTime)
            tvTempNow.text = "${weather.temperature.temp}°С"
            tvTitleWeather.text = weather.infoWeather.titleWeather.capitalize()
            tvFeelsTemp.text = weather.temperature.feelsTemp.toString() + "°С"
        }
    }

    private fun setProgressWeather(weather: WeatherEntity) {
        with(binding){
            tvSunrise.text = convertTimeStampToTime(weather.sunrise)
            tvSunset.text = convertTimeStampToTime(weather.sunset)
            val timeNowInDay = convertTimeStampToLocalDateTime(System.currentTimeMillis())
            val timeSunsetInDay = convertTimeStampToLocalDateTime(weather.sunset)
            tvDaylight.text =
                convertTimeStampToCountTime(timeSunsetInDay.toLong() - timeNowInDay.toLong())
            val progress = (timeNowInDay / timeSunsetInDay) * 100
            if (progress >= 100){
                viewEndPoint.setBackgroundResource(R.drawable.background_oval_active)
                circularProgressBar.progress = 100f
            } else {
                circularProgressBar.progress = progress
            }
        }

    }
}