package com.haidragon.musicapp.service;

import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = MusicService.class
)
@GeneratedEntryPoint
@InstallIn(ServiceComponent.class)
public interface MusicService_GeneratedInjector {
  void injectMusicService(MusicService musicService);
}
