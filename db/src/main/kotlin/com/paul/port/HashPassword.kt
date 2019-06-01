package com.paul.port

import org.apache.commons.codec.digest.DigestUtils

class HashPassword {

    companion object {
        fun createHash(plainPassword: String): String{
            return DigestUtils.md5Hex(plainPassword)
        }
    }

}