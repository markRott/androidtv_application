package com.example.learnandroidtvsecondstep.entities.dto

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("results")
    val results: List<MovieDto>?
)