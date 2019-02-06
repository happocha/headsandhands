package ru.potelov.headsandhands.data.model

class Weather {
    lateinit var description: String
    var time: Long = 0
    var temp: Float = 0.toFloat()
    var speedWind: Float = 0.toFloat()
    var forecast: List<Weather> = listOf()

    constructor(description: String, time: Long, temp: Float, speedWind: Float) {
        this.description = description
        this.time = time
        this.temp = temp
        this.speedWind = speedWind
    }

    constructor(forecast: List<Weather>)

    override fun toString(): String {
        return "Weather{" +
                "description='" + description + '\''.toString() +
                ", time='" + time + '\''.toString() +
                ", temp=" + temp +
                ", speedWind=" + speedWind +
                '}'.toString()
    }
}


