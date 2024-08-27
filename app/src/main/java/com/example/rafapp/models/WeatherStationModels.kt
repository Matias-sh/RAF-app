data class WeatherStation(
    val id: String,
    val date: String,
    val stationID: String?,
    val sensors: Sensors
)

data class Sensors(
    val solarRadiation: SolarRadiation?,
    val solarPanel: SolarPanel?,
    val precipitation: Precipitation?,
    val battery: Battery?,
    val hCSerialNumber: HCSerialNumber?,
    val hCAirTemperature: HCAirTemperature?,
    val hCRelativeHumidity: HCRelativeHumidity?,
    val dewPoint: DewPoint?,
    val vPD: VPD?,
    val deltaT: DeltaT?,
    val sunshineDuration: SunshineDuration?,
    val uSonicWindSpeed: USonicWindSpeed?,
    val uSonicWindDir: USonicWindDir?,
    val windGust: WindGust?,
    val eT0: ET0?,
    val windOrientation: WindOrientation?,
    val sunrise: Sunrise?,
    val sunset: Sunset?,
    val midnight: Midnight?
)

data class SolarRadiation(val avg: Float?)
data class SolarPanel(val last: Float?)
data class Precipitation(val sum: Float?)
data class Battery(val last: Float?)
data class HCSerialNumber(val last: Long?)
data class HCAirTemperature(val avg: Float?, val max: Float?, val min: Float?)
data class HCRelativeHumidity(val avg: Float?, val max: Float?, val min: Float?)
data class DewPoint(val avg: Float?, val min: Float?)
data class VPD(val avg: Float?, val min: Float?)
data class DeltaT(val avg: Float?, val max: Float?, val min: Float?)
data class SunshineDuration(val time: Float?)
data class USonicWindSpeed(val avg: Float?, val max: Float?)
data class USonicWindDir(val last: Float?)
data class WindGust(val max: Float?)
data class ET0(val result: Float?)
data class WindOrientation(val result: Float?)
data class Sunrise(val result: Long?)
data class Sunset(val result: Long?)
data class Midnight(val result: Long?)
