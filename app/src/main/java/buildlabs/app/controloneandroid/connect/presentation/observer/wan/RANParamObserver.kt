package buildlabs.app.controloneandroid.connect.presentation.observer.wan

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.telephony.TelephonyManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class RANParamObserver(
    context: Context
) : ParamsObserver {

    private val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observeRSSI(): Flow<ParamsObserver.NetworkSignalStrength> {

        TODO("Not yet implemented")

    }

    override fun observeNetworkType(): Flow<ParamsObserver.NetworkType> {
        TODO("Not yet implemented")

    }

    override fun observeNetworkStatus(): Flow<ParamsObserver.NetworkStatus> {
        return callbackFlow {
            val connectionCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(ParamsObserver.NetworkStatus.AVAILABLE) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(ParamsObserver.NetworkStatus.LOSING) }
                }


                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(ParamsObserver.NetworkStatus.LOST) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(ParamsObserver.NetworkStatus.UNAVAILABLE) }
                }

            }
            connectivityManager.registerDefaultNetworkCallback(connectionCallback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(connectionCallback)
            }
        }.distinctUntilChanged()

    }

}