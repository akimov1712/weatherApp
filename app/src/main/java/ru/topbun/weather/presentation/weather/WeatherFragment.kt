package ru.topbun.weather.presentation.weather

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.topbun.weather.databinding.FragmentWeatherBinding
import ru.topbun.weather.presentation.base.BaseFragment

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding>(FragmentWeatherBinding::inflate) {

    private val viewModel by viewModels<WeatherViewModel>()

    override fun setListenersInView() {
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.state.collect{
                    when(it){
                        is WeatherState.WeatherItem -> {

                        }
                        is WeatherState.Loading -> {

                        }
                        is WeatherState.ConnectError -> {
                            Toast.makeText(requireContext(), "Ошибка в подключении\nПроверьте интернет подключение", Toast.LENGTH_SHORT).show()
                        }
                        is WeatherState.ClientError -> {
                            Toast.makeText(requireContext(), "Данные не найдены", Toast.LENGTH_SHORT).show()
                        }
                        is WeatherState.ServerError -> {
                            Toast.makeText(requireContext(), "Сервер не отвечает\nПопробуйте через некоторое время", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}