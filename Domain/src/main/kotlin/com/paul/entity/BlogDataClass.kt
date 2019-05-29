package com.paul.entity

import java.util.*

// U should be a UserDataClass
data class BlogDataClass<U>( val blog_owner: U, val blog_content: String, val created_on: Date )
