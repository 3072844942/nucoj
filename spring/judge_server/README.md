#### NUCOJ判题服务器

### 支持语言
> C， C_PLUS_PLUS， PYTHON  
> Java? 存在一些BUG

### 执行判题 
**请求URL：**
- `/judge/result `

**请求方式：**
- POST


**请求参数示例**

``` json
{
    "language": "JAVA",
    "solutions": [
        {
            "stdIn": "http://cdn.yuzzl.top/1596680709217.in",
            "expectedStdOut": "http://cdn.yuzzl.top/1596680709217.out"
        },
        {
            "stdIn": "http://cdn.yuzzl.top/1596680709217.in",
            "expectedStdOut": "http://cdn.yuzzl.top/1596680709217.out"
        }
    ],
    "submissionCode":"import java.util.Scanner;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner in = new Scanner(System.in);\n        int a = in.nextInt();\n        int b = in.nextInt();\n        System.out.println(a + b);\n    }\n}",
    "judgePreference": "OI",
    "memoryLimit":30000,
    "outputLimit": 10000,
    "special": null,
    "specialLanguage": null
}
```

** 返回示例 **

``` json
  {
    "judgeResults": [
        {
            "realTimeCost": "112",
            "memoryCost": "26404",
            "cpuTimeCost": "104",
            "condition": 0,
            "stdInPath": "/home/judgeEnvironment/resolutions/e11a6dc4-b882-4e18-9ea5-8f9582fc4a60/solution.in",
            "stdOutPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_0.out",
            "stdErrPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_0.err",
            "message": "ACCEPT",
            "answer: null
        },
        {
            "realTimeCost": "131",
            "memoryCost": "29028",
            "cpuTimeCost": "119",
            "condition": 1,
            "stdInPath": "/home/judgeEnvironment/resolutions/e11a6dc4-b882-4e18-9ea5-8f9582fc4a60/solution.in",
            "stdOutPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_1.out",
            "stdErrPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_1.err",
            "message": "WORONG_ANSWER",
            "answer: null
        }
    ],
    "submissionId": "c4154a3e-4105-445c-84e4-dd92d4086f2b",
    "judgeEndTime": 1599143354314,
    "extraInfo": []
}
```

### 特别判断
**请求URL：**
- `/judge/special `

**请求方式：**
- POST


**请求参数示例**

``` json
{
    "language": "JAVA",
    "solutions": [
        {
            "stdIn": "http://cdn.yuzzl.top/1596680709217.in",
            "expectedStdOut": "http://cdn.yuzzl.top/1596680709217.in"
        }
    ],
    "submissionCode":"import java.util.Scanner;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner in = new Scanner(System.in);\n        int a = in.nextInt();\n        int b = in.nextInt();\n        System.out.println(a + b);\n    }\n}",
    "judgePreference": "OI",
    "memoryLimit":30000,
    "outputLimit": 10000,
    "special": "import java.util.Scanner;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner in = new Scanner(System.in);\n        int a = in.nextInt();\n        int b = in.nextInt();\n        System.out.println(a + b);\n    }\n}",
    "specialLanguage": "JAVA"
}
```

** 返回示例 **

``` json
  {
    "judgeResults": [
        {
            "realTimeCost": "112",
            "memoryCost": "26404",
            "cpuTimeCost": "104",
            "condition": 0,
            "stdInPath": "/home/judgeEnvironment/resolutions/e11a6dc4-b882-4e18-9ea5-8f9582fc4a60/solution.in",
            "stdOutPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_0.out",
            "stdErrPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_0.err",
            "message": "ACCEPT",
            "answer: null
        },
        {
            "realTimeCost": "131",
            "memoryCost": "29028",
            "cpuTimeCost": "119",
            "condition": 1,
            "stdInPath": "/home/judgeEnvironment/resolutions/e11a6dc4-b882-4e18-9ea5-8f9582fc4a60/solution.in",
            "stdOutPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_1.out",
            "stdErrPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_1.err",
            "message": "WORONG_ANSWER",
            "answer: null
        }
    ],
    "submissionId": "c4154a3e-4105-445c-84e4-dd92d4086f2b",
    "judgeEndTime": 1599143354314,
    "extraInfo": []
}
```

### 测试代码

**请求URL：**
- `/judge/debug `

**请求方式：**
- POST


**请求参数示例**

``` json
{
    "language": "JAVA",
    "solutions": [
        {
            "stdIn": "http://cdn.yuzzl.top/1596680709217.in",
            "expectedStdOut": "http://cdn.yuzzl.top/1596680709217.in"
        }
    ],
    "submissionCode":"import java.util.Scanner;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner in = new Scanner(System.in);\n        int a = in.nextInt();\n        int b = in.nextInt();\n        System.out.println(a + b);\n    }\n}",
    "judgePreference": "OI",
    "memoryLimit":30000,
    "outputLimit": 10000,
    "special": null,
    "specialLanguage": null
}
```

** 返回示例 **
返回结果存储于answer中
``` json
  {
    "judgeResults": [
        {
            "realTimeCost": "112",
            "memoryCost": "26404",
            "cpuTimeCost": "104",
            "condition": 0,
            "stdInPath": "/home/judgeEnvironment/resolutions/e11a6dc4-b882-4e18-9ea5-8f9582fc4a60/solution.in",
            "stdOutPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_0.out",
            "stdErrPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_0.err",
            "message": "ACCEPT",
            "answer: answer
        },
        {
            "realTimeCost": "131",
            "memoryCost": "29028",
            "cpuTimeCost": "119",
            "condition": 0,
            "stdInPath": "/home/judgeEnvironment/resolutions/e11a6dc4-b882-4e18-9ea5-8f9582fc4a60/solution.in",
            "stdOutPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_1.out",
            "stdErrPath": "/home/judgeEnvironment/submissions/c4154a3e-4105-445c-84e4-dd92d4086f2b/running_1.err",
            "message": "ACCEPT",
            "answer: answer
        }
    ],
    "submissionId": "c4154a3e-4105-445c-84e4-dd92d4086f2b",
    "judgeEndTime": 1599143354314,
    "extraInfo": []
}
```