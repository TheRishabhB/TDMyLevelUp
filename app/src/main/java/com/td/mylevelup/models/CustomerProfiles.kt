package com.td.mylevelup.models

import com.td.mylevelup.R

enum class CustomerProfiles(val profile: Profile) {
    IVANA_EASTOM(Profile("Ivana Easom", 38, "Female", "In School",
            "Renting",
            "1f9e0890-77b5-44ae-a987-03a0a6a1029a_e2ba9727-a181-48f6-a1bc-0abf5ce173a2", R.drawable.ivana)),

    GALEN_NEVIUS(Profile("Galen Nevius", 49, "Male", null,
            "Sharing Rent",
            "1f9e0890-77b5-44ae-a987-03a0a6a1029a_6c8434d3-9d00-45d9-83d6-5c87cc97cdd8", R.drawable.galen)),

    FRANK_NERLINSKI(Profile("Frank Nerlinski", 34, "Male", "Barista",
            "Renting",
            "1f9e0890-77b5-44ae-a987-03a0a6a1029a_f19107d0-3995-4006-a42d-fc7dced91fcb", R.drawable.frank)),

    YUETTE_MCCALLION(Profile("Yuette Mccallion", 43, "Female", "FullTime",
            "Renting",
            "1f9e0890-77b5-44ae-a987-03a0a6a1029a_85a09159-bda3-426a-bcd3-00532807d1df", R.drawable.yuette)),

    TAYLAH_BASORA(Profile("Taylah Basora", 51, "Female", "Barista",
            "Sharing Rent",
            "1f9e0890-77b5-44ae-a987-03a0a6a1029a_46ecc00a-84f5-4b64-a1fa-4354edeba8c4", R.drawable.taylah));
}