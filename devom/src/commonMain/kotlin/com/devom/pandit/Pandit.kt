package com.devom.pandit

import com.devom.domain.pandit.GetPanditReviewsUseCase
import com.devom.domain.panditslots.GetAvailableSlotsUseCase

class Pandit {
    val getAvailableSlotsUseCase by lazy { GetAvailableSlotsUseCase() }
    val createPanditSlotUseCase by lazy { com.devom.domain.panditslots.CreatePanditSlotUseCase() }
    val bookPanditSlotUseCase by lazy { com.devom.domain.panditslots.BookPanditSlotUseCase() }
    val getPanditBookingsUseCase by lazy { com.devom.domain.panditslots.GetPanditBookingsUseCase() }
    val getPanditReviewsUseCase by lazy { GetPanditReviewsUseCase() }
    val createPanditReviewUseCase by lazy { com.devom.domain.pandit.CreatePanditReviewUseCase() }
    val updateBookingStatusUseCase by lazy { com.devom.domain.panditslots.UpdateBookingStatusUseCase() }
    val getBiographyUseCase by lazy { com.devom.domain.pandit.GetBiographyUseCase() }
    val updateBiographyUseCase by lazy { com.devom.domain.pandit.UpdateBiographyUseCase() }
    val getPanditPoojaUseCase by lazy { com.devom.domain.pandit.GetPanditPoojaUseCase() }
    val mapPanditPoojaItemUseCase by lazy { com.devom.domain.pandit.MapPanditPoojaItemUseCase() }
    val getPanditBookingById by lazy { com.devom.domain.panditslots.GetBookingByIdUseCase() }
}
