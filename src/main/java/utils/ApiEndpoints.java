package utils;

public enum ApiEndpoints {
    BASE_URL("https://asia.creativecdn.com"),
    COLUMN_URL("https://ecapi-cdn.pchome.com.tw/fsapi/cms/contents");

    private final String url;

    ApiEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
