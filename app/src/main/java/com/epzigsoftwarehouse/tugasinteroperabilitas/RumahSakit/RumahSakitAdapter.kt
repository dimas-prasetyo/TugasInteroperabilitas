package com.epzigsoftwarehouse.tugasinteroperabilitas.RumahSakit

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.location.Location
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.epzigsoftwarehouse.tugasinteroperabilitas.FormSkriningActivity
import com.epzigsoftwarehouse.tugasinteroperabilitas.R
import com.epzigsoftwarehouse.tugasinteroperabilitas.RumahSakitDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_rumah_sakit_list.view.*
import java.time.LocalDate


class RumahSakitAdapter(val context: Context?, val items: ArrayList<RumahSakit>, val nikPeserta: String) : RecyclerView.Adapter<RumahSakitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.layout_rumah_sakit_list, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)

        // Gambar
        Picasso.get()
            .load(item.gambar)
            .placeholder(R.color.colorGray)
            .error(R.color.colorGray)
            .into(holder.image)

        // Nama
        holder.txt_nama.text = item.nama

        // Stok
        holder.txt_stok.text = item.stok.toString()

        // Jarak
        val lokasiSaya = Location("locationA")
        lokasiSaya.latitude = -7.771193349012682
        lokasiSaya.longitude = 110.37743542421101

        val endPoint = Location("locationA")
        endPoint.latitude = item.latitude.toDouble()
        endPoint.longitude = item.longtitude.toDouble()

        val distance = lokasiSaya.distanceTo(endPoint).toDouble() / 1000
        val number2digits:Double = String.format("%.2f", distance).toDouble()

        holder.txt_jarak.text = number2digits.toString() + " km"

        // Hari buka
        val yourArray: List<String> = item.waktu.split("")

        if (yourArray[LocalDate.now().getDayOfWeek().value].toInt() == 1){
            //holder.root_card_view.getBackground().setAlpha(255);
        } else {
            //holder.root_card_view.backgroundTintList = ContextCompat.getColorStateList(context!!, R.color.colorGray)
            holder.root_card_view.alpha = 0.3f
        }

        holder.root_card_view.setOnClickListener {

            if (nikPeserta == "0" || nikPeserta == null){
                val manageDetailIntent = Intent(context, RumahSakitDetailActivity::class.java)
                manageDetailIntent.putExtra("id", item.id.toString())

                context?.startActivity(manageDetailIntent)
            } else {
                val manageDetailIntent = Intent(context, FormSkriningActivity::class.java)
                manageDetailIntent.putExtra("id", item.id.toString())
                manageDetailIntent.putExtra("nik", nikPeserta)

                context?.startActivity(manageDetailIntent)
            }
        }

    }
    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root_card_view = view.root_card_view
        val image = view.image
        val txt_nama = view.txt_nama
        val txt_stok = view.txt_stok
        val txt_jarak = view.txt_jarak
    }


}