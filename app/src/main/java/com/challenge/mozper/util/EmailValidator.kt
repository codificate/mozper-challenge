package com.challenge.mozper.util

const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
fun String.isEmailValid(): Boolean {
    return EMAIL_REGEX.toRegex().matches(this);
}