package com.epzigsoftwarehouse.tugasinteroperabilitas

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.epzigsoftwarehouse.tugasinteroperabilitas.imageSlider.SliderAdapter
import com.epzigsoftwarehouse.tugasinteroperabilitas.imageSlider.SliderItem
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    private lateinit var sliderAdapter: SliderAdapter
    private var widthScreen = 0
    private var widthBanner = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        card_view_rumah_sakit.setOnClickListener {
            val intent = Intent(this, RumahSakitListActivity::class.java)
            startActivity(intent)
        }

        card_view_registrasi.setOnClickListener {
            val intent = Intent(this, DaftarVaksinasiActivity::class.java)
            startActivity(intent)
        }

        sliderAdapter = SliderAdapter(this)
        imageSlider.setSliderAdapter(sliderAdapter)

        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        imageSlider.setIndicatorSelectedColor(Color.WHITE)
        imageSlider.setIndicatorUnselectedColor(Color.GRAY)
        imageSlider.setScrollTimeInSec(3)
        imageSlider.setAutoCycle(true)
        imageSlider.startAutoCycle()

        imageSlider.setOnIndicatorClickListener {
            Log.i(
                "GGG",
                "onIndicatorClicked: " + imageSlider.getCurrentPagePosition()
            )
        }


        val displayMetrics = this.resources.displayMetrics

        widthScreen = displayMetrics.widthPixels
        card_view_banner.getLayoutParams().height = widthScreen / 2

        renewItems(imageSlider)

    }


    fun renewItems(view: View?) {
        val sliderItemList: MutableList<SliderItem> = ArrayList()
        //dummy data
        for (i in 0..3) {
            val sliderItem = SliderItem()
            sliderItem.description = "Slider Item $i"
            sliderItem.id = i
            sliderItemList.add(sliderItem)
        }

        sliderAdapter.renewItems(sliderItemList)
    }

    /*fun removeLastItem(view: View?) {
        if (sliderAdapter.getCount() - 1 >= 0) sliderAdapter.deleteItem(sliderAdapter.getCount() - 1)
    }

    fun addNewItem(view: View?) {
        val sliderItem = SliderItem()
        sliderItem.description = "Slider Item Added Manually"
        sliderItem.imageUrl =
            "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
        sliderAdapter.addItem(sliderItem)
    }*/
}