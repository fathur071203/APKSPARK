package com.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Username(
    var tokenName :String = "",
) : Parcelable