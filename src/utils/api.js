//api.js
const BASE_URL = 'http://101.201.254.63:8080';
export const myRequest = (options)=>{
    return new Promise((resolve,reject)=>{
        // if(options.check){
        //     let key = wx.getStorageSync('userKey').key;
        //     options.data = {...options.data , check:key };
        // }
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