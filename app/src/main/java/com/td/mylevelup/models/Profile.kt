package com.td.mylevelup.models

import android.support.annotation.DrawableRes

data class Profile(val name: String,
                   val age: Int,
                   val gender: String,
                   val occupation: String?,
                   val habitationStatus: String,
                   val id: String,
                   @DrawableRes val profilePictureID: Int)