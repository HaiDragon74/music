package com.haidragon.musicapp.presentation.utils

interface RoomCallback<T> {
    fun onSuccess(result: T?)
    fun onFailure(errorMessage: String)
}
interface InsertCallback : RoomCallback<Unit>
interface UpdaterCallback : RoomCallback<Unit>
interface ReadCallback : RoomCallback<Unit>