package com.example.rafapp.models

import com.google.gson.annotations.SerializedName

data class WeatherStation(
    @SerializedName("name") val name: Name,
    @SerializedName("rights") val rights: String,
    @SerializedName("info") val info: Info,
    @SerializedName("starred") val starred: Boolean,
    @SerializedName("dates") val dates: Dates,
    @SerializedName("config") val config: Config,
    @SerializedName("position") val position: Position,
    @SerializedName("meta") val meta: Meta?,
    @SerializedName("metaUnits") val metaUnits: String,
    @SerializedName("networking") val networking: Networking,
    @SerializedName("warnings") val warnings: Warnings,
    @SerializedName("licenses") val licenses: Boolean?
)

data class Name(
    @SerializedName("original") val original: String,
    @SerializedName("custom") val custom: String
)

data class Info(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String
)

data class Dates(
    @SerializedName("min_date") val minDate: String,
    @SerializedName("max_date") val maxDate: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("last_communication") val lastCommunication: String
)

data class Config(
    @SerializedName("type") val type: String,
    @SerializedName("version") val version: String
)

data class Position(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)

data class RainData(
    @SerializedName("vals") val vals: List<Double>,
    @SerializedName("sum") val sum: Double,
    @SerializedName("time_start") val timeStart: Long
)

data class Meta(
    @SerializedName("airTemp") val airTemp: Double,
    @SerializedName("rh") val rh: Double,
    @SerializedName("solarRadiation") val solarRadiation: Double,
    @SerializedName("rain_last") val rainLast: Double,
    @SerializedName("rain1h") val rain1h: Double,
    @SerializedName("windSpeed") val windSpeed: Double,
    @SerializedName("battery") val battery: Double,
    @SerializedName("solarPanel") val solarPanel: Double,
    @SerializedName("time") val time: Double,
    @SerializedName("rain7d") val rain7d: RainData,
    @SerializedName("rain2d") val rain2d: RainData,
    @SerializedName("rain48h") val rain48h: RainData,
    @SerializedName("rain24h") val rain24h: RainData,
    @SerializedName("rainCurrentDay") val rainCurrentDay: RainData
)

data class Modem(
    @SerializedName("brand") val brand: String,
    @SerializedName("type") val type: String,
    @SerializedName("fwversion") val fwVersion: String,
    @SerializedName("sn") val sn: String
)

data class Networking(
    @SerializedName("type") val type: String,
    @SerializedName("mcc") val mcc: String,
    @SerializedName("mnc") val mnc: String,
    @SerializedName("mcc_sim") val mccSim: String,
    @SerializedName("mnc_sim") val mncSim: String,
    @SerializedName("country") val country: String,
    @SerializedName("apn") val apn: String,
    @SerializedName("usernme") val usernme: String,
    @SerializedName("password") val password: String,
    @SerializedName("simid") val simid: String,
    @SerializedName("rssi_pct") val rssiPct: String,
    @SerializedName("imei") val imei: String,
    @SerializedName("modem") val modem: Modem
)

data class Warnings(
    @SerializedName("sensors") val sensors: List<String>,
    @SerializedName("sms_numbers") val smsNumbers: List<String>
)
