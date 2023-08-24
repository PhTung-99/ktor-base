package com.example.features.authentication.constants

import com.example.data.models.BaseMessageCode

object AuthenticationMessageCode {
    const val featureCode = "authentication"

    // authentication_C_1
    const val SIGNUP_SUCCESS = "${featureCode}_${BaseMessageCode.CREATE}_${BaseMessageCode.SUCCESS}"

    // authentication_C_0
    const val SIGNUP_FAIL = "${featureCode}_${BaseMessageCode.CREATE}_${BaseMessageCode.FAIL}"

    // authentication_EMAIL-USED_0
    const val EMAIL_USED = "${featureCode}_EMAIL-USED_${BaseMessageCode.FAIL}"

    // authentication_EMAIL-NOT-FOUND_0
    const val EMAIL_NOT_FOUND = "${featureCode}_EMAIL-NOT-FOUND_${BaseMessageCode.FAIL}"

    // authentication_EMAIL-NOT-FOUND_0
    const val PASSWORD_WRONG = "${featureCode}_PASSWORD-WRONG_${BaseMessageCode.FAIL}"



}