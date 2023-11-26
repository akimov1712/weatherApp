package ru.topbun.weather.data

import ru.topbun.weather.App
import java.lang.RuntimeException

open class AppException : RuntimeException()

class ConnectException: AppException()
class ClientErrorException: AppException()
class ServerErrorException: AppException()

class CachedDataException: AppException()