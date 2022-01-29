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
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_CONTACTS),0)
        } else {
            Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        var uri             : Uri
        var toast           : Toast
        var smsMessage      : CharArray
        var cursorContact   : Cursor?
        val msgData = StringBuilder()
        val number = StringBuilder()

        val cursorSMS : Cursor? = baseContext.contentResolver.query(Uri.parse("content://sms/inbox"), arrayOf("body"), "address=?", arrayOf("123456"), null)

        if (cursorSMS != null) {
            while (cursorSMS.moveToNext()){
                smsMessage = cursorSMS.getString(0).toCharArray()
                number.clear()

                for (i in 0 until smsMessage.count()){
                    if(smsMessage[i].isDigit() || smsMessage[i].equals('+')){
                        number.append(smsMessage[i])
                        if((i < smsMessage.count()-1 && !smsMessage[i+1].isDigit()) || (i == smsMessage.count()-1)){
                            if(number.count() > 5){
                                toast = Toast.makeText(applicationContext, number, Toast.LENGTH_SHORT)
                                toast.show()

                                uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number.toString()))
                                cursorContact = baseContext.contentResolver.query(uri, arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME), null, null, null)
                                if (cursorContact != null && cursorContact.moveToFirst()){
                                    toast = Toast.makeText(applicationContext, cursorContact.getString(0), Toast.LENGTH_SHORT)
                                    toast.show()
                                    cursorContact.close()
                                }
                            }
                        }
                    }
                    else{
                        number.clear()
                    }
                }
                msgData.append(cursorSMS.getString(0))
            }
            cursorSMS.close()
        }
    }
}