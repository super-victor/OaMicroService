//api.js
const BASE_URL = 'http://101.201.254.63:8080';


export const myRequest = (options)=>{
    return new Promise((resolve,reject)=>{
        //加token   
        uni.request({
            url: BASE_URL+options.url,
            method: options.method || 'GET',
            data: options.data || {},
            header:{
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6aGFuZ3NhbiIsImV4cCI6MTYxMTM2NTI0OX0.EMHBZvQjOL3AN8g0UuAL2V8TJk2O9ci5m5upzeQS4r7k9-KOBKecC0VXZUYe3jEA0AzppVZ48n_VYF6pboRfRw'

            },
            success: (res)=>{
                resolve(res);
            },
            fail: (err)=>{
                reject(err);
            }
        })
    })
}