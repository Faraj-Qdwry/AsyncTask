package com.example.all_the_way_up.asynctaskex

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

        backgrounder().execute()

        startNewActivity(context)
    }

    private fun startNewActivity(context: Context) {
        val intent = Intent(this, newlyout::class.java)
        startActivity(intent)
    }

    inner class backgrounder : AsyncTask<Void, Void, String>() {
        lateinit var progressDialog: ProgressDialog
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Started Loadong All users")
            progressDialog.setCancelable(false)
            progressDialog.show()
        }

        override fun doInBackground(vararg p0: Void?): String {
            if (hasInternet()) {
                var clinte = OkHttpClient()
                var request = Request.Builder().url("https://api.github.com/users").build()
                var response = clinte.newCall(request).execute()

                return response.body()?.string().toString()
            }
            else{
                return "No Internet Connection"
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            TextView1.text = result
        }
    }

    private fun hasInternet(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activenewWorkInfo = connectivityManager.activeNetworkInfo
        return activenewWorkInfo != null && activenewWorkInfo.isConnected
    }
}
