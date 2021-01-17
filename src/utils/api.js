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
                'token' : wx.getStorageSync('userToken')
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