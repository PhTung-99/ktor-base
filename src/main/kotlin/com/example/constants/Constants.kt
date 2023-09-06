package com.example.constants

import java.nio.file.Paths

object Constants {
    val USER_IMAGES_PATH = "${Paths.get("").toAbsolutePath()}/static-content/images/"
    const val USER_IMAGES_ROUTE = "http://localhost:8080/static-content/images/"
}