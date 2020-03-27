package com.example.busapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.busapp.R
import com.example.busapp.ui.busmap.BusMapActivity
import com.example.busapp.ui.favorite.FavoriteActivity
import com.example.busapp.ui.search.SearchActivity
import com.example.busapp.utils.permission.PermissionMatcher
import com.example.busapp.utils.permission.PermissionUtil
import com.example.busapp.location.LocationProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val FINISH_INTERVAL_TIME = 2000
        private val REQUEST_CODE_APP_DETAIL_SETTING = 10001
    }

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMenuClickListener()
        LocationProvider.init(this)
        PermissionUtil.requestPermissions(this, PermissionUtil.getPermissions())
    }

    private fun setMenuClickListener() {
        first_menu.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        second_menu.setOnClickListener {
            startActivity(Intent(this, BusMapActivity::class.java))
        }
        third_menu.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
        fourth_menu.setOnClickListener {
            Toast.makeText(applicationContext, "아직 지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - backPressedTime

        if (intervalTime in 0..FINISH_INTERVAL_TIME) {
            super.onBackPressed()
        } else {
            backPressedTime = tempTime
            Toast.makeText(applicationContext, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionMatcher.match(this, permissions, grantResults, PermissionUtil.getPermissions())
            .rest(object : PermissionMatcher.MatcherConsumer<Boolean> {
                override fun accept(v: Boolean) {
                    // 여기서 수용 시 처리 로직 추가 필요
                }
            })
            .neverAsk(object : PermissionMatcher.MatcherConsumer<Boolean> {
                override fun accept(v: Boolean) {
                    showPermissionAlertDialog(false)
                }
            })
            .denied(object: PermissionMatcher.MatcherConsumer<Boolean> {
                override fun accept(v: Boolean) {
                    showPermissionAlertDialog(true)
                }
            })
    }

    private fun showPermissionAlertDialog(isShowRationale: Boolean) {
        AlertDialog.Builder(this)
            .setTitle(R.string.permission_dialog_title)
            .setMessage(if (isShowRationale) R.string.permission_dialog_content else R.string.permission_dialog_content_setting)
            .setCancelable(false)
            .setPositiveButton(if (isShowRationale) R.string.permission_dialog_button_positive else R.string.permission_dialog_button_positive_setting)
            { dialog, _ ->
                if (isShowRationale) {
                    PermissionUtil.requestPermissions(this, PermissionUtil.getPermissions())
                } else {
                    startAppDetailSetting()
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.permission_dialog_button_negative)
            { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .show()
    }

    private fun startAppDetailSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, REQUEST_CODE_APP_DETAIL_SETTING)
    }
}
