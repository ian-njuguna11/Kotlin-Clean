package com.paul.ports

import java.lang.StringBuilder
import java.security.MessageDigest

class UserPorts{
    companion object{

        fun hashPassword(plainTextPassword: String): String{
            val result: String

            result = try{
                val md5 = MessageDigest.getInstance("MD5")
                val md5HashBytes = md5.digest(plainTextPassword.toByteArray()).toTypedArray()
                byteArrayToString(md5HashBytes)
            } catch (e: Exception){
                "Error: ${e.message}"
            }

            return result
        }

        fun byteArrayToString(array: Array<Byte>): String{
            val result = StringBuilder(array.size * 2)

            for (byte in array){

                val toAppend = String.format("%2X", byte).replace(" ", "0")
                result.append(toAppend)
            }

            result.setLength(result.length - 1)
            return result.toString()
        }

    }
}
