package com.example.rafapp.models

data class WeatherStation(
    val id: String,
    val date: String,
    val stationID: String,
    val sensors: Sensors
)

data class Sensors(
    val solarRadiation: SensorData?,
    val solarPanel: SensorData?,
    val precipitation: SensorData?,
    val battery: SensorData?,
    val hCSerialNumber: SensorData?,
    val hCAirTemperature: SensorTempData?,
    val hCRelativeHumidity: SensorTempData?,
    val dewPoint: SensorTempData?,
    val vPD: SensorTempData?,
    val deltaT: SensorTempData?,
    val sunshineDuration: SensorData?,
    val uSonicWindSpeed: SensorTempData?,
    val uSonicWindDir: SensorData?,
    val windGust: SensorTempData?,
    val eT0: SensorData?,
    val windOrientation: SensorData?,
    val sunrise: SensorData?,
    val sunset: SensorData?,
    val midnight: SensorData?
)

data class SensorData(
    val avg: Double?,
    val last: Double?,
    val sum: Double?,
    val time: Double?,
    val result: Double?
)

data class SensorTempData(
    val avg: Double,
    val max: Double,
    val min: Double
)
