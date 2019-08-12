package tj.manu.ratingfriends.utils

import android.app.Activity
import android.content.Context
import com.google.firebase.auth.PhoneAuthProvider

object SettingsUtils {

    private const val KEY_VERIFICATION_ID = "verification_id"

    fun storeVerificationId(context: Activity, verificationId: String) {
        val sharedPref = context.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(KEY_VERIFICATION_ID, verificationId)
            apply()
        }
    }

    fun getVerificationId(context: Activity): String {
        val sharedPref = context.getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(KEY_VERIFICATION_ID, null)
    }
}