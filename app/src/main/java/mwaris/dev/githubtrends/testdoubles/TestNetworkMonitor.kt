package mwaris.dev.githubtrends.testdoubles

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import mwaris.dev.githubtrends.utils.NetworkMonitor

class TestNetworkMonitor : NetworkMonitor {
    private val connectivityFlow = MutableStateFlow(true)

    override val isOnline: Flow<Boolean> = connectivityFlow

}