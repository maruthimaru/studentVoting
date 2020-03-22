package com.example.laksh_pc.voteapp

import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.telephony.SmsManager
import android.widget.ImageView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CommonClass(var context: Context) {
    fun sweetAlertDialog(title: String?, content: String?, alertDialog: Int) {
        val sw = SweetAlertDialog(context, alertDialog)
        sw.titleText = title
        sw.contentText = content
        sw.show()
        Handler().postDelayed({ sw.dismissWithAnimation() }, 5000)
    }

    fun imageLoad(imageView: ImageView?, bitmap: Bitmap?) {
        Glide.with(context).asBitmap().load(bitmap).apply(RequestOptions.circleCropTransform()).into(imageView!!)
    }

    fun sendSMS(phoneNo: String?, msg: String?) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, msg, null, null)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}