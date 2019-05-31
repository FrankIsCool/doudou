package com.common.weather;

import com.alibaba.fastjson.annotation.JSONField;

public class WeatherData {
    @JSONField(name = "city")
    private String city;
    @JSONField(name = "temperature")
    private String temperature ;//温度，可能为空
    @JSONField(name = "humidity")
    private String humidity ;//湿度，可能为空
    @JSONField(name = "info")
    private String info ;//	天气情况
    @JSONField(name = "wid")
    private String wid ;//天气标识id，可参考小接口2
    @JSONField(name = "direct")
    private String direct ;//	风向，可能为空
    @JSONField(name = "power")
    private String power ;//	风力，可能为空
    @JSONField(name = "aqi")
    private String aqi ;//	空气质量指数，可能为空

}
