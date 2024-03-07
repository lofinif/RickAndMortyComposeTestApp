package com.example.rickandmortycomposetestapp.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortycomposetestapp.data.characterdetails.CharacterDetailsRepositoryImpl
import com.example.rickandmortycomposetestapp.data.characterdetails.dto.CharacterDetailsItem
import com.example.rickandmortycomposetestapp.data.network.RickAndMortyApi
import com.example.rickandmortycomposetestapp.data.characterlist.CharacterListRepositoryImpl
import com.example.rickandmortycomposetestapp.data.characterlist.CharacterListPagingSource
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.domain.repository.CharacterDetailsRepository
import com.example.rickandmortycomposetestapp.domain.repository.CharacterListRepository
import com.example.rickandmortycomposetestapp.ui.BaseMapper
import com.example.rickandmortycomposetestapp.ui.characterdetails.mapper.CharacterDetailsItemToCharacterDetailsModelMapper
import com.example.rickandmortycomposetestapp.ui.characterdetails.model.CharacterDetailsModel
import com.example.rickandmortycomposetestapp.ui.characterlist.mapper.ResultCharacterItemsToResultCharacterModelMapper
import com.example.rickandmortycomposetestapp.ui.characterlist.model.ResultCharacterModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class Mapper{
    @Binds
    abstract fun provideCharacterDetailsMapper(mapper: CharacterDetailsItemToCharacterDetailsModelMapper): BaseMapper<CharacterDetailsItem, CharacterDetailsModel>

    @Binds
    abstract fun provideResultMapper(mapper: ResultCharacterItemsToResultCharacterModelMapper): BaseMapper<ResultsCharacterItems, ResultCharacterModel>

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun provideCharacterListRepo(characterListScreenRepositoryImpl: CharacterListRepositoryImpl): CharacterListRepository

    @Binds
    abstract fun provideDetailsRepo(characterDetailsRepositoryImpl: CharacterDetailsRepositoryImpl): CharacterDetailsRepository
}


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule(){
    @Singleton
    @Provides
    fun provideNetwork(): Retrofit {
        val client = OkHttpClient()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder().client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): RickAndMortyApi =
        RickAndMortyApi.create(retrofit)
}

@Module
@InstallIn(ViewModelComponent::class)
class PagingModule{
    @Provides
    fun providePager(
        api: RickAndMortyApi
    ) = Pager(PagingConfig(pageSize = 20, prefetchDistance = 10)){
        CharacterListPagingSource(api)
    }
}