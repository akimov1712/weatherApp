package ru.topbun.weather.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.topbun.weather.data.CachedDataException
import ru.topbun.weather.data.ClientErrorException
import ru.topbun.weather.data.ConnectException
import ru.topbun.weather.data.ServerErrorException
import ru.topbun.weather.domain.useCases.GetCachedWeatherUseCase
import ru.topbun.weather.domain.useCases.GetWeatherCityUseCase
import ru.topbun.weather.domain.useCases.GetWeatherCoordsUseCase
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
            } catch (e: ConnectException) {
                _state.emit(WeatherState.ConnectError)
                getCachedWeather()
            } catch (e: ClientErrorException) {
                _state.emit(WeatherState.ClientError)
            } catch (e: ServerErrorException) {
                _state.emit(WeatherState.ServerError)
            } catch (e: CachedDataException){
                _state.emit(WeatherState.CachedDataError)
            }
        }
    }

    private fun getCachedWeather(){
        viewModelScope.launch {
            try {
                _state.value = WeatherState.Loading
                getCachedWeatherUseCase().collect{
                    _state.emit(WeatherState.WeatherItem(it))
                }
            } catch (e: CachedDataException){
                _state.emit(WeatherState.CachedDataError)
            }
        }
    }

    init {
        getWeatherCity("Moscow")
    }

}