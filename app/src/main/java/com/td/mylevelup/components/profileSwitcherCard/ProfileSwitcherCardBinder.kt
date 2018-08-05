package com.td.mylevelup.components.profileSwitcherCard

import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ngam.rvabstractions.multiCard.AbstractMultiCardBinder
import com.td.mylevelup.R
import com.td.mylevelup.models.Profile
import de.hdodenhof.circleimageview.CircleImageView

class ProfileSwitcherCardBinder(private val profile: Profile): AbstractMultiCardBinder() {
    override fun createItem(container: ViewGroup): View {
        return getView(R.layout.profile_information, container)
    }

    override fun getCardView(view: View): CardView {
        return view.findViewById(R.id.profileCardView)
    }

    override fun bindData(view: View) {
        val imageView: CircleImageView = view.findViewById(R.id.profileImage)
        val nameText: TextView = view.findViewById(R.id.profileUserName)
        val informationText: TextView = view.findViewById(R.id.profileInformation)

        imageView.setImageResource(profile.profilePictureID)
        nameText.text = profile.name
        informationText.text = String.format("%d - %1s - %2s",
                profile.age, profile.occupation ?: "Unknown", profile.habitationStatus)
    }
}