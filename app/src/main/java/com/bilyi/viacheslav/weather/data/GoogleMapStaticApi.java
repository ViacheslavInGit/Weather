package com.bilyi.viacheslav.weather.data;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleMapStaticApi {

    @GET("staticmap")
    Single<ResponseBody> takePhotoResponseBody(
            @Query(StaticApiRequestSchema.MapParameters.SIZE) @NonNull String size,
            @Query(StaticApiRequestSchema.MapParameters.SCALE) int scale,
            @Query(StaticApiRequestSchema.FeatureParameters.PATH) @NonNull String path,
            @Query(StaticApiRequestSchema.FeatureParameters.MARKERS) @NonNull String fromMarker,
            @Query(StaticApiRequestSchema.FeatureParameters.MARKERS) @NonNull String toMarker,
            @Query(StaticApiRequestSchema.CredentialParameters.KEY) @NonNull String mapApiKey
    );
}
