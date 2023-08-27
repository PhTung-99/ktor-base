package com.example.features.user.constants

import com.example.constants.BaseMessageCode

object UserMessageCode {
    const val featureCode = "user"

    // authentication_EMAIL-USED_0
    const val USER_NOT_FOUND = "${featureCode}_USER-NOT-FOUND_${BaseMessageCode.FAIL}"

}