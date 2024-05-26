package com.glucode.about_you.engineers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glucode.about_you.databinding.ItemEngineerBinding
import com.glucode.about_you.engineers.models.Engineer

class EngineersRecyclerViewAdapter(
    private var engineers: List<Engineer>,
    private val onClick: (Engineer) -> Unit
) : RecyclerView.Adapter<EngineersRecyclerViewAdapter.EngineerViewHolder>() {

    override fun getItemCount() = engineers.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        return EngineerViewHolder(ItemEngineerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(engineers[position], onClick)
    }

    inner class EngineerViewHolder(private val binding: ItemEngineerBinding) : RecyclerView.ViewHolder(binding.root) {
         var currentEngineer: Engineer? = null;


        fun bind(engineer: Engineer, onClick: (Engineer) -> Unit) {
            currentEngineer = engineer
            binding.name.text = engineer?.name
            binding.role.text = engineer?.role
            engineer.profilePictureUri?.let {
                binding.profileImage.setImageURI(it)
            } ?: run {

                val resId = binding.root.context.resources.getIdentifier(engineer.defaultImageName, "drawable", binding.root.context.packageName)
                if (resId != 0) {
                    binding.profileImage.setImageResource(resId)
                }
            }
            binding.root.setOnClickListener {
                onClick(engineer)
            }

        }
    }
}