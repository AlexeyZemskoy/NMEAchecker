package com.example.nmeachecker

object Mock {
    val movementHistory = Array(10) { i -> Pair(i.toFloat(), i.toFloat()) } +
            Array(20) { i -> Pair(i.toFloat() + 10f, 10f) } +
            arrayOf(Pair(30.6f, 10.4f), Pair(31f, 11f),
                Pair(31.4f, 11.6f), Pair(31.8f, 12.2f),
                Pair(32.2f, 12.8f), Pair(32f, 13.6f),
                Pair(31.5f, 14.5f), Pair(30.5f, 15.2f),
                Pair(29.5f, 15.5f), Pair(28.5f, 15.5f),
                Pair(27.5f, 15.5f), Pair(26.5f, 15.5f),
                Pair(26f, 15f), Pair(25f, 14f),
                Pair(24f, 13f), Pair(23f, 12f),
                Pair(22f, 11f), Pair(21f, 10f),
                Pair(20f, 9f), Pair(19f, 8f), Pair(18f, 7f)
            )
}
