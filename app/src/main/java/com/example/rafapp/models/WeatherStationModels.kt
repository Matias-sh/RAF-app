package com.example.rafapp.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class WeatherStation(
    @SerializedName("_id") val id: OId,
    @SerializedName("date") val date: DateWrapper,
    @SerializedName("sensors") val sensors: Sensors,
    @SerializedName("stationID") val stationId: OId,
    @SerializedName("createdAt") val createdAt: DateWrapper,
    @SerializedName("updatedAt") val updatedAt: DateWrapper
)

data class OId(
    @SerializedName("\$oid") val oid: String
)

data class DateWrapper(
    @SerializedName("\$date") val date: String
)

data class Sensors(
    val solarRadiation: SensorValue,
    val solarPanel: SensorValue,
    val precipitation: SensorValue,
    val battery: SensorValue,
    val hCSerialNumber: SensorValue,
    val hCAirTemperature: TemperatureSensor,
    val hCRelativeHumidity: TemperatureSensor,
    val dewPoint: SensorValue,
    val vPD: SensorValue,
    val deltaT: TemperatureSensor,
    val sunshineDuration: SensorValue,
    val uSonicWindSpeed: WindSensor,
    val uSonicWindDir: SensorValue,
    val windGust: SensorValue,
    val eT0: SensorValue?,
    val windOrientation: SensorValue,
    val sunrise: SensorValue,
    val sunset: SensorValue,
    val midnight: SensorValue?
)

data class SensorValue(
    val avg: Float? = null,
    val last: Float? = null,
    val sum: Float? = null,
    val max: Float? = null,
    val min: Float? = null,
    val time: Float? = null,
    val result: Float? = null
)

data class TemperatureSensor(
    val avg: Float,
    val max: Float,
    val min: Float
)

data class WindSensor(
    val avg: Float,
    val max: Float
)