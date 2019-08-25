package com.qezhhnjy.login.util

class KotlinUtil {

    companion object {
        fun max(a: Int, b: Int) = if (a > b) a else b

        fun min(a: Int, b: Int) = if (a < b) a else b

        fun contain(a: Int, min: Int, max: Int) = a in min..max

        fun exclude(a: Int, min: Int, max: Int) = a !in min..max
    }
}

fun max(a: Int, b: Int) = if (a > b) a else b

fun min(a: Int, b: Int) = if (a < b) a else b

fun contain(a: Int, min: Int, max: Int) = a in min..max

fun exclude(a: Int, min: Int, max: Int) = a !in min..max
