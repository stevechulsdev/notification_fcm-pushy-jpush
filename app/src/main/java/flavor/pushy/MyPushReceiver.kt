package flavor.pushy

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import me.pushy.sdk.Pushy

class MyPushReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var notiTitle: String = "empty"
        var notiContents: String = "empty"

        intent?.getStringExtra("message").let {
            notiTitle = intent!!.getStringExtra("message")
        }

        var builder = NotificationCompat.Builder(context)
            .setAutoCancel(true)
            .setSmallIcon(android.R.mipmap.sym_def_app_icon)
            .setContentTitle(notiTitle)
            .setContentText(notiContents)

        Pushy.setNotificationChannel(builder, context)

        if(Build.VERSION.SDK_INT >= 23)
        {
            var notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(1, builder.build())
        }
    }
}