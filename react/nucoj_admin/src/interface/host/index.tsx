interface Host {
    "code": string,
    "message": string,
    "request": any, // 不清楚是什么
    "data": {
        "workPath": object,
        "scriptPath": string,
        "resolutionPath": string,
        "port": number,
        "workingAmount": number,
        "cpuCoreAmount": number,
        "memoryCostPercentage": number,
        "cpuCostPercentage": number,
        "queueAmount": number,
        "maxWorkingAmount": number,
        "version": string
    }
}

export type {Host}