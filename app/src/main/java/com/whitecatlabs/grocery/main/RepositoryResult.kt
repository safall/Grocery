package com.whitecatlabs.grocery.main

sealed interface RepositoryResult<T>
data class Success<T>(val data: T) : RepositoryResult<T>
data class Failure<T>(val error: Exception) : RepositoryResult<T>