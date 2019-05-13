package flavor.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFcmService : FirebaseMessagingService() {

    val TAG: String = javaClass.simpleName

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.e(TAG, "From: ${remoteMessage?.from}")

        remoteMessage?.data?.isNotEmpty()?.let {
            Log.e(TAG, "Message data playload: ${remoteMessage.data}")
        }

        remoteMessage?.notification?.let {
            Log.e(TAG, "Message Notification Body: ${it.body}")
        }
    }

    override fun onNewToken(token: String?) {
        Log.e(TAG, "Refreshed token: $token")
    }
}