package buildlabs.app.controloneandroid.connect.presentation.observer.wan

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.telephony.CellSignalStrength
import android.telephony.TelephonyManager
import android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT
import android.telephony.TelephonyManager.NETWORK_TYPE_CDMA
import android.telephony.TelephonyManager.NETWORK_TYPE_EDGE
import android.telephony.TelephonyManager.NETWORK_TYPE_EHRPD
import android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_0
import android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_A
import android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_B
import android.telephony.TelephonyManager.NETWORK_TYPE_GPRS
import android.telephony.TelephonyManager.NETWORK_TYPE_GSM
import android.telephony.TelephonyManager.NETWORK_TYPE_HSDPA
import android.telephony.TelephonyManager.NETWORK_TYPE_HSPA
import android.telephony.TelephonyManager.NETWORK_TYPE_HSPAP
import android.telephony.TelephonyManager.NETWORK_TYPE_HSUPA
import android.telephony.TelephonyManager.NETWORK_TYPE_LTE
import android.telephony.TelephonyManager.NETWORK_TYPE_NR
import android.telephony.TelephonyManager.NETWORK_TYPE_TD_SCDMA
import android.telephony.TelephonyManager.NETWORK_TYPE_UMTS
import android.telephony.TelephonyManager.NETWORK_TYPE_UNKNOWN
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import buildlabs.app.controloneandroid.R
import kotlinx.coroutines.flow.MutableStateFlow

@RequiresApi(Build.VERSION_CODES.Q)
class WANViewModel (
    private val context: Context
) : ViewModel() {

    val networkType: MutableStateFlow<String> = MutableStateFlow("")

    private val telephonyManager: TelephonyManager = context.getSystemService(
        Context.TELEPHONY_SERVICE) as TelephonyManager

    private val connectivityManager: ConnectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    private fun getSignalStrength(
        context: Context,
        telephonyManagerServiceHandle: TelephonyManager
    ) {
        var sStrength = -1

        when (telephonyManagerServiceHandle.signalStrength?.level) {
            0 -> {
                sStrength = CellSignalStrength.SIGNAL_STRENGTH_NONE_OR_UNKNOWN
            }
            1 -> {
                sStrength = CellSignalStrength.SIGNAL_STRENGTH_POOR
            }
            2 -> {
                sStrength = CellSignalStrength.SIGNAL_STRENGTH_MODERATE // = 2
            }
            3 -> {
                sStrength = CellSignalStrength.SIGNAL_STRENGTH_GOOD
            }
            4 -> {
                sStrength = CellSignalStrength.SIGNAL_STRENGTH_GREAT
            }

        }

    }

    private fun checkNetworkType(
        context: Context,
        telephonyManagerServiceHandle: TelephonyManager
    ) : String {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return ""
        }


        val nwType : String
        when(telephonyManagerServiceHandle.dataNetworkType) {
            NETWORK_TYPE_1xRTT -> {
                nwType = context.getString(R.string._1xrtt)
            }
            NETWORK_TYPE_CDMA -> {
                nwType = context.getString(R.string.cdma)
            }
            NETWORK_TYPE_EDGE -> {
                nwType = context.getString(R.string.edge)
            }

            NETWORK_TYPE_EHRPD -> {
               nwType = context.getString(R.string.ehrpd)
            }

            NETWORK_TYPE_EVDO_0 -> {
                nwType = context.getString(R.string.evdo_0)
            }

            NETWORK_TYPE_EVDO_A -> {
                nwType = context.getString(R.string.evdo_A)
            }

            NETWORK_TYPE_EVDO_B -> {
               nwType = context.getString(R.string.evdo_B)
            }

            NETWORK_TYPE_GPRS -> {
                nwType = context.getString(R.string.gprs)
            }

            NETWORK_TYPE_GSM -> {
                nwType = "GSM"
            }

            NETWORK_TYPE_HSDPA -> {
                nwType = context.getString(R.string.hsdpa)
            }

            NETWORK_TYPE_HSPA -> {
                nwType = context.getString(R.string.hspa)
            }

            NETWORK_TYPE_HSPAP -> {
                nwType = context.getString(R.string.hspap)
            }

            NETWORK_TYPE_HSUPA -> {
                nwType = context.getString(R.string.hsupa)
            }
 
            NETWORK_TYPE_LTE -> {
                nwType = context.getString(R.string.lte)
            }

            NETWORK_TYPE_NR -> {
                nwType = context.getString(R.string.nr)
            }

            NETWORK_TYPE_TD_SCDMA -> {
                nwType = context.getString(R.string.scdma)
            }

            NETWORK_TYPE_UMTS -> {
                nwType = context.getString(R.string.umts)
            }

            else -> {
                nwType = NETWORK_TYPE_UNKNOWN.toString()
            }
        }

        return nwType

    }
}


