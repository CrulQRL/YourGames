package com.faqrulans.core.utils

object KeysManager {

    init {
        System.loadLibrary("keys")
    }

    external fun getPassphrase(): String

    external fun getApiKey(): String

}
