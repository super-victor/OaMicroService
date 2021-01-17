//api.js
const BASE_URL = 'http://101.201.254.63:8080';
const BASE_URL_a = 'http://121.196.40.164:8080';
const BASE_URL_b = 'http://192.168.43.187:8080';

export const myRequest = (options)=>{
    return new Promise((resolve,reject)=>{
        //加token
        uni.request({
            url: BASE_URL_b+options.url,
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