package com.example.rickandmortycomposetestapp.ui

interface BaseMapper<A, B> {
    fun map(item: A): B
}