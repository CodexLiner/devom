package com.devom.app.ui.screens.document

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.app.models.SupportedFiles
import com.devom.models.auth.UserResponse
import com.devom.models.document.CreateDocumentInput
import com.devom.models.document.GetDocumentResponse
import com.devom.utils.network.onResult
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.extension
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.io.buffered
import kotlinx.io.readByteArray

class UploadDocumentViewModel : ViewModel() {
    private val _documents = MutableStateFlow<List<GetDocumentResponse>>(emptyList())
    val documents = _documents

    private val _user = MutableStateFlow<UserResponse?>(null)
    val user = _user

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.onResult {
                    _user.value = it.data
                    getDocuments(user.value?.userId.toString())
                }
            }
        }
    }
    fun uploadDocument(userId: String, platformFile: PlatformFile, supportedFiles: SupportedFiles) {
        viewModelScope.launch {
            Project.document.createDocumentUseCase.invoke(
                input = CreateDocumentInput(
                    userId = userId,
                    mimeType = "image/${platformFile.extension}",
                    documentType = supportedFiles.type,
                    description = supportedFiles.document,
                    documentName = platformFile.name,
                    file = platformFile.source().buffered().readByteArray()


                )
            ).collect {
                it.onResult {

                }
            }
        }
    }

    fun getDocuments(userId: String) {
        viewModelScope.launch {
            Project.document.getDocumentsUseCase.invoke(userId = userId).collect {
                it.onResult {
                    _documents.value = it.data
                }
            }
        }
    }
}