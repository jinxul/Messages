package com.givekesh.messages.di.modules

import android.content.ContentResolver
import com.givekesh.messages.data.mappers.MessagesMapper
import com.givekesh.messages.data.source.local.ContentApi
import com.givekesh.messages.data.source.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideContentApi(
        contentResolver: ContentResolver,
        messagesMapper: MessagesMapper
    ) = ContentApi(contentResolver, messagesMapper)

    @Singleton
    @Provides
    fun provideMainRepository(
        contentApi: ContentApi
    ): MainRepository = MainRepository(contentApi)
}