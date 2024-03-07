package com.example.rickandmortycomposetestapp.ui.characterdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.flowWithLifecycle
import com.example.rickandmortycomposetestapp.BaseTest
import com.example.rickandmortycomposetestapp.data.CallResult
import com.example.rickandmortycomposetestapp.data.characterdetails.dto.CharacterDetailsItem
import com.example.rickandmortycomposetestapp.domain.usecase.GetDetailsUseCase
import com.example.rickandmortycomposetestapp.ui.characterdetails.mapper.CharacterDetailsItemToCharacterDetailsModelMapper
import com.example.rickandmortycomposetestapp.ui.characterdetails.model.CharacterDetailsModel
import com.example.rickandmortycomposetestapp.ui.characterdetails.state.CharacterDetailsScreenState
import com.example.sharedtest.characterDetailsItemMock
import com.example.sharedtest.characterDetailsModelMock
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailsViewModelTest: BaseTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var useCase: GetDetailsUseCase

    @MockK
    private lateinit var mapper: CharacterDetailsItemToCharacterDetailsModelMapper

    @RelaxedMockK
    private lateinit var stateHandle: SavedStateHandle

    private lateinit var viewModel: CharacterDetailsViewModel

    override fun setUp() {
        super.setUp()
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this)
        viewModel = CharacterDetailsViewModel(useCase, mapper, stateHandle)
    }

    @Test
    fun `savedStateHandle is not null, Loaded event is received`() = runTest {
        every { stateHandle.get<CharacterDetailsModel>(any()) }.returns(
            characterDetailsModelMock
        )

        viewModel.fetchDetails("1")

        assertThat(viewModel.screenState).isInstanceOf(CharacterDetailsScreenState.Loaded::class.java)
        assertThat((viewModel.screenState as CharacterDetailsScreenState.Loaded).model).isEqualTo(
                characterDetailsModelMock
            )
    }

    @Test
    fun `savedStateHandle is null and repo call is successful, Loaded events are received`() {
        every { stateHandle.get<CharacterDetailsModel>(any()) }.returns(
            null
        )
        coEvery { useCase.invoke(any()) }.returns(CallResult.Success(
            characterDetailsItemMock
        ))
        every { mapper.map(any()) }.returns(characterDetailsModelMock)

        viewModel.fetchDetails("")

        assertThat((viewModel.screenState as CharacterDetailsScreenState.Loaded).model).isEqualTo(
            characterDetailsModelMock
        )
    }
    @Test
    fun `savedStateHandle is null and repo call is not successful, Error events are received`() {
        every { stateHandle.get<CharacterDetailsModel>(any()) }.returns(
            null
        )
        coEvery { useCase.invoke(any()) }.returns(CallResult.IOError)

        viewModel.fetchDetails("")

        assertThat((viewModel.screenState)).isInstanceOf(CharacterDetailsScreenState.Error::class.java)
    }
}