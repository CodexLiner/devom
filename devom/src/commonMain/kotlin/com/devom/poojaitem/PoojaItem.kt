package com.devom.poojaitem

import com.devom.domain.poojaitem.CreatePoojaItemUseCase
import com.devom.domain.poojaitem.GetPoojaItemUseCase
import com.devom.domain.poojaitem.RemovePoojaItemUseCase
import com.devom.domain.poojaitem.UpdatePoojaItemUseCase

class PoojaItem {
    val getPoojaItemUseCase by lazy { GetPoojaItemUseCase() }
    val createPoojaItemUseCase by lazy { CreatePoojaItemUseCase() }
    val updatePoojaItemUseCase by lazy { UpdatePoojaItemUseCase() }
    val removePoojaItemUseCase by lazy { RemovePoojaItemUseCase() }
}