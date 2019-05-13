package com.stevechulsdev.notification

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import me.pushy.sdk.Pushy



class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG, "Flavor: ${BuildConfig.FLAVOR}")

        var flavorString = BuildConfig.FLAVOR
        flavorString.let {
            if(BuildConfig.FLAVOR.equals("fcm"))
            {
                Log.e(TAG, "is fcm")

                FirebaseInstanceId.getInstance().instanceId
                    .addOnCompleteListener(OnCompleteListener { task ->
                        if(!task.isSuccessful)
                        {
                            Log.e(TAG, "getInstanceId failed, ${task.exception}")
                            return@OnCompleteListener
                        }

                        val token = task.result?.token

                        Log.e(TAG, "MyFcmToken: $token")
                    })
            }

            if(BuildConfig.FLAVOR.equals("jpush"))
            {
                Log.e(TAG, "is jpush")
            }

            if (BuildConfig.FLAVOR.equals("pushy"))
            {
                Log.e(TAG, "is pushy")

//                var deviceToken = Pushy.register(applicationContext)
//
//                Log.e(TAG, "pushy token: $deviceToken")

                Pushy.listen(applicationContext)

                if(ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
                }

                if(!Pushy.isRegistered(applicationContext))
                {
                    flavor.pushy.MyPushyAsyncTask(applicationContext).execute()
                }
            }
        }
    }
}
