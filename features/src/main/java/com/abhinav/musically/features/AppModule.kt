package com.abhinav.musically.features

import com.abhinav.musically.network.repository.ArtistRepository
import com.abhinav.musically.network.repository.ArtistRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/*
* Each Screen should have its own separate module but in this case as only the 1 repos were required
* I went with a single one
* */
@InstallIn(ViewModelComponent::class)
@Module
abstract class AppModule {
    @Binds
    abstract fun bindTaskRepository(repository: ArtistRepositoryImpl): ArtistRepository
}

