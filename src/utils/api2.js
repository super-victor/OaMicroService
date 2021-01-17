//api2.js
const BASE_URL = 'http://192.168.31.116:8085';

export const myRequest2 = (options)=>{
    return new Promise((resolve,reject)=>{
        //加token
        uni.request({
            url: BASE_URL+options.url,
            method: options.method || 'GET',
            data: options.data || {},
            header:{
                'Content-Type': 'application/x-www-form-urlencoded'
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