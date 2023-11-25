package ru.topbun.weather.presentation.weather

import android.os.Bundle
import android.util.Log
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setListenersInView() {
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.state.collect{
                    when(it){
                        is WeatherState.WeatherItem -> {
                            Log.d("sis", it.weather.toString())
                        }
                        is WeatherState.Loading -> {
                            Log.d("sis", "Загрузка")
                        }
                        is WeatherState.ConnectError -> {
                            Log.d("sis", "Connect error")
                        }
                        is WeatherState.ClientError -> {
                            Log.d("sis", "Client error")
                        }
                        is WeatherState.ServerError -> {
                            Log.d("sis", "Server error")
                        }
                    }
                }
            }
        }
    }
}