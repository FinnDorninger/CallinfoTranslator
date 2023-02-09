package at.finndevelopment.callinfotranslator

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_CONTACTS),0)
        }
        if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_SMS),0)
        }
    }

    override fun onResume() {
        super.onResume()
        var number : String
        val messageParser = MessageParser()
        val cursorSMS : Cursor? = baseContext.contentResolver.query(Uri.parse("content://sms/inbox"), arrayOf("body"), "address=", arrayOf("123456"), null) //ToDo: Setting for Number

        if (cursorSMS != null) {
            while (cursorSMS.moveToNext()){
                number = messageParser.getNumber(cursorSMS.getString(0), "004366049915") //ToDo: Setting for ignoring number for example
                if (number.isNotEmpty()) {
                    if (!findAndShowContact(number)) {
                        findAndShowContact(messageParser.removeCountryCode(number))
                    }
                }
            }
            cursorSMS.close()
        }
    }

    fun findAndShowContact(number : String) : Boolean {
        val toast : Toast
        val uri : Uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number))
        val cursorContact   : Cursor? = baseContext.contentResolver.query(uri, arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME), null, null, null)
        if (cursorContact != null && cursorContact.moveToFirst()){
            toast = Toast.makeText(applicationContext, cursorContact.getString(0), Toast.LENGTH_SHORT)
            toast.show()
            cursorContact.close()
            return true
        } else {
            return false
        }
    }
}