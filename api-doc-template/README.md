# XXX开放平台API接口文档
## 一、接口列表
### 1.订单查询接口
查询订单详情接口
### 1.1 服务地址
正式地址：http://api.hostname/appctx/order/query/<br>
测试地址：http://staging.hostname/appctx/order/query/
### 1.2 交互协议
HTTP/POST
### 1.3 请求参数
| 参数 | 参数类型 | 是否必填|最大长度 |描述 |
| --- | --- | --- | ---- | ---- |
|app_key | string | 是|20|申请key |
|sig | string | 是|32|签名 |
|order_id | string | 是|20|订单id |
### 1.4 响应参数
|参数|参数类型|描述|
|---|---|---|
|code|string|状态码|
|msg|string|提示信息|
|data|  | 数据|

### 1.5 响应示例
```
{
    "code": "0", 
    "msg": "调用成功", 
    "data": {
        "order_id": "1234567", 
        "user_id": "2345"
    }
}
```
### 1.6 异常示例
```
{
    "code": "1001", 
    "msg": "接口签名验签不通过", 
    "data": null
}
```

##二、接口验签
为了保证接口不被恶意访问，需要对接口做验签。
### 2.1签名计算
STEP1：对POST参数按照KEY进行排序(参数值为null的不要带入签名计算);<br>
STEP2：将排序后的KV对(参数名-值对)拼接;<br>
STEP3：将secret拼接到最后;<br>
STEP4：将所得字符串进行SHA-1运算，返回即为sn的值.<br>

### 2.2 Java版
```
public static String genUrlSign(Map<String, String> paramMap, final String appkey, final String secret) {
        if (paramMap.isEmpty())
            return "";
        String[] keyArray = paramMap.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);

        // 拼接有序的参数名-值串
        // 格式： appkey+{key+value}n+secret
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appkey);
        for (String key : keyArray) {
            stringBuilder.append(key).append(paramMap.get(key));
        }
        stringBuilder.append(secret);
        String codes = stringBuilder.toString();
        // SHA-1编码， 这里使用的是Apache Codec
        String sign = org.apache.commons.codec.digest.DigestUtils.shaHex(codes).toUpperCase();
        return sign;
    }

```


##三、状态码列表
|状态码|描述|
|---|---|
|0|调用成功|
|1000|请求参数不合法|
|1001|签名校验不通过|





