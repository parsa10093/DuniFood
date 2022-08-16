package com.parsamini.dunifood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parsamini.dunifood.FoodAdapter.FoodShilang
import com.parsamini.dunifood.databinding.ActivityMainBinding
import com.parsamini.dunifood.databinding.AddFoodBinding
import com.parsamini.dunifood.databinding.DeletItemBinding
import com.parsamini.dunifood.databinding.DialogUpdateBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), FoodShilang {
    lateinit var binding: ActivityMainBinding
    lateinit var MyAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("001100" , "line 27")
        Log.e("001100" , "line 28")
        Log.e("001100" , "line 29")
        Log.e("001100" , "line 30")
        val FoodList = Foodlist()
        Adapter(FoodList)
        ButtonAdd(FoodList)
        search()
    }

    fun Adapter(FoodList: ArrayList<Food>) {
        MyAdapter = FoodAdapter(FoodList.clone() as ArrayList<Food>, this)
        binding.recyclerView.adapter = MyAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    fun Foodlist(): ArrayList<Food> {
        val FoodList = arrayListOf(
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                "Hamburger",
                "Iran, Tehran",
                "$60 vip",
                "138.2",
                "435 Ratings",
                4.0f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                "Grilled Fish",
                "Iran, Isfahan",
                "$120 vip",
                "239.4",
                "353 Ratings",
                5.0f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                "Lesania",
                "Iran, Mashhad",
                "$430 vip",
                "540.2",
                "23 Ratings",
                3.5f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                "Pizza",
                "Iran, Shiraz",
                "$1970 vip",
                "193.2",
                "543 Ratings",
                3.5f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                "Sushi",
                "Iran, Yazd",
                "$6720 vip",
                "433.5",
                "354 Ratings",
                2.5f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                "Roasted Fish",
                "Iran, Kerman",
                "$620 vip",
                "321.3",
                "45 Ratings",
                5.0f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                "Fried Chicken",
                "Iran, Qom",
                "$128 vip",
                "382.6",
                "354 Ratings",
                4.0f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                "Vegetable Saled",
                "Iran, Qazvin",
                "$99 vip",
                "423.6",
                "258 Ratings",
                3.5f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                "Grilled Chicken",
                "Iran, Sari",
                "$100 vip",
                "42342",
                "24 Ratings",
                4.0f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                "Berioon",
                "Iran, Ahvaz",
                "$102 vip",
                "1383.23",
                "455 Ratings",
                1.5f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                "Ghorme Sabzi",
                "Iran, Kashan",
                "$98 vip",
                "132.7",
                "523 Ratings",
                5.0f
            ),
            Food(
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                "Rice",
                "France, Paris",
                "$6464 vip",
                "8374.4",
                "144 Ratings",
                2.0f
            ),
            Food(
                "https://upload.wikimedia.org/wikipedia/commons/f/ff/Egg_Sandwich.jpg",
                "sandwich",
                "Italia, Atn",
                "$66456 vip",
                "7243.5",
                "783 Ratings",
                4.0f
            ),

            )
        return FoodList
    }

    override fun clickShort(food: Food, position: Int) {
        val DialogUpdate = AlertDialog.Builder(this).create()
        val DialogUpdateBinding = DialogUpdateBinding.inflate(layoutInflater)
        DialogUpdate.setView(DialogUpdateBinding.root)
        DialogUpdate.setCancelable(true)
        DialogUpdate.show()
        DialogUpdateBinding.EdtNameFood.setText(food.name)
        DialogUpdateBinding.edtDistans.setText(food.distands)
        DialogUpdateBinding.edtCityName.setText(food.city)
        DialogUpdateBinding.edtPriceFood.setText(food.price)

        DialogUpdateBinding.buttonSubmit.setOnClickListener {
            val name = DialogUpdateBinding.EdtNameFood.text.toString()
            val distans = DialogUpdateBinding.edtDistans.text.toString()
            val city = DialogUpdateBinding.edtCityName.text.toString()
            val price = DialogUpdateBinding.edtPriceFood.text.toString()
            food.name = name
            food.distands = distans
            food.city = city
            food.price = price
            MyAdapter.UpdateItem(food, position)
            DialogUpdate.dismiss()
        }
    }

    override fun clickLong(viewFoodOld: Food, position: Int) {
        val DialogDel = AlertDialog.Builder(this).create()
        val BindingDialogDel = DeletItemBinding.inflate(layoutInflater)
        DialogDel.setView(BindingDialogDel.root)
        DialogDel.setCancelable(true)
        DialogDel.show()
        BindingDialogDel.buttonCancle.setOnClickListener {
            DialogDel.dismiss()
        }
        BindingDialogDel.buttonAccept.setOnClickListener {
            MyAdapter.RemoveItem(viewFoodOld, position)
            DialogDel.dismiss()
        }
    }

    fun ButtonAdd(FoodList: ArrayList<Food>) {
        binding.ButtonAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val MyViewBinding = AddFoodBinding.inflate(layoutInflater)
            dialog.setView(MyViewBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            MyViewBinding.buttonSubmit.setOnClickListener {

                if (MyViewBinding.EdtNameFood.text!!.isNotEmpty() &&
                    MyViewBinding.edtPriceFood.text!!.isNotEmpty() &&
                    MyViewBinding.edtCityName.text!!.isNotEmpty() &&
                    MyViewBinding.edtDistans.text!!.isNotEmpty()
                ) {

                    val nameFood = MyViewBinding.EdtNameFood.text.toString()
                    val nameCity = MyViewBinding.edtCityName.text.toString()
                    val PriceValue = "$" + MyViewBinding.edtPriceFood.text.toString() + " vip"
                    val valueDistans = MyViewBinding.edtDistans.text.toString()
                    val numOfRating: Int = (1..9999).random()
                    val min = 0f
                    val max = 5f
                    val starOfRating = min + Random().nextFloat() * (max - min)
                    val numUrlImg = (0..12).random()
                    val UrlImgFood = FoodList[numUrlImg].urlImg
                    val newFood = Food(
                        UrlImgFood,
                        nameFood,
                        nameCity,
                        PriceValue,
                        valueDistans,
                        numOfRating.toString() + " Ratings",
                        starOfRating
                    )
                    MyAdapter.addNewItem(newFood)
                    Toast.makeText(this, "Food is add", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    binding.recyclerView.smoothScrollToPosition(0)
                } else
                    Toast.makeText(this, "please por all blanks edit text!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun search() {
        Log.d("000111000", "log anjam shod", )
        binding.edtSearch.addTextChangedListener { txtEdt ->

            if (txtEdt!!.isNotEmpty()) {
                val cloneList = Foodlist().clone() as ArrayList<Food>
                val ItemFiltered = cloneList.filter { FoodItem ->
                    FoodItem.name.contains(txtEdt.toString())
                }
                MyAdapter.setItem(ArrayList(ItemFiltered))
            }
            else
                MyAdapter.setItem(Foodlist().clone() as ArrayList<Food>)
        }
    }
}