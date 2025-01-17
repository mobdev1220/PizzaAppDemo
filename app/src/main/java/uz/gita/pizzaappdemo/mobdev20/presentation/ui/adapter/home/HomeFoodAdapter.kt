package uz.gita.pizzaappdemo.mobdev20.presentation.ui.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.pizzaappdemo.mobdev20.R
import uz.gita.pizzaappdemo.mobdev20.data.local.home.SharedPref
import uz.gita.pizzaappdemo.mobdev20.data.remote.home.model.FoodData
import uz.gita.pizzaappdemo.mobdev20.databinding.FoodItemBinding

class HomeFoodAdapter(val context: Context) :
    ListAdapter<FoodData, HomeFoodAdapter.ViewHolder>(HomeFoodDiffUtils) {
    lateinit var sharedPref: SharedPref

    object HomeFoodDiffUtils : DiffUtil.ItemCallback<FoodData>() {
        override fun areItemsTheSame(oldItem: FoodData, newItem: FoodData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FoodData, newItem: FoodData): Boolean =
            oldItem == newItem

    }

    inner class ViewHolder(private val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(): FoodData = with(binding) {
            sharedPref = SharedPref(context)
            getItem(absoluteAdapterPosition).apply {
                when (sharedPref.language) {
                    1 -> {
                        foodName.text = name_eng
                        foodDescription.text = description_eng
                        foodPrise.text = foodPrice_eng!!
                    }
                    2 -> {
                        foodName.text = name_uz
                        foodDescription.text = description_uz
                        foodPrise.text = foodPrice_uz!!
                    }
                    else -> {
                        foodName.text = name_ru
                        foodDescription.text = description_ru
                        foodPrise.text = foodPrice_ru!!
                    }
                }
                Glide
                    .with(foodImage)
                    .load(foodImageUrl)
                    .into(foodImage)
                if (event_eng!!.isEmpty()) {
                    event.visibility = View.GONE
                } else {
                    event.text = event_eng
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            FoodItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}