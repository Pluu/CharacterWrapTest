package com.pluu.myapplication

object CharacterWrap {
    val WJ = "\u2060"
    val NBSP = "\u00A0"
    private val regex = "[^a-zA-Z0-9 \n${NBSP}${WJ}](?!${WJ})".toRegex()

    fun toWrap(text: CharSequence): CharSequence {
        return text.replace(regex, "$0${WJ}")
            .replace(" ".toRegex(), NBSP)
    }
}