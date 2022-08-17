package com.mobilejazz.network.mapper

interface BaseMapper<Remote, Local> {
    fun map(remote: Remote): Local
}