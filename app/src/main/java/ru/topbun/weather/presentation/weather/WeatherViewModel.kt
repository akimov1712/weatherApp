package ru.topbun.weather.presentation.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.topbun.weather.data.BackendException
import ru.topbun.weather.data.CachedDataException
import ru.topbun.weather.data.ParseBackendResponseException
import ru.topbun.weather.domain.useCases.GetCachedWeatherUseCase
import ru.topbun.weather.domain.useCases.GetWeatherCityUseCase
import ru.topbun.weather.domain.useCases.GetWeatherCoordsUseCase
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherCityUseCase: GetWeatherCityUseCase,
    private val getWeatherCoordsUseCase: GetWeatherCoordsUseCase,
    private val getCachedWeatherUseCase: GetCachedWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val state get() = _state.asStateFlow()

    fun getWeatherCity(city: String) {
        viewModelScope.launch {
            try {
                _state.value = WeatherState.Loading
                getWeatherCityUseCase(city).collect {
                    _state.emit(WeatherState.WeatherItem(it))
                }
                Log.d("sis", "Успешно")
            } catch (e: ConnectException) {
                _state.emit(WeatherState.ConnectError)
                getCachedWeather()
                Log.d("sis", "Ошибка в подключении")
            } catch (e: BackendException) {
                _state.emit(WeatherState.BackendError("${e.code}: ${e.message}"))
            } catch (e: ParseBackendResponseException) {
                _state.emit(WeatherState.ParseBackendResponseError)
            } catch (e: CachedDataException) {
                _state.emit(WeatherState.CachedDataError)
            }
        }
    }

    private fun getCachedWeather() {
        Log.d("sis", "Получение кешированных данных")
        viewModelScope.launch {
            try {
                Log.d("sis", "Закешированные данные получены")
                _state.value = WeatherState.Loading
                getCachedWeatherUseCase().collect {
                    _state.emit(WeatherState.WeatherItem(it))
                }
            } catch (e: CachedDataException) {
                Log.d("sis", "Кешированные данные не найдены")
                _state.emit(WeatherState.CachedDataError)
            }
        }
    }

    init {
        getWeatherCity("Moscoавпw")
    }

}