package com.bilyi.viacheslav.weather.data;

public interface StaticApiRequestSchema {

    interface LocationParameters {

        String CENTER = "center";

        String ZOOM = "zoom";
    }

    interface MapParameters {

        String SIZE = "size";

        String SCALE = "scale";

        String FORMAT = "format";

        String MAP_TYPE = "maptype";

        String LANGUAGE = "language";

        String REGION = "region";
    }

    interface FeatureParameters {

        String MARKERS = "markers";

        String PATH = "path";

        String VISIBLE = "visible";

        String STYLE = "style";
    }

    interface CredentialParameters {

        String KEY = "key";

        String SIGNATURE = "signature";
    }
}
