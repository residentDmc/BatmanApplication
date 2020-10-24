package com.akaf.batmanapplication.utils

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class ToStringConverterFactory : Converter.Factory() {

    companion object {
        private val MEDIA_TYPE: MediaType = "text/plain".toMediaTypeOrNull()!!
        private var toStringConverterFactory: ToStringConverterFactory? = null

        val instance: ToStringConverterFactory
            get() {
                if (toStringConverterFactory == null) toStringConverterFactory =
                    ToStringConverterFactory()
                return toStringConverterFactory!!
            }
    }

    fun converterFactory() = this

    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) = object : Converter<ResponseBody, Any?> {
        val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)
        override fun convert(value: ResponseBody) = if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
    }


    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return when (String::class.java) {
            type -> Converter { value: String? ->
                value!!.toRequestBody(MEDIA_TYPE)
            }
            else -> super.requestBodyConverter(
                type,
                parameterAnnotations,
                methodAnnotations,
                retrofit
            )!!
        }
    }
}