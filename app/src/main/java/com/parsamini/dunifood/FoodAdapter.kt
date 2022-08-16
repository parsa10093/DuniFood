package com.parsamini.dunifood

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parsamini.dunifood.databinding.RecyclerBinding
import kotlin.collections.ArrayList

class FoodAdapter(val FoodData :ArrayList<Food> , val sar1Shilang: FoodShilang) :RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    inner class FoodHolder(val binding: RecyclerBinding) :RecyclerView.ViewHolder(binding.root){
        fun BindData(position: Int){
            binding.textFoodName.text = FoodData[position].name
            binding.textCity.text = FoodData[position].city
            binding.textPrice.text = FoodData[position].price
            binding.textFasele.text = FoodData[position].distands
            binding.textRating.text = FoodData[position].CountOfRating
            binding.ratingBar.rating = FoodData[position].rating
            Glide.with(binding.root.context)
                .load(FoodData[position].urlImg)
                .into(binding.imgFood)

            itemView.setOnClickListener {
                sar1Shilang.clickShort(FoodData[adapterPosition] , position)
            }

            itemView.setOnLongClickListener {
                sar1Shilang.clickLong(FoodData[adapterPosition] , adapterPosition)
                true
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val binding = RecyclerBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return FoodHolder(binding)

    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.BindData(position)
    }

    override fun getItemCount(): Int {
        return FoodData.size
    }

    fun addNewItem(newFood :Food){
        FoodData.add(0, newFood)
        notifyItemInserted(0)
    }

    fun RemoveItem(OldFood :Food , position: Int){
        FoodData.remove(OldFood)
        notifyItemRemoved(position)
    }

    fun UpdateItem(newFood: Food , position: Int){
        FoodData.set(position , newFood)
        notifyItemChanged(position)
    }

    fun setItem(newItem: ArrayList<Food>){
        FoodData.clear()
        FoodData.addAll(newItem)
        notifyDataSetChanged()
    }

    interface FoodShilang{
        fun clickShort(food: Food , position: Int)
        fun clickLong(viewFoodOld:Food , position: Int)
    }
}