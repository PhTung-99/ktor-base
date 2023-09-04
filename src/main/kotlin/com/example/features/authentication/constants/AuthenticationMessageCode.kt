package com.example.features.authentication.constants


object AuthenticationMessageCode {
    const val featureCode = "authentication"

    // authentication_C_1
    const val SIGNUP_SUCCESS = "${featureCode}-SIGNUP_SUCCESS"

    // authentication_C_0
    const val SIGNUP_FAIL = "${featureCode}-SIGNUP_FAIL"

    // authentication_EMAIL-USED_0
    const val EMAIL_USED = "${featureCode}-EMAIL_USED"

    // authentication_EMAIL-NOT-FOUND_0
    const val EMAIL_NOT_FOUND = "${featureCode}-EMAIL_NOT_FOUND"

    // authentication_EMAIL-NOT-FOUND_0
    const val PASSWORD_WRONG = "${featureCode}-PASSWORD_WRONG"

    const val INVALID_INFO = "${featureCode}-INVALID_INFO"

    const val INVALID_REFRESH_TOKEN = "${ featureCode}-INVALID_REFRESH_TOKEN"

    const val INVALID_TOKEN = "${ featureCode}-INVALID_TOKEN"

}