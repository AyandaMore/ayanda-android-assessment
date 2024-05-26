package com.glucode.about_you.views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.glucode.about_you.R
import com.glucode.about_you.about.ImagePickerFragment
import com.glucode.about_you.engineers.models.Engineer

class ProfileCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val profileImageView: ImageView
    private val profileNameTextView: TextView
    private val profileRoleTextView: TextView
    private val profileYearsTextView: TextView
    private val profileCoffeesTextView: TextView
    private val profileBugsTextView: TextView
    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>

    init {
        LayoutInflater.from(context).inflate(R.layout.profile_card, this, true)
        profileImageView = findViewById(R.id.profileImageView)
        profileNameTextView = findViewById(R.id.profileNameTextView)
        profileRoleTextView = findViewById(R.id.profileRoleTextView)
        profileYearsTextView = findViewById(R.id.profileYearsTextView)
        profileCoffeesTextView = findViewById(R.id.profileCoffeesTextView)
        profileBugsTextView = findViewById(R.id.profileBugsTextView)
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
        profileImageView.setOnClickListener {
            if (::imagePickerLauncher.isInitialized) {
                imagePickerLauncher.launch("image/*")
            }
        }
    }

    fun bind(engineer: Engineer) {
        profileNameTextView.text = engineer.name
        profileRoleTextView.text = engineer.role
        profileYearsTextView.text = engineer.quickStats.years.toString()
        profileCoffeesTextView.text = engineer.quickStats.coffees.toString()
        profileBugsTextView.text = engineer.quickStats.bugs.toString()



        // Load default image resource
        val resId = context.resources.getIdentifier(engineer.defaultImageName, "drawable", context.packageName)
        if (resId != 0) {
            profileImageView.setImageResource(resId)
        }


    }
    fun updateProfileImage(uri: Uri) {
        profileImageView.setImageURI(uri)
    }

    fun setImagePickerLauncher(launcher: ActivityResultLauncher<String>) {
        this.imagePickerLauncher = launcher
    }
}
