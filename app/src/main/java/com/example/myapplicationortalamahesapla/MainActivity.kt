package com.example.myapplicationortalamahesapla

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsSpinner
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.yeni_ders_layout.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class MainActivity : AppCompatActivity() {
    val DERSLER = arrayOf("Matematik", "Fizik", "Kimya", "Algoritma", "Tarih")

    private var tumDerslerinBilgileri: ArrayList<Dersler> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DERSLER)
        girilenders.setAdapter(adapter)

        if (rootLayout.childCount == 0) {
            hesapla.visibility = View.INVISIBLE
        } else {
            hesapla.visibility = View.VISIBLE
        }


        ekle.setOnClickListener {
            if (!girilenders.text.isNullOrEmpty()) {
                var v = LayoutInflater.from(this)
                var yeniDersView = v.inflate(R.layout.yeni_ders_layout, null)

                var dersAdi = girilenders.text.toString()
                var dersKredi = secKredi.selectedItem.toString()
                var dersHarf = secNot.selectedItem.toString()

                yeniDersView.yenigirilenders.setText(dersAdi)
                yeniDersView.yenisecKredi.setSelection(SpinnerIndexBul(secKredi, dersKredi))
                yeniDersView.yenisecNot.setSelection(SpinnerIndexBul(secNot, dersHarf))



                yeniDersView.sil.setOnClickListener {
                    rootLayout.removeView(yeniDersView)
                    if (rootLayout.childCount == 0) {
                        hesapla.visibility = View.INVISIBLE
                    } else {
                        hesapla.visibility = View.VISIBLE
                    }
                }
                rootLayout.addView(yeniDersView)
                hesapla.visibility = View.VISIBLE
                sifirla()

            } else {
                Toast.makeText(this, "LÜTFEN DERS ADINI GİRİNİZ", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun SpinnerIndexBul(spinner: Spinner, aranacakDeger: String): Int {

        var index = 0
        for (i in 0..spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(aranacakDeger)) {
                index = i
                break
            }
        }
        return index

    }
    fun sifirla() {
        girilenders.setText("")
        secKredi.setSelection(0)
        secNot.setSelection(0)
    }


    fun hesaplaOrtalama(view: View) {


        var toplamNot = 0.0
        var toplamKredi = 0.0

        for (i in 0..rootLayout.childCount - 1) {
            var tekSatir = rootLayout.getChildAt(i)
            var geciciDers = Dersler(
                tekSatir.yenigirilenders.text.toString(),
                ((tekSatir.yenisecKredi.selectedItemPosition) + 1).toString(),
                tekSatir.yenisecNot.selectedItem.toString())

            tumDerslerinBilgileri.add(geciciDers)
        }

        for (oankiDers in tumDerslerinBilgileri) {
            toplamKredi += oankiDers.dersKredi.toDouble()
            toplamNot += harfiNotaCevir(oankiDers.dersHarf) * (oankiDers.dersKredi.toDouble())

        }
        FancyToast.makeText(this,"ORTALAMANIZ:" + (toplamNot / toplamKredi), FancyToast.LENGTH_LONG,FancyToast.INFO,true).show()
        tumDerslerinBilgileri.clear()

    }

    fun harfiNotaCevir(gelenharf: String): Double {

        var deger = 0.0
        when (gelenharf) {

            "AA" -> deger = 4.0
            "BA" -> deger = 3.5
            "BB" -> deger = 3.0
            "CB" -> deger = 2.5
            "CC" -> deger = 2.0
            "DC" -> deger = 1.5
            "DD" -> deger = 1.0
            "FF" -> deger = 0.0
        }
        return deger
    }

}












