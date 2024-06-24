package buildlabs.app.controloneandroid.connect.presentation.observer.wan

import kotlinx.coroutines.flow.Flow

interface ParamsObserver {
    fun observeRSSI(): Flow<NetworkSignalStrength>
    fun observeNetworkType(): Flow<NetworkType>
    fun observeNetworkStatus(): Flow<NetworkStatus>

    enum class NetworkSignalStrength {
        UNKNOWN,            // 0
        POOR,               // 1
        MODERATE,           // 2
        GOOD,               // 3
        GREAT               // 4
    }

    enum class NetworkStatus {
        AVAILABLE,
        UNAVAILABLE,
        LOSING,
        LOST
    }

    enum class NetworkType {
        TYPE_GPRS,
        TYPE_EDGE,
        TYPE_CDMA,
        TYPE_1xRTT,
        TYPE_EVDO_0,
        TYPE_EVDO_A,
        TYPE_EVDO_B,
        TYPE_HSDPA,
        TYPE_HSUPA,
        TYPE_HSPA,
        TYPE_LTE
    }

}