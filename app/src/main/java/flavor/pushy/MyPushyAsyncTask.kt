package flavor.pushy

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import me.pushy.sdk.Pushy
import java.lang.Exception
import java.net.URL

class MyPushyAsyncTask(context: Context) : AsyncTask<Void, Void, Exception>() {

    private val TAG: String = javaClass.simpleName

    private val context: Context = context

    private val API_KEY: String = "6e11b5ff73ac61f1fc1866739bad4e2a624cfa9222e7a0c97b87f415d0b67e5f"

    override fun doInBackground(vararg params: Void?): Exception? {
        try {
            var  deviceToken = Pushy.register(context)

            Log.e(TAG, "pushy deviceToken: $deviceToken")

            URL("https://$API_KEY/register/device?token=$deviceToken").openConnection()
        }
        catch (exception: Exception)
        {
            return exception
        }

        // Success
        return null
    }

    override fun onPostExecute(exception: Exception?) {
        exception.let {
            Toast.makeText(context, "exception: $exception", Toast.LENGTH_SHORT)
            return
        }
    }
}