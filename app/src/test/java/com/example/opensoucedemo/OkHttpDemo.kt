package com.example.opensoucedemo

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Test
import java.io.IOException
import java.util.concurrent.CountDownLatch

/**
 * Created by dingbaosheng on 12/9/20 9:55 AM.
 */
class OkHttpDemo {

    val url = "https://github.com/square/okhttp"

    @Test
    fun testGet(){

        val client = OkHttpClient()

        val request = Request.Builder().url(url).build()

        var response:Response? = null

        response = client.newCall(request).execute()

        print("sync response:${response.body().toString()}")
    }


    @Test
    fun testSyncGet() {

        val coutDownLatch = CountDownLatch(1)

        val url = "http://www.baidu.com"
        val client = OkHttpClient()

        val request = Request.Builder().url(url).build()

        val call =  client.newCall(request)

        call.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                print("onFailure thread:${Thread.currentThread().name} e:${e.toString()}")
                coutDownLatch.countDown()
            }

            override fun onResponse(call: Call, response: Response) {
                print("onResponse:thread:${Thread.currentThread().name} e:${response.body().toString()}")
                coutDownLatch.countDown()
            }
        })

        coutDownLatch.await()

    }
}