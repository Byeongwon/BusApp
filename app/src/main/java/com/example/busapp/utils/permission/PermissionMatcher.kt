package com.example.busapp.utils.permission

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

/**
 * 앱 권한 체크 결과 처리 유틸
 */
class PermissionMatcher(act: Activity, p: Array<String>, g: IntArray, np: List<String>) {

    companion object {
        @JvmStatic
        fun match(act: Activity, permissions: Array<String>, grantResults: IntArray) : PermissionMatcher {
            return PermissionMatcher(act, permissions, grantResults, emptyList())
        }

        @JvmStatic
        fun match(act: Activity, permissions: Array<String>, grantResults: IntArray, needPermissions: List<String>): PermissionMatcher {
            return PermissionMatcher(act, permissions, grantResults, needPermissions)
        }
    }

    private val needPermissions = ArrayList<String>()
    private val permissions = hashMapOf<String, Boolean>()
    private val denyPermissions = ArrayList<String>()
    private val neverAskPermissions = ArrayList<String>()
    private var isNeedPermissionDeny = false

    init {
        needPermissions.addAll(np)
        for (index in p.indices) {
            val key = p[index]
            val isGranted = g[index] == PackageManager.PERMISSION_GRANTED
            permissions[key] = isGranted
            if (!isGranted && needPermissions.contains(key)) isNeedPermissionDeny = true
            if (!isGranted) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(act, key)) {
                    denyPermissions.add(key)
                } else {
                    neverAskPermissions.add(key)
                }
            }
        }
    }

    /**
     * 특정 권한이 허용되었는지 확인한다.
     */
    fun isGrant(permissionKey: String, f: MatcherConsumer<Boolean>): PermissionMatcher {
        if (isNeedPermissionDeny || permissions.size < 1) return this
        if (permissions.containsKey(permissionKey)) permissions[permissionKey]?.let { f.accept(it) }
        return this
    }

    /**
     * 귀찮으면 허용된 권한이 모두 처리되었는지 확인한다.
     */
    fun rest(f: MatcherConsumer<Boolean>): PermissionMatcher {
        if (isNeedPermissionDeny) return this
        if (denyPermissions.isNullOrEmpty() && neverAskPermissions.isNullOrEmpty()) f.accept(true)
        return this
    }

    /**
     * 요청한 권한에 대해 사용자가 부여하지 않는 권한이 있는지 여부를 반환한다.
     */
    fun denied(f: MatcherConsumer<Boolean>): PermissionMatcher {
        if (denyPermissions.isNotEmpty() && neverAskPermissions.isEmpty()) f.accept(true)
        return this
    }

    /**
     * 요청한 권한에 대해 사용자가 부여하지 않은 권한 리스트를 반환한다.
     */
    fun denyPermission(f: MatcherConsumer<List<String>>) : PermissionMatcher {
        if (denyPermissions.isNotEmpty() && neverAskPermissions.isEmpty()) f.accept(denyPermissions)
        return this
    }

    /**
     * 다시보지 않기 설정한 거부 권한이 있는지 반환한다.
     */
    fun neverAsk(f: MatcherConsumer<Boolean>): PermissionMatcher {
        if (neverAskPermissions.isNotEmpty()) f.accept(true)
        return this
    }

    /**
     * 필수로 요청한 권한에 대해 사용자가 부여하지 않은 권한 리스트를 반환한다.
     */
    fun neverAskPermission(f: MatcherConsumer<List<String>>) : PermissionMatcher {
        if (neverAskPermissions.isNotEmpty()) f.accept(neverAskPermissions)
        return this
    }

    /**
     * 앱에서 지정한 필수 권한에 대해 사용자가 허가 하지 않은 권한이 있는지 여부를 반환한다.
     */
    fun denyNeedPermission(f: MatcherConsumer<Boolean>): PermissionMatcher {
        if (isNeedPermissionDeny) f.accept(true)
        return this
    }

    interface MatcherConsumer<T> {
        fun accept(v: T)
    }
}