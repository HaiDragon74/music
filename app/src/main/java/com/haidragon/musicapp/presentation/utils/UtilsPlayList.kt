package com.haidragon.musicapp.presentation.utils

import android.content.Context
import com.haidragon.musicapp.domain.model.Playlist
import com.haidragon.musicapp.presentation.utils.PlaylistDirector
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylistFavorite
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylistMyTopTracks
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylistRecentlyAdd
import com.haidragon.musicapp.presentation.utils.build.BuildPlaylistRecentlyPlayed

class UtilsPlayList(private val context: Context) {
    private val buildPlaylistRecentlyAdd= BuildPlaylistRecentlyAdd()
    private val buildPlaylistRecentlyPlayed= BuildPlaylistRecentlyPlayed()
    private val buildPlaylistMyTopTracks= BuildPlaylistMyTopTracks()
    private val buildPlaylistFavorite= BuildPlaylistFavorite()
    private val playlistDirector= PlaylistDirector()
    fun listPlaylistDefault():List<Playlist>{
        playlistDirector.construct(buildPlaylistFavorite, listOf())
        playlistDirector.construct(buildPlaylistRecentlyAdd,listOf())
        playlistDirector.construct(buildPlaylistRecentlyPlayed,listOf())
        playlistDirector.construct(buildPlaylistMyTopTracks,listOf())
        return listOf(
            buildPlaylistFavorite.resultPlaylist(),
            buildPlaylistRecentlyAdd.resultPlaylist(),
            buildPlaylistRecentlyPlayed.resultPlaylist(),
            buildPlaylistMyTopTracks.resultPlaylist())
    }
}