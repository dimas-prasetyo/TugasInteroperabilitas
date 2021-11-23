package com.epzigsoftwarehouse.tugasinteroperabilitas.imageSlider

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import com.epzigsoftwarehouse.tugasinteroperabilitas.R


class SliderAdapter(context: Context) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private val context: Context
    private var mSliderItems: MutableList<SliderItem> = ArrayList()

    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_slider, null)
        return SliderAdapterVH(inflate)

    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem: SliderItem = mSliderItems[position]
        //viewHolder.textViewDescription.setText(sliderItem.getDescription())
        viewHolder.textViewDescription.textSize = 16f
        viewHolder.textViewDescription.setTextColor(Color.WHITE)

        if (sliderItem.id == 0){
            Picasso.get()
                    .load(R.drawable.banner_1)
                    .placeholder(R.color.primary_light)
                    .error(R.color.primary_light)
                    .into(viewHolder.imageViewBackground)
        }

        if (sliderItem.id == 1){
            Picasso.get()
                    .load(R.drawable.banner_2)
                    .placeholder(R.color.primary_light)
                    .error(R.color.primary_light)
                    .into(viewHolder.imageViewBackground)
        }

        if (sliderItem.id == 2){
            Picasso.get()
                    .load(R.drawable.banner_3)
                    .placeholder(R.color.primary_light)
                    .error(R.color.primary_light)
                    .into(viewHolder.imageViewBackground)
        }

        if (sliderItem.id == 3){
            Picasso.get()
                    .load(R.drawable.banner_4)
                    .placeholder(R.color.primary_light)
                    .error(R.color.primary_light)
                    .into(viewHolder.imageViewBackground)
        }


        /*Glide.with(viewHolder.itemView)
            .load(sliderItem.imageUrl)
            .fitCenter()
            .into(viewHolder.imageViewBackground)*/


        /*viewHolder.itemView.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View?) {
                Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT)
                    .show()
            }
        })*/
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        lateinit var itemView: View
        var imageViewBackground: ImageView
        var imageGifContainer: ImageView
        var textViewDescription: TextView

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container)
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider)
            //this.itemView = itemView
        }
    }

    init {
        this.context = context
    }
}
