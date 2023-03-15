package at.finndevelopment.callinfotranslator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SMSAdapter(private val mSMS:  List<SMS>) : RecyclerView.Adapter<SMSAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactname = itemView.findViewById<TextView>(R.id.contactname)
        val smsDate = itemView.findViewById<TextView>(R.id.smsdate)
        val phoneNumber = itemView.findViewById<TextView>(R.id.phonenumber)
        val smsText = itemView.findViewById<TextView>(R.id.smstext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SMSAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_sms, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: SMSAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val sms: SMS = mSMS.get(position)
        // Set item views based on your views and data model
        viewHolder.contactname.setText(sms.contactName)
        viewHolder.phoneNumber.setText(sms.phoneNumber)
        viewHolder.smsDate.setText(sms.smsText)
        viewHolder.smsText.setText(sms.smsText)
    }

    override fun getItemCount(): Int {
        return mSMS.size
    }
}