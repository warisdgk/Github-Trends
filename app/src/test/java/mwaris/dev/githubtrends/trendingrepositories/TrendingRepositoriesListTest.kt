package mwaris.dev.githubtrends.trendingrepositories

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.base.TestNetworkMonitor
import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class TrendingRepositoriesListTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun noTrendingRepositoriesAvailable() = runTest {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        try {
            val trendingListRepository = TrendingListRepository()
            val savedStateHandle = SavedStateHandle()
            val testNetworkMonitor = TestNetworkMonitor()
            val viewModel =
                TrendingRepositoriesListingViewModel(
                    trendingListRepository,
                    savedStateHandle,
                    testDispatcher,
                    testNetworkMonitor
                )

            trendingListRepository.signalEmptyData(true)
            viewModel.getTrendingGithubRepositoriesList()

            assertEquals(
                TrendingRepositoriesScreenState(),
                viewModel.dataState.value
            )
        } finally {
            Dispatchers.resetMain()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun someTrendingRepositoriesAvailable() = runTest {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        try {
            val listOfTrendingRepositories = listOf(
                Repository(
                    "23096959",
                    "go",
                    "golang/go",
                    "https://avatars.githubusercontent.com/u/4314092?v=4",
                    "The Go programming language"
                ),
                Repository(
                    "34526884",
                    "ant-design",
                    "ant-design/ant-design",
                    "https://avatars.githubusercontent.com/u/12101536?v=4",
                    "An enterprise-class UI design language and React UI library"
                ),
                Repository(
                    "44838949",
                    "swift",
                    "apple/swift",
                    "https://avatars.githubusercontent.com/u/10639145?v=4",
                    "The Swift Programming Language"
                ),
                Repository(
                    "3638964",
                    "ansible",
                    "ansible/ansible",
                    "https://avatars.githubusercontent.com/u/1507452?v=4",
                    "Ansible is a radically simple IT automation platform that makes your applications and systems easier to deploy and maintain. Automate everything from code deployment to network configuration to cloud management, in a language that approaches plain English, using SSH, with no agents to install on remote systems. https://docs.ansible.com."
                ),
            )

            val trendingListRepository = TrendingListRepository()
            val testNetworkMonitor = TestNetworkMonitor()
            val savedStateHandle = SavedStateHandle()
            val viewModel =
                TrendingRepositoriesListingViewModel(
                    trendingListRepository,
                    savedStateHandle,
                    testDispatcher,
                    testNetworkMonitor
                )

            trendingListRepository.signalEmptyData(false)
            viewModel.getTrendingGithubRepositoriesList()

            assertEquals(
                TrendingRepositoriesScreenState(listOfRepositories = listOfTrendingRepositories),
                viewModel.dataState.value
            )
        } finally {
            Dispatchers.resetMain()
        }
    }
}