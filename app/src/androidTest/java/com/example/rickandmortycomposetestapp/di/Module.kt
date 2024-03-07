package com.example.rickandmortycomposetestapp.di

import com.example.rickandmortycomposetestapp.data.characterdetails.CharacterDetailsRepositoryImplTest
import com.example.rickandmortycomposetestapp.data.characterlist.CharacterListRepositoryImplTest
import com.example.rickandmortycomposetestapp.domain.repository.CharacterDetailsRepository
import com.example.rickandmortycomposetestapp.domain.repository.CharacterListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(components = [ViewModelComponent::class], replaces = [RepositoryModule::class])
abstract class TestRepositoryModule{
    @Binds
    abstract fun provideCharacterListRepo(characterListRepositoryImplTest: CharacterListRepositoryImplTest): CharacterListRepository
    @Binds
    abstract fun provideCharacterDetailsRepo(characterDetailsRepositoryImplTest: CharacterDetailsRepositoryImplTest): CharacterDetailsRepository
}