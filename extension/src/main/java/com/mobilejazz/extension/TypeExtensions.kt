package com.mobilejazz.extension

import java.lang.reflect.Type

val Type.simpleName: String
    get() = toString().substring(this.toString().indexOf(String.space) + 1)