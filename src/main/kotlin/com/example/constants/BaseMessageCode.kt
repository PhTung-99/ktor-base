package com.example.constants

object BaseMessageCode {
    const val CREATE = "C"
    const val UPDATE = "U"
    const val READ = "R"
    const val DELETE = "D"

    const val SUCCESS = "1"
    const val FAIL = "0"

    const val READ_SUCCESS = "${READ}_$SUCCESS"
}