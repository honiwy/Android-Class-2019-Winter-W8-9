package app.appworks.school.stylish.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data  class Message (var chatuser:String,
                     var message:String
                     ):Parcelable
