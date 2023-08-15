package mwaris.dev.githubtrends.utils

interface Parselable<T> {

    fun parse(): T?
}