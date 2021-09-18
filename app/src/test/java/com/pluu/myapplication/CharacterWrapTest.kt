package com.pluu.myapplication

import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterWrapTest {

    @Test
    fun toWrap() {
        // Single line
        assertEquals(
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            CharacterWrap.toWrap("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        )
        assertEquals(
            "abcdefghijklmnopqrstuvwxyz",
            CharacterWrap.toWrap("abcdefghijklmnopqrstuvwxyz")
        )
        assertEquals("0123456789", CharacterWrap.toWrap("0123456789"))
        assertEquals(CharacterWrap.NBSP, CharacterWrap.toWrap(" "))
        assertEquals(CharacterWrap.NBSP, CharacterWrap.toWrap(CharacterWrap.NBSP))
        assertEquals(CharacterWrap.WJ, CharacterWrap.toWrap(CharacterWrap.WJ))
        assertEquals(".${CharacterWrap.WJ}", CharacterWrap.toWrap("."))
        assertEquals(".${CharacterWrap.WJ}", CharacterWrap.toWrap(".${CharacterWrap.WJ}"))

        // Multi-line
        assertEquals("ABC\nDEF", CharacterWrap.toWrap("ABC\nDEF"))
        assertEquals("ABC\n123", CharacterWrap.toWrap("ABC\n123"))
        assertEquals("ABC\n${CharacterWrap.NBSP}", CharacterWrap.toWrap("ABC\n "))
        assertEquals("ABC\n.${CharacterWrap.WJ}", CharacterWrap.toWrap("ABC\n."))

        //
        assertEquals(
            ".${CharacterWrap.WJ}${CharacterWrap.NBSP}",
            CharacterWrap.toWrap(".${CharacterWrap.WJ} ")
        )
        assertEquals(
            ".${CharacterWrap.WJ}${CharacterWrap.NBSP}",
            CharacterWrap.toWrap(".${CharacterWrap.WJ}${CharacterWrap.NBSP}")
        )
    }
}