package com.example.nmeachecker

interface Session {
    val number: Int
    var aggregate: String
    var distance: Float
    var actualDistance: Float
    var hectarage: Float
    var time: Long
    var actualTime: Long
    var minAllowedSpeed: Float
    var maxAllowedSpeed: Float
    var minActualSpeed: Float
    var avrActualSpeed: Float
    var maxActualSpeed: Float
    var paymentPerKm: Float
    var paymentPerHectare: Float
    var paymentPerHour: Float
}