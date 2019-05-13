package com.stevechulsdev.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG, "Flavor: ${BuildConfig.FLAVOR}")

        var flavor = BuildConfig.FLAVOR
        flavor.let {
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
            }
        }
    }
}
