package com.example.networkmodule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
	val data: List<DataItem?>? = null,
	val meta: Meta? = null
) : Parcelable

@Parcelize
data class Pagination(
	val total: Int? = null,
	val pages: Int? = null,
	val limit: Int? = null,
	val links: Links? = null,
	val page: Int? = null
) : Parcelable

@Parcelize
data class DataItem(
	val userId: Int? = null,
	val id: Int? = null,
	val title: String? = null,
	val body: String? = null
) : Parcelable

@Parcelize
data class Meta(
	val pagination: Pagination? = null
) : Parcelable

@Parcelize
data class Links(
	val next: String? = null,
	val current: String? = null,
	val previous: String? = null
) : Parcelable
