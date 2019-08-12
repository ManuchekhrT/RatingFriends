package tj.manu.ratingfriends.utils

import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText

object CommonUtils {

    private const val VERIFICATION_CODE_LENGTH = 6
    private const val STANDART_TJK_PHONE_LENGTH = 9

    fun convertEdtString(editText: EditText): String {
        return editText.text.toString().trim()
    }

    fun convertEdtToStringWithReplaceAll(editText: EditText): String {
        return editText.text.toString().trim { it <= ' ' }.replace(" ".toRegex(), "")
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhone(phone: String): Boolean{
        return if (TextUtils.isEmpty(phone)) {
            false
        } else {
            android.util.Patterns.PHONE.matcher(phone).matches()
        }
    }

    //if phone length is 9
    fun isLocalPhoneFormat(phone: String): Boolean {
        return phone.length == STANDART_TJK_PHONE_LENGTH
    }


    fun isVerificationCodeLength(code: String): Boolean {
        return code.length == VERIFICATION_CODE_LENGTH
    }
}