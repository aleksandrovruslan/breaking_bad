package com.aleksandrov.breakingbad.domain.models

data class Character(
    var char_id: Int,
    var name: String,
    var birthday: String,
    var occupation: List<String>,
    var img: String,
    var status: String,
    var nickname: String,
    var appearance: List<Int>,
    var portrayed: String,
    var category: String,
    var better_call_saul_appearance: List<Int>?,
)
