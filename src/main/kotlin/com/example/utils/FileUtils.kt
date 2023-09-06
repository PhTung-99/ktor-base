package com.example.utils

import com.example.constants.Constants
import java.io.File
import java.util.*

fun saveImage(fileBytes: ByteArray, originalFileName: String?): String {
    val fileExtension = originalFileName?.takeLastWhile { it != '.' }
    val fileName = UUID.randomUUID().toString() + "." + fileExtension
    File("${Constants.USER_IMAGES_PATH}$fileName").createNewFile()
    File("${Constants.USER_IMAGES_PATH}$fileName").writeBytes(fileBytes)
    return fileName
}

fun fileInit() {
    val folder = File(Constants.USER_IMAGES_PATH)

    if (!folder.exists()) {
        val folderCreated = folder.mkdirs()
        if (folderCreated) {
            println("Folder Images created successfully.")
        } else {
            println("Failed to create folder image file")
        }
    } else {
        println("Folder images already exists.")
    }
}