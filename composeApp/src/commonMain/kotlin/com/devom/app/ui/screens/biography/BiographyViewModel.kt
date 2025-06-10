package com.devom.app.ui.screens.biography

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.app.models.SupportedFiles
import com.devom.app.utils.videoExtensions
import com.devom.models.auth.UserResponse
import com.devom.models.document.CreateDocumentInput
import com.devom.models.pandit.GetBiographyResponse
import com.devom.models.pandit.UpdateBiographyInput
import com.devom.utils.Application
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.network.onResult
import com.devom.utils.network.onResultNothing
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.extension
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.io.buffered
import kotlinx.io.readByteArray

class BiographyViewModel : ViewModel() {
    private val _user = MutableStateFlow<UserResponse?>(null)
    val user = _user

    private val _biography = MutableStateFlow<GetBiographyResponse?>(null)
    val biography = _biography

    fun uploadDocument(userId: String, platformFile: PlatformFile, supportedFiles: SupportedFiles) {
        viewModelScope.launch {
            Project.document.createDocumentUseCase.invoke(
                input = CreateDocumentInput(
                    userId = userId,
                    mimeType = "*/*",
                    documentType =  if (platformFile.extension.lowercase() in videoExtensions) "video" else "image",
                    description = supportedFiles.document,
                    documentName = platformFile.name,
                    file = platformFile.source().buffered().readByteArray()
                )
            ).collect {
                it.onResult {
                    getBiography(userId)
                    Application.showToast("Document uploaded successfully")
                }
            }
        }
    }

    fun removeDocument(documentId: String) {
        viewModelScope.launch {
            Project.document.removeDocumentUseCase.invoke(documentId).collect {
                it.onResultNothing {
                    getBiography(user.value?.userId.toString(), cachePolicy = CachePolicy.NetworkOnly)
                    Application.showToast("Document removed successfully")
                }
            }
        }
    }

    fun getBiography(userId: String , cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork) {
        viewModelScope.launch {
            Project.pandit.getBiographyUseCase.invoke(userId , cachePolicy).collect {
                it.onResult {
                    _biography.value = it.data
                }
            }
        }
    }

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.onResult {
                    _user.value = it.data
                    getBiography(it.data.userId.toString())
                }
            }
        }
    }


    fun updateBiography(input: UpdateBiographyInput) {
        viewModelScope.launch {
            Project.pandit.updateBiographyUseCase.invoke(
                input.copy(
                    userId = _user.value?.userId ?: 0
                )
            ).collect {
                it.onResultNothing {
                    getBiography(user.value?.userId.toString() , cachePolicy = CachePolicy.NetworkOnly)
                    Application.showToast("Biography updated successfully")
                }
            }
        }
    }
}