package com.example.busapp.utils.permission

import android.Manifest
import android.app.Activity
import androidx.core.app.ActivityCompat

/**
 * 권한 관련 유틸
 */
object PermissionUtil {

    /**
     * 권한 요청
     */
    @JvmStatic
    fun requestPermissions(activity: Activity?, needPermissions: List<String>?) {
        val act = activity ?: return
        if (needPermissions.isNullOrEmpty()) return
        ActivityCompat.requestPermissions(act, needPermissions.toTypedArray(), 0)
    }

    /**
     * 권한 리스트. 필요시 추가 필요
     */
    @JvmStatic
    fun getPermissions() : List<String> {
        return ArrayList<String>().apply {
            add(Manifest.permission.ACCESS_FINE_LOCATION)
            add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
}